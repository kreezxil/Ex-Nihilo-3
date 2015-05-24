package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class FluidCraftStoneTrigger extends BarrelLogic{

	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {
		if (barrel.getFluid() != null && barrel.getFluid().getFluid() != null)
		{
			if (barrel.getFluid().getFluid().equals(FluidRegistry.WATER))
			{
				IBlockState up = barrel.getWorld().getBlockState(barrel.getPos().up());
				
				if (up.getBlock() == Blocks.lava && up.getBlock().getMetaFromState(up) == 0)
				{
					barrel.setContents(new ItemStack(Blocks.stone, 1));
					barrel.setState(BarrelStates.output);
					
					return true;
				}
				
				if (up.getBlock() == Blocks.lava && up.getBlock().getMetaFromState(up) > 0)
				{
					barrel.setContents(new ItemStack(Blocks.cobblestone, 1));
					barrel.setState(BarrelStates.output);
					
					return true;
				}
			}
		}
		
		return false;
	}

}
