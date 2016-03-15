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
import exnihilo2.util.helpers.InventoryHelper;

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

		if (fluid != null && fluid.amount > 0)
		{
			if (player != null)
			{
				if (!player.capabilities.isCreativeMode)
				{
					if(item.stackSize > 1)
					{
						item.stackSize--;
						InventoryHelper.giveItemStackToPlayer(player, InventoryHelper.getContainer(item));
					}
					else
					{
						player.setCurrentItemOrArmor(0, InventoryHelper.getContainer(item));
					}
				}
			}
			else
			{
				barrel.addOutput(InventoryHelper.getContainer(item));
			}

			barrel.fill(fluid, true);
			barrel.getWorld().playSoundEffect(barrel.getPos().getX() + 0.5f, barrel.getPos().getY() + 0.5f, barrel.getPos().getZ() + 0.5f, "game.neutral.swim", 0.2f, 0.8f);
			return true;
		}

		return false;
	}
}
