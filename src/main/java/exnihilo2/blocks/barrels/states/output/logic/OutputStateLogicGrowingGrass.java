package exnihilo2.blocks.barrels.states.output.logic;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.helpers.PositionHelper;

public class OutputStateLogicGrowingGrass extends BarrelLogic{
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) 
	{
		if (barrel.getContents().getItem() == Item.getItemFromBlock(Blocks.dirt) && barrel.getContents().getMetadata() == 0)
		{
			if (barrel.getWorld().rand.nextInt(1000) == 0)
			{
				if (isRandomNearbyBlockGrass(barrel) && PositionHelper.getLightLevelAbove(barrel.getWorld(), barrel.getPos()) >= 9)
				{
					barrel.setContents(new ItemStack(Blocks.mycelium, 1));
				}
			}
		}
		
		return false;
	}
	
	private static boolean isRandomNearbyBlockGrass(TileEntityBarrel barrel)
	{
		World world = barrel.getWorld();
		BlockPos pos = barrel.getPos();
		int grass = 0;
		
		int x = world.rand.nextInt(3) - 1;
		int y = world.rand.nextInt(3) - 1;
		int z = world.rand.nextInt(3) - 1;
		
		if (world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z)).getBlock() == Blocks.mycelium)
		{
			return true;
		}
		
		return false;
	}
}
