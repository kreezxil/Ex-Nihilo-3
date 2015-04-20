package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraftforge.fluids.Fluid;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class FluidStateLogicLuminosity extends BarrelLogic{
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) 
	{
		if (barrel.getFluid() != null)
		{
			Fluid fluid = barrel.getFluid().getFluid();
			
			//set the light level. Just in case the fluid has changed.
			if (barrel.getLuminosity() != fluid.getLuminosity())
			{
				barrel.setLuminosity(fluid.getLuminosity());
			}
		}
		
		return false;
	}
}
