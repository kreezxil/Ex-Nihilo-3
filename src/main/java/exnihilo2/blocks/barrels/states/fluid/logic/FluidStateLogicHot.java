package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class FluidStateLogicHot extends BarrelLogic{
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) 
	{
		if (barrel.getFluid() != null && barrel.getFluid().getFluid() != null)
		{
			//if the fluid is hot...
			if(barrel.getFluid().getFluid().getTemperature() > 400)
			{
				World world = barrel.getWorld();
				
				//and the barrel is flammable...
				if (world.getBlockState(barrel.getPos()).getBlock().getMaterial().getCanBurn())
				{
					//buuuurn baby burn!
					if (barrel.getTimerStatus() == -1.0d)
					{
						barrel.startTimer(400);
					}
					
					if (barrel.getTimerStatus() > 0 && barrel.getTimerStatus() < 1.0d)
					{
						BlockPos pos = barrel.getPos();

						if (barrel.getTimerTime() % 30 == 0)
						{
							world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + Math.random(), (double)pos.getY() + 1.2D, (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
						}

						if (barrel.getTimerTime() % 5 == 0)
						{
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)pos.getX() + Math.random(), (double)pos.getY() + 1.2D, (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
						}
					}
						
					if (barrel.getTimerStatus() == 1.0d)
					{
						if (barrel.getFluidAmount() < barrel.getCapacity())
						{
							BlockPos pos = barrel.getPos();
							BlockPos above = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
							
							if (world.isAirBlock(above))
							{
								world.setBlockState(above, Blocks.fire.getDefaultState(), 3);
							}
						}
						else
						{
							Block fblock = barrel.getFluid().getFluid().getBlock();
							
							world.setBlockState(barrel.getPos(), fblock.getDefaultState(), 3);
							world.notifyBlockOfStateChange(barrel.getPos(), fblock);
							
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
}
