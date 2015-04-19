package exnihilo2.barrels.states.empty.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.barrels.architecture.BarrelLogic;
import exnihilo2.barrels.states.fluid.BarrelStateFluid;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public class FluidStateTriggerWeather extends BarrelLogic {
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {
		
		if (barrel.getWorld().getWorldInfo().isRaining() && barrel.getWorld().getBiomeGenForCoords(barrel.getPos()).rainfall > 0.0f)
		{
			if (barrel.getPos().getY() >= barrel.getWorld().getTopSolidOrLiquidBlock(barrel.getPos()).getY() - 1)
			{
				FluidStack water = new FluidStack(FluidRegistry.WATER, 0);
				barrel.fill(water, true);
				return true;
			}
		}
		
		return false;
	}
}
