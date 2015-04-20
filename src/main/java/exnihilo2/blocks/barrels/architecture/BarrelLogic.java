package exnihilo2.blocks.barrels.architecture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public abstract class BarrelLogic{

	public boolean onActivate(TileEntityBarrel barrel) {
		return false;
	}

	public boolean onUpdate(TileEntityBarrel barrel) {
		return false;
	}
	
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		return false;
	}

	//Player CAN be null if the item is inserted by pipes!
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		return false;
	}

}
