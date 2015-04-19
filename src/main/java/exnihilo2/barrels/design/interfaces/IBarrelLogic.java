package exnihilo2.barrels.design.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public interface IBarrelLogic {
	public boolean onActivate(TileEntityBarrel barrel);
	public boolean onUpdate(TileEntityBarrel barrel);
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item);
}
