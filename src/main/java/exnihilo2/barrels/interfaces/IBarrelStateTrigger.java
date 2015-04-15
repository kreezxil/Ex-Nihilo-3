package exnihilo2.barrels.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.blocks.tileentities.TileEntityBarrel;

public interface IBarrelStateTrigger {
	//This must be unique!
	public String getStateTriggerID();
	
	public boolean activate(TileEntityBarrel barrel);
	public boolean update(TileEntityBarrel barrel);
	public boolean itemUse(TileEntityBarrel barrel, ItemStack item);
	public boolean fluidUse(TileEntityBarrel barrel, FluidStack fluid);
}
