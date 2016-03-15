package exnihilo2.entities.items;

import exnihilo2.EN2;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityItemEmeraldBlack extends EntityItem{
	public static final int SURVIVAL_CHANCE = 70;

    public EntityItemEmeraldBlack(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntityItemEmeraldBlack(World worldIn, double x, double y, double z, ItemStack stack)
    {
        super(worldIn, x, y, z, stack);
    }

    public EntityItemEmeraldBlack(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if(damageSource.isExplosion())
        {
        	if (isBlockSurrounded(this.getEntityWorld(),this.getPosition()))
        	{
        		if (!this.getEntityWorld().isRemote)
        		{
        			int survived = 0;
            		
            		for (int x = 0; x < this.getEntityItem().stackSize; x++)
            		{
            			if (getEntityWorld().rand.nextInt(100) < SURVIVAL_CHANCE)
            				survived++;
            		}
            		
            		this.dropItem(Items.diamond, survived);
        		}
        		
        		this.setDead();
        	}
        }
        
        return super.attackEntityFrom(damageSource, damage);
    }
    
    private static boolean isBlockSurrounded(World world, BlockPos pos)
	{
    	return !world.isAirBlock(pos.up()) 
    			&& !world.isAirBlock(pos.down())
    			&& !world.isAirBlock(pos.north())
    			&& !world.isAirBlock(pos.east())
    			&& !world.isAirBlock(pos.south())
    			&& !world.isAirBlock(pos.west());
	}
}
