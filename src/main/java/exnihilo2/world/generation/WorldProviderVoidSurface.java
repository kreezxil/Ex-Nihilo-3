package exnihilo2.world.generation;

import exnihilo2.world.EN2World;
import exnihilo2.world.generation.templates.pojos.Template;
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
		Template map = EN2World.getOverworldTemplate();
		
		if (map != null)
		{
			spawn = new BlockPos(spawn.getX(), map.getSpawnYLevel(), spawn.getZ());
		}
		else
		{
			spawn = worldObj.getTopSolidOrLiquidBlock(spawn);
		}
		
		return spawn;
	}
}
