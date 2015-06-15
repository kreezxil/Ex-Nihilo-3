package exnihilo2.items.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;

public class ItemSpores extends Item{
	public ItemSpores()
	{
		super();

		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityPlayer player, EntityLivingBase entity)
	{
		if (!entity.worldObj.isRemote && entity instanceof EntityCow)
		{
			entity.setDead();
			EntityMooshroom mooshroom = new EntityMooshroom(entity.worldObj);
			mooshroom.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
			mooshroom.setHealth(entity.getHealth());
			mooshroom.renderYawOffset = entity.renderYawOffset;
			entity.worldObj.spawnEntityInWorld(mooshroom);
			entity.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, entity.posX, entity.posY + (double)(entity.height / 2.0F), entity.posZ, 0.0D, 0.0D, 0.0D);
			
			item.stackSize -= 1;

			if (item.stackSize <= 0)
			{
				player.destroyCurrentEquippedItem(); 
			}

			return true;
		}
		return false;
	}
}
