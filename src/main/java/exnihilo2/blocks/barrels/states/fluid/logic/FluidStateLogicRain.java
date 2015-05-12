package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.Position;

public class FluidStateLogicRain extends BarrelLogic{
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) 
	{
		if (barrel.getFluid() != null)
		{
			if (Position.isRainingAt(barrel.getWorld(), barrel.getPos()))
			{
				barrel.fill(new FluidStack(FluidRegistry.WATER, 1), true);
			}
		}
		
		return false;
	}
}
