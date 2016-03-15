package exnihilo2.world;

import java.io.File;

import exnihilo2.EN2;
import exnihilo2.world.generation.WorldProviderVoidSurface;
import exnihilo2.world.generation.WorldProviderVoidEnd;
import exnihilo2.world.generation.WorldProviderVoidHell;
import exnihilo2.world.generation.templates.TemplateLoader;
import exnihilo2.world.generation.templates.defaults.TemplateExNihiloEasy;
import exnihilo2.world.generation.templates.defaults.TemplateExNihiloHard;
import exnihilo2.world.generation.templates.defaults.TemplateExNihiloModerate;
import exnihilo2.world.generation.templates.defaults.TemplateSkyblock21;
import exnihilo2.world.generation.templates.pojos.Template;
import exnihilo2.world.manipulation.Moss;
import exnihilo2.world.manipulation.Mycelium;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

public class EN2World {
	private static String CATEGORY_WORLD_GEN = "world generation";
	private static String CATEGORY_WORLD_MOD = "world manipulation";
	
	private static Template template_overworld;
	private static Template template_nether;
	private static Template template_end;
	
	private static boolean gen_surface;
	private static boolean gen_nether;
	private static boolean gen_nether_allow_fortresses;
	private static boolean gen_end;
	private static boolean gen_end_allow_crystals;
	
	private static boolean moss_spread_with_rain;
	private static int moss_spread_speed;
	private static boolean mycelium_sprout_with_rain;
	private static int mycelium_sprout_speed;
	
	public static void configure(Configuration config)
	{
		gen_surface = config.get(CATEGORY_WORLD_GEN, "void overworld", false).getBoolean(false);
		gen_nether = config.get(CATEGORY_WORLD_GEN, "void nether", false).getBoolean(false);
		gen_nether_allow_fortresses = config.get(CATEGORY_WORLD_GEN, "void nether generate fortresses", false).getBoolean(false);
		gen_end = config.get(CATEGORY_WORLD_GEN, "void end", false).getBoolean(false);
		gen_end_allow_crystals = config.get(CATEGORY_WORLD_GEN, "void end generate crystals", false).getBoolean(false);
		
		TemplateSkyblock21.generate(getTemplatePath());
		TemplateExNihiloEasy.generate(getTemplatePath());
		TemplateExNihiloModerate.generate(getTemplatePath());
		TemplateExNihiloHard.generate(getTemplatePath());
		
		String template_overworld_name = config.get(CATEGORY_WORLD_GEN, "void overworld template", "skyblock_2-1_overworld.json").getString();
		String template_nether_name = config.get(CATEGORY_WORLD_GEN, "void nether template", "skyblock_2-1_nether.json").getString();
		String template_end_name = config.get(CATEGORY_WORLD_GEN, "void end template", "none").getString();
		
		if (!template_overworld_name.equals("none") && template_overworld_name.trim().length() > 0)
			template_overworld = TemplateLoader.load(EN2.path + File.separator + "templates" + File.separator + template_overworld_name);
		
		if (!template_nether_name.equals("none") && template_nether_name.trim().length() > 0)
			template_nether = TemplateLoader.load(EN2.path + File.separator + "templates" + File.separator + template_nether_name);
		
		if (!template_end_name.equals("none") && template_end_name.trim().length() > 0)
			template_end = TemplateLoader.load(EN2.path + File.separator + "templates" + File.separator + template_end_name);
		
		moss_spread_with_rain = config.get(CATEGORY_WORLD_MOD, "moss spreads when raining", true).getBoolean(true);
		moss_spread_speed = config.get(CATEGORY_WORLD_MOD, "moss spread speed", Moss.DEFAULT_GROWTH_SPEED).getInt();
		mycelium_sprout_with_rain = config.get(CATEGORY_WORLD_MOD, "mycelium sprouts when raining", true).getBoolean(true);;
		mycelium_sprout_speed = config.get(CATEGORY_WORLD_MOD, "mycelium sprout speed", Mycelium.DEFAULT_GROWTH_SPEED).getInt();
		
		Moss.setGrowth(moss_spread_speed);
		Moss.setSpreadsWhileRaining(moss_spread_with_rain);
		Mycelium.setGrowth(mycelium_sprout_speed);
		Mycelium.setSpreadsWhileRaining(mycelium_sprout_with_rain);
	}
	
	public static String getTemplatePath()
	{
		return EN2.path + File.separator + "templates";
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
	
	public static boolean isWorldGenerationOverridden(int dimension)
	{
		switch (dimension)
		{
		case -1:
			return gen_nether;
			
		case 0:
			return gen_surface;
			
		case 1:
			return gen_end;
			
		default:
			return false;
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
		if (!world.isRemote)
		{
			ChunkProviderServer provider = (ChunkProviderServer)world.getChunkProvider();
	        for (Object o : provider.func_152380_a().toArray())
	        {
	        	Moss.grow(world, (Chunk)o);
	        	Mycelium.grow(world, (Chunk)o);
	        }
		}
	}
}
