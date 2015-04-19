package exnihilo2.barrels.design.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public interface IBarrelState 
{
	public void onActivate(TileEntityBarrel barrel);
	public void onUpdate(TileEntityBarrel barrel);
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item);
	public void onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item);
	public boolean canExtractItem(TileEntityBarrel barrel);
	public boolean canManipulateFluids(TileEntityBarrel barrel);
	public void render(TileEntityBarrel barrel, double x, double y, double z);
}
