package exnihilo2.barrels.architecture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

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

	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		return false;
	}

}
