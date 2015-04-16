package exnihilo2.barrels.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public interface IBarrelState 
{
	public void onActivate(TileEntityBarrel barrel);
	public void onUpdate(TileEntityBarrel barrel);
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item);
	public void onUseItem(TileEntityBarrel barrel, ItemStack item);
	public void render(TileEntityBarrel barrel, double x, double y, double z);
}
