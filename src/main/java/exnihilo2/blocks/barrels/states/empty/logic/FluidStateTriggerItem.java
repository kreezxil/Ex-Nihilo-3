package exnihilo2.blocks.barrels.states.empty.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.fluid.BarrelStateFluid;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.Inventory;

public class FluidStateTriggerItem extends BarrelLogic {
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) 
	{
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(item);
		
		return fluid != null;
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(item);

		if (fluid != null)
		{
			if (item.getItem() == Items.potionitem && item.getItemDamage() == 0)
			{
				if (player != null)
				{
					//Without this line, the glass bottle would be consumed.
					player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.glass_bottle, 1, 0));
				}
				else
				{
					barrel.addOutput(new ItemStack(Items.glass_bottle, 1, 0));
				}

			}else
			{
				if (player != null)
				{
					player.inventory.setInventorySlotContents(player.inventory.currentItem, Inventory.getContainer(item));
				}
				else
				{
					barrel.addOutput(Inventory.getContainer(item));
				}
			}


			barrel.fill(fluid, true);
			return true;
		}

		return false;
	}
}
