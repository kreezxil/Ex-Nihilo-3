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

			if (state.getBlock() == Blocks.cobblestone || state.getBlock() == Blocks.stonebrick)
			{
				if (hasAdjacentMossSources(world, pos))
				{
					if (state.getBlock() == Blocks.cobblestone)
					{
						world.setBlockState(pos, Blocks.mossy_cobblestone.getDefaultState());
					}
					else if (state.getBlock() == Blocks.stonebrick)
					{
						world.setBlockState(pos, Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
					}
				}
			}
		}
	}

	private static boolean hasAdjacentMossSources(World world, BlockPos pos)
	{
		return (isMossSource(world, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()))
				|| isMossSource(world, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()))
				|| isMossSource(world, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1))
				|| isMossSource(world, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1))
				|| isMossSource(world, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()))
				|| isMossSource(world, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())));
	}

	private static boolean isMossSource(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		return state.getBlock() == Blocks.water || state.getBlock() == Blocks.mossy_cobblestone || (state.getBlock() == Blocks.stonebrick && state.getBlock().getMetaFromState(state) == 1);
	}
}
