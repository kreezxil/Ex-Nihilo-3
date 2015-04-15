package exnihilo2.barrels.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.blocks.tileentities.TileEntityBarrel;

public interface IBarrelState 
{
	public void onActivate(TileEntityBarrel barrel);
	public void onUpdate(TileEntityBarrel barrel);
	public void onItemUse(TileEntityBarrel barrel, ItemStack item);
	public void onFluidUse(TileEntityBarrel barrel, FluidStack fluid);
	public void renderBarrelContents(TileEntityBarrel barrel, double x, double y, double z);
}
