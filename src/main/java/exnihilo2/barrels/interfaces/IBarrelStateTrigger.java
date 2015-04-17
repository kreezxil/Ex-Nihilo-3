package exnihilo2.barrels.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public interface IBarrelStateTrigger {
	//This must be unique!
	public String getStateTriggerID();
	
	public boolean activate(TileEntityBarrel barrel);
	public boolean update(TileEntityBarrel barrel);
	public boolean itemUse(EntityPlayer player, TileEntityBarrel barrel, ItemStack item);
}
