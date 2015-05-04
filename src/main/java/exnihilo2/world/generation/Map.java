package exnihilo2.world.generation;

import exnihilo2.EN2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class Map {
	public static void generateSpawnPlatform(World world, Chunk chunk, int xOffset, int zOffset)
	{
		for (int x = -1; x < 2; x++)
    	{
			for (int y = -1; y < 2; y++)
	    	{
				for (int z = -1; z < 2; z++)
		    	{
					setBlock(world, chunk, xOffset + x, 64 - y, zOffset + z, Blocks.dirt);
		    	}
	    	}
    	}
	}
	
	public static boolean getChunkContainsPoint(Chunk chunk, int x, int z)
	{
		int chunkXMin = chunk.xPosition * 16;
    	int chunkXMax = chunk.xPosition * 16 + 16;
    	int chunkZMin = chunk.zPosition * 16;
    	int chunkZMax = chunk.zPosition * 16 + 16;
    	
    	return (x >= chunkXMin && x < chunkXMax && z >= chunkZMin && z < chunkZMax);
	}
	
	public static void setBlock(World world, Chunk chunk, int x, int y, int z, Block block)
	{
		BlockPos pos = new BlockPos(x, y, z);
		
		if (getChunkContainsPoint(chunk, pos.getX(), pos.getZ()))
		{
			chunk.setBlockState(pos, block.getDefaultState());
		}
		else
		{
			world.getChunkFromBlockCoords(pos).setBlockState(pos, block.getDefaultState());
		}
	}
}
