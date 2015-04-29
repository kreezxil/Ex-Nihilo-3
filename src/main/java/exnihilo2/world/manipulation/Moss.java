package exnihilo2.world.manipulation;

import exnihilo2.EN2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class Moss {
	private static int speed = 16;
	
	public static void grow(World world, Chunk chunk)
	{
		for (int i = 0; i < speed; i++)
		{
			BlockPos pos = new BlockPos((chunk.xPosition * 16) + world.rand.nextInt(16), world.rand.nextInt(256), (chunk.zPosition * 16) + world.rand.nextInt(16));
			IBlockState state = world.getBlockState(pos);
			
			if (state.getBlock() == Blocks.water || state.getBlock() == Blocks.mossy_cobblestone || (state.getBlock() == Blocks.stonebrick && state.getBlock().getMetaFromState(state) == 1))
			{
				spreadToAdjacentBlocks(world, pos);
			}
		}
	}
	
	private static void spreadToAdjacentBlocks(World world, BlockPos pos)
	{
		boolean spread = false;
		
		for (int x = -1; x < 2; x++)
		{
			if (spread)
				break;
			
			for (int y = -1; y < 2; y++)
			{
				if (spread)
					break;
				
				for (int z = -1; z < 2; z++)
				{
					if (spread)
						break;
					
					BlockPos dest = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					IBlockState destState = world.getBlockState(dest);

					if (destState.getBlock() == Blocks.cobblestone)
					{
						world.setBlockState(dest, Blocks.mossy_cobblestone.getDefaultState());
						spread = true;
					}
					else if (destState.getBlock() == Blocks.stonebrick)
					{
						world.setBlockState(dest, Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
						spread = true; 
					}
				}
			}
		}
	}
}
