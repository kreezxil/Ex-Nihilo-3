package exnihilo2.util.helpers;

import exnihilo2.EN2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public abstract class PositionHelper {
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
	
	public static boolean isTopBlock(World world, BlockPos pos)
	{
		for (int y = world.getChunkFromBlockCoords(pos).getTopFilledSegment() + 16; y >= pos.getY(); y--)
		{
			if (world.getBlockState(new BlockPos(pos.getX(), y, pos.getZ())).getBlock() != Blocks.air)
			{
				if (y > pos.getY())
				{
					return false;
				}				
				else if (y == pos.getY())
				{
					return true;
				}
			}
		}
		
		//There are no blocks above the current position. It technically counts.
		return true;
	}
	
	public static boolean isRainingAt(World world, BlockPos pos)
	{
		return world.isRaining() && world.getBiomeGenForCoords(pos).getFloatRainfall() > 0f && isTopBlock(world, pos);
	}
	
	public static boolean canRainReach(World world, BlockPos pos)
	{
		if (world.isRaining())
		{
			if (isRainingAt(world, pos))
			{
				return true;
			}
			
			else
			{
				for (int x = -1; x < 2; x += 2)
				{
					for (int z = -1; z < 2; z += 2)
					{
						if (isRainingAt(world, new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z)))
						{
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean isChunkSpawn(World world, Chunk chunk)
	{
		double x = (double)world.getWorldInfo().getSpawnX() / world.provider.getMovementFactor();
		double z = (double)world.getWorldInfo().getSpawnZ() / world.provider.getMovementFactor();
		
		return getChunkContainsPoint(chunk, (int)x, (int)z);
	}
	
	public static boolean getChunkContainsPoint(Chunk chunk, int x, int z)
	{
    	return (x >> 4 == chunk.xPosition && z >> 4 == chunk.zPosition);
	}
}
