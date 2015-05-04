package exnihilo2.world.generation;

import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderVoidSurface extends WorldProviderSurface{

	@Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderVoidSurface(worldObj);
    }
	
	@Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
		return true;
    }
	
	@Override
	public BlockPos getRandomizedSpawnPoint()
	{
		BlockPos spawn = new BlockPos(worldObj.getSpawnPoint());
		spawn = worldObj.getTopSolidOrLiquidBlock(spawn);
		
		return spawn;
	}
}
