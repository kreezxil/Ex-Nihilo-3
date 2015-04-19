package exnihilo2.barrels.design.bases;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import exnihilo2.barrels.design.interfaces.IBarrelLogic;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public class BarrelLogic implements IBarrelLogic{

	@Override
	public boolean onActivate(TileEntityBarrel barrel) {
		return false;
	}

	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {
		return false;
	}

	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		return false;
	}

}
