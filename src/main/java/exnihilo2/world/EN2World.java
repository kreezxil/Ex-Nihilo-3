package exnihilo2.world;

import java.io.File;

import exnihilo2.EN2;
import exnihilo2.world.generation.WorldProviderVoidSurface;
import exnihilo2.world.generation.WorldProviderVoidEnd;
import exnihilo2.world.generation.WorldProviderVoidHell;
import exnihilo2.world.generation.maps.MapLoader;
import exnihilo2.world.generation.maps.pojos.Map;
import exnihilo2.world.manipulation.Moss;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

public class EN2World {
	private static String CATEGORY_WORLDGEN = "world generation";
	
	private static Map map;
	
	private static boolean gen_surface;
	private static boolean gen_nether;
	private static boolean gen_end;
	
	public static void load(Configuration config)
	{
		gen_surface = config.get(CATEGORY_WORLDGEN, "void overworld", false).getBoolean(false);
		gen_nether = config.get(CATEGORY_WORLDGEN, "void nether", false).getBoolean(false);
		gen_end = config.get(CATEGORY_WORLDGEN, "void end", false).getBoolean(false);
		
		map = MapLoader.load(EN2.path + File.separator + "maps" + File.separator + config.get(CATEGORY_WORLDGEN, "map file", "default.json").getString());
	}
	
	public static Map getMap()
	{
		return map;
	}
	
	public static void registerWorldProviders()
	{
		if (gen_end)
			hijackEndGeneration();
		
		if (gen_surface)
			hijackSurfaceGeneration();
		
		if (gen_nether)
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
	
	private static void hijackSurfaceGeneration()
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
