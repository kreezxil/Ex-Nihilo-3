package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class FluidCraftObsidianTrigger extends BarrelLogic{

	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {
		if (barrel.getFluid() != null && barrel.getFluid().getFluid() != null)
		{
			if (barrel.getFluid().getFluid().equals(FluidRegistry.LAVA))
			{
				IBlockState up = barrel.getWorld().getBlockState(barrel.getPos().up());
				
				if (up.getBlock() == Blocks.water)
				{
					barrel.setContents(new ItemStack(Blocks.obsidian, 1));
					barrel.setState(BarrelStates.output);
					
					barrel.getWorld().playSoundEffect(barrel.getPos().getX() + 0.5f, barrel.getPos().getY() + 0.5f, barrel.getPos().getZ() + 0.5f, "random.fizz", 0.5f, 4.5f);
					
					return true;
				}
			}
		}
		
		return false;
	}

}
