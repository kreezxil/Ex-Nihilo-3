package exnihilo2.blocks.barrels.states.empty.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.registries.composting.CompostRegistry;
import exnihilo2.registries.composting.CompostRegistryEntry;
import exnihilo2.util.helpers.InventoryHelper;

public class CompostStateTrigger extends BarrelLogic{
	
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) 
	{
		return CompostRegistry.isCompostable(item);
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		CompostRegistryEntry recipe = CompostRegistry.getEntryForItemStack(item);
		
		if (recipe != null)
		{
			barrel.setState(BarrelStates.compost);
			barrel.getState().useItem(player, barrel, item);
			
			return true;
		}

		return false;
	}
}
