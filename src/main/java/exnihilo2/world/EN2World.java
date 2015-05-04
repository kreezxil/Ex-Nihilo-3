package exnihilo2.world;

import exnihilo2.EN2;
import exnihilo2.world.generation.WorldProviderVoidSurface;
import exnihilo2.world.generation.WorldProviderVoidEnd;
import exnihilo2.world.generation.WorldProviderVoidHell;
import exnihilo2.world.manipulation.Moss;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;

public class EN2World {
	public static void registerWorldProviders()
	{
		hijackEndGeneration();
		hijackOverworldGeneration();
		hijackNetherGeneration();
	}
	
	private static void hijackEndGeneration()
	{
		try
		{
			DimensionManager.unregisterProviderType(1);
			DimensionManager.registerProviderType(1, WorldProviderVoidEnd.class, true);
		}
		catch (Exception e)
		{
			EN2.log.error("Failed to hijack world provider for the End.");	
		}
	}
	
	private static void hijackOverworldGeneration()
	{
		try
		{
			DimensionManager.unregisterProviderType(0);
			DimensionManager.registerProviderType(0, WorldProviderVoidSurface.class, true);
		}
		catch (Exception e)
		{
			EN2.log.error("Failed to hijack world provider the Overworld.");	
		}
	}
	
	private static void hijackNetherGeneration()
	{
		try
		{
			DimensionManager.unregisterProviderType(-1);
			DimensionManager.registerProviderType(-1, WorldProviderVoidHell.class, true);
		}
		catch (Exception e)
		{
			EN2.log.error("Failed to hijack world provider for the Nether.");	
		}
	}
	
	public static void load(World world)
	{
		int x = (int)(world.getWorldInfo().getSpawnX() / world.provider.getMovementFactor() / 16);
        int z = (int)(world.getWorldInfo().getSpawnZ() / world.provider.getMovementFactor() / 16);
        
        world.getChunkProvider().provideChunk(x, z);  
	}
	
	public static void tick(World world)
	{
		ChunkProviderServer provider = (ChunkProviderServer)world.getChunkProvider();
        for (Object o : provider.func_152380_a().toArray())
        {
        	Moss.grow(world, (Chunk)o);
        }
	}
}
