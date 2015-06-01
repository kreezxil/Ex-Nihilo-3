package exnihilo2.items.misc;

import exnihilo2.EN2;
import exnihilo2.entities.items.EntityItemEmeraldBlack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEmeraldBlack extends Item{
	public ItemEmeraldBlack()
	{
		super();
		
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		EntityItemEmeraldBlack emerald = new EntityItemEmeraldBlack(world, location.posX, location.posY, location.posZ, itemstack);
		emerald.setVelocity(location.motionX, location.motionY, location.motionZ);
		emerald.setDefaultPickupDelay();
		return emerald;
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}
	
	
}
