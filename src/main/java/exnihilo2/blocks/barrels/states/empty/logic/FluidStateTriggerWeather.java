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
import exnihilo2.util.helpers.PositionHelper;

public class FluidStateTriggerWeather extends BarrelLogic {
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {

		if (PositionHelper.isRainingAt(barrel.getWorld(), barrel.getPos()))
		{
			barrel.fill(new FluidStack(FluidRegistry.WATER, 0), true);
			return true;
		}
		
		return false;
	}
}
