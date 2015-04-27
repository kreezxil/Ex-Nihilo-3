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

public class OutputStateLogicGrowingGrass extends BarrelLogic{
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) 
	{
		if (barrel.getContents().getItem() == Item.getItemFromBlock(Blocks.dirt))
		{
			if (barrel.getWorld().rand.nextInt(500) == 0)
			{
				if (isRandomNearbyBlockGrass(barrel) && canSeeSky(barrel) && getLightLevel(barrel) >= 9)
				{
					barrel.setContents(new ItemStack(Blocks.grass, 1));
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
		
		if (world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z)).getBlock() == Blocks.grass)
		{
			return true;
		}
		
		return false;
	}
	
	private static boolean canSeeSky(TileEntityBarrel barrel)
	{
		return barrel.getWorld().canBlockSeeSky(barrel.getPos());
	}
	
	private static int getLightLevel(TileEntityBarrel barrel)
	{
		return barrel.getWorld().getLight(new BlockPos(barrel.getPos().getX(), barrel.getPos().getY() + 1, barrel.getPos().getZ()));
	}
}
