package exnihilo2.blocks.barrels.states.witchwater.logic;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.fluids.EN2Fluids;

public class WitchwaterStateLogic extends BarrelLogic{
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {
		if (barrel.getTimerStatus() == -1)
		{
			barrel.startTimer(1000);
		}

		if (barrel.getTimerStatus() >= 1.0d)
		{
			if (!barrel.getWorld().isRemote)
			{
				barrel.transformFluidTo(new FluidStack(EN2Fluids.witchwater, 1000));
				barrel.setState(BarrelStates.fluid);
			}
			
			return true;
		}
		
		return false;
	}
}
