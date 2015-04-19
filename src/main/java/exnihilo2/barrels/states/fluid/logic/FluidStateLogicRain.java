package exnihilo2.barrels.states.fluid.logic;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.barrels.design.bases.BarrelLogic;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public class FluidStateLogicRain extends BarrelLogic{
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) 
	{
		if (barrel.getFluid() != null)
		{
			World world = barrel.getWorld();
			BlockPos pos = barrel.getPos();
			
			if (barrel.getFluid().fluidID == FluidRegistry.WATER.getID() && world.getWorldInfo().isRaining() && world.getBiomeGenForCoords(pos).rainfall > 0.0f)
			{
				if (pos.getY() >= world.getTopSolidOrLiquidBlock(pos).getY() - 1)
				{
					FluidStack water = new FluidStack(FluidRegistry.WATER, 1);
					
					barrel.fill(water, true);
				}
			}
		}
		
		return false;
	}
}
