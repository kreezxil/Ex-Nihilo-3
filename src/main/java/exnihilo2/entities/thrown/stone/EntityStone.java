package exnihilo2.entities.thrown.stone;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityStone extends EntityThrowable
{
	public EntityStone(World par1World)
    {
        super(par1World);
    }

    public EntityStone(World par1World, EntityLivingBase par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
    }

    public EntityStone(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition position)
    {
        if (position.entityHit != null)
        {
            position.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.5f);
        }

        for (int i = 0; i < 10; ++i)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.TOWN_AURA, position.hitVec.xCoord, position.hitVec.yCoord, position.hitVec.zCoord, 0, 0, 0);
        }
        
        worldObj.playSoundAtEntity(this, "dig.stone", 0.5F, 1.0F);
        
        if (!this.worldObj.isRemote)
        {
        	if (worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && worldObj.rand.nextInt(20) == 0)
        	{
        		EntitySilverfish fishy = new EntitySilverfish(this.worldObj);
        		fishy.setLocationAndAngles(posX, posY, posZ, this.rotationYaw, 0);
        		this.worldObj.spawnEntityInWorld(fishy);
        	}
        	
            this.setDead();
        }
    }
}