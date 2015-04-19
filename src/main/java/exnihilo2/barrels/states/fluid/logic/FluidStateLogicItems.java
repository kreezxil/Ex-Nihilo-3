package exnihilo2.barrels.states.fluid.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.barrels.architecture.BarrelLogic;
import exnihilo2.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.InventoryUtil;

public class FluidStateLogicItems extends BarrelLogic{
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = barrel.getFluid();
		FluidStack ifluid = FluidContainerRegistry.getFluidForFilledItem(item);

		if (fluid != null )
		{
			if (ifluid != null && barrel.fill(ifluid, false) > 0)
			{
				return true;
			}
			
			if (FluidContainerRegistry.isEmptyContainer(item) && fluid.amount >= barrel.getCapacity())
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = barrel.getFluid();
		FluidStack ifluid = FluidContainerRegistry.getFluidForFilledItem(item);

		if (fluid != null )
		{
			if (ifluid != null && barrel.fill(ifluid, false) > 0)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, InventoryUtil.getContainer(item));
				barrel.fill(ifluid, true);
			}
			
			if (FluidContainerRegistry.isEmptyContainer(item) && fluid.amount >= barrel.getCapacity())
			{
				ItemStack full = FluidContainerRegistry.fillFluidContainer(fluid, item);
				
				if (full != null)
				{
					if (item.stackSize > 1) 
					{
						if (player.inventory.addItemStackToInventory(full)) 
						{
							item.stackSize -= 1;
						}
					} 
					else 
					{
						player.inventory.setInventorySlotContents(player.inventory.currentItem, full);
					}
					
					barrel.drain(barrel.getCapacity(), true);
					return true;
				}
			}
		}
		
		return false;
	}
}
