package exnihilo2.registries;

import exnihilo2.registries.composting.CompostRegistry;
import exnihilo2.registries.hammering.HammerRegistry;
import exnihilo2.registries.sifting.SieveRegistry;
import net.minecraftforge.common.config.Configuration;

public class EN2Registries {
	public static final String CATEGORY_DEFAULT_RECIPES = "default recipes";
	
	public static boolean loadCompostDefaults = true;
	public static boolean loadHammerDefaults = true;
	public static boolean loadSieveDefaults = true;
	
	public static void configure(Configuration config)
	{
		loadCompostDefaults = config.get(CATEGORY_DEFAULT_RECIPES, "load default compost recipes", true).getBoolean(true);
		loadHammerDefaults = config.get(CATEGORY_DEFAULT_RECIPES, "load default hammer recipes", true).getBoolean(true);
		loadSieveDefaults = config.get(CATEGORY_DEFAULT_RECIPES, "load default sieve recipes", true).getBoolean(true);
		
		CompostRegistry.initialize();
		HammerRegistry.initialize();
		SieveRegistry.initialize();
	}
}
