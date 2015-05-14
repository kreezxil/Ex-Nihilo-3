package exnihilo2.world;

import java.io.File;

import exnihilo2.EN2;
import exnihilo2.world.generation.WorldProviderVoidSurface;
import exnihilo2.world.generation.WorldProviderVoidEnd;
import exnihilo2.world.generation.WorldProviderVoidHell;
import exnihilo2.world.generation.templates.TemplateLoader;
import exnihilo2.world.generation.templates.pojos.Template;
import exnihilo2.world.manipulation.Moss;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

public class EN2World {
	private static String CATEGORY_WORLDGEN = "world generation";
	
	private static Template template_overworld;
	private static Template template_nether;
	private static Template template_end;
	
	private static boolean gen_surface;
	private static boolean gen_nether;
	private static boolean gen_nether_allow_fortresses;
	private static boolean gen_end;
	private static boolean gen_end_allow_crystals;
	
	public static void load(Configuration config)
	{
		gen_surface = config.get(CATEGORY_WORLDGEN, "void overworld", false).getBoolean(false);
		gen_nether = config.get(CATEGORY_WORLDGEN, "void nether", false).getBoolean(false);
		gen_nether_allow_fortresses = config.get(CATEGORY_WORLDGEN, "void nether generate fortresses", false).getBoolean(false);
		gen_end = config.get(CATEGORY_WORLDGEN, "void end", false).getBoolean(false);
		gen_end_allow_crystals = config.get(CATEGORY_WORLDGEN, "void end generate crystals", false).getBoolean(false);
		
		String template_overworld_name = config.get(CATEGORY_WORLDGEN, "void overworld template", "default.json").getString();
		String template_nether_name = config.get(CATEGORY_WORLDGEN, "void nether template", "none").getString();
		String template_end_name = config.get(CATEGORY_WORLDGEN, "void end template", "none").getString();
		
		if (!template_overworld_name.equals("none") && template_overworld_name.trim().length() > 0)
			template_overworld = TemplateLoader.load(EN2.path + File.separator + "templates" + File.separator + template_overworld_name);
		
		if (!template_nether_name.equals("none") && template_nether_name.trim().length() > 0)
			template_nether = TemplateLoader.load(EN2.path + File.separator + "templates" + File.separator + template_nether_name);
		
		if (!template_end_name.equals("none") && template_end_name.trim().length() > 0)
			template_end = TemplateLoader.load(EN2.path + File.separator + "templates" + File.separator + template_end_name);
	}
	
	public static Template getOverworldTemplate()
	{
		return template_overworld;
	}
	
	public static Template getNetherTemplate()
	{
		return template_nether;
	}
	
	public static boolean getNetherFortressesAllowed()
	{
		return gen_nether_allow_fortresses;
	}
	
	public static Template getEndTemplate()
	{
		return template_end;
	}
	
	public static boolean getEndCrystalsAllowed()
	{
		return gen_end_allow_crystals;
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
