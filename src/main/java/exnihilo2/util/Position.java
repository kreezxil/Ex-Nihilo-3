package exnihilo2.util;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public abstract class Position {
	public static BlockPos getRandomPositionInChunk(World world, Chunk chunk)
	{
		return new BlockPos((chunk.xPosition * 16) + world.rand.nextInt(16), world.rand.nextInt(256), (chunk.zPosition * 16) + world.rand.nextInt(16));
	}
	
	public static BlockPos getRandomPositionNearBlock(World world, BlockPos pos)
	{
		return new BlockPos(pos.getX() + (world.rand.nextInt(3) - 1), pos.getY() + (world.rand.nextInt(3) - 1), pos.getZ() + (world.rand.nextInt(3) - 1));
	}
	
	public static boolean hasNearbyWaterSource(World world, BlockPos pos)
	{
		return world.getBlockState(getRandomPositionNearBlock(world, pos)).getBlock() == Blocks.water;
	}
}
