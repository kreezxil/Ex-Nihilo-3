package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.helpers.InventoryHelper;

public class FluidSummonSlimeTrigger extends BarrelLogic{
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = barrel.getFluid();

		if (fluid != null  
				&& fluid.getFluid() != null 
				&& fluid.getFluid() == FluidRegistry.WATER 
				&& barrel.getFluidAmount() == barrel.getCapacity()
				&& barrel.getWorld().getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			return item.getItem() == Items.skull;
		}

		return false;
	}

	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = barrel.getFluid();

		if (fluid != null  
				&& fluid.getFluid() != null 
				&& fluid.getFluid() == FluidRegistry.WATER 
				&& barrel.getFluidAmount() == barrel.getCapacity()
				&& barrel.getWorld().getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			if (item.getItem() == Items.skull)
			{
				if (player != null)
				{
					if (!player.capabilities.isCreativeMode)
					{
						InventoryHelper.consumeItem(player, item);
					}
				}
				else
				{
					item.stackSize--;

					if (item.stackSize <= 0)
						item = null;
				}

				barrel.setState(BarrelStates.slime_green);

				return true;
			}

		}

		return false;
	}
}
