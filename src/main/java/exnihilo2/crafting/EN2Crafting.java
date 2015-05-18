package exnihilo2.crafting;

import exnihilo2.crafting.recipes.BarrelRecipes;
import exnihilo2.crafting.recipes.CrookRecipes;
import exnihilo2.crafting.recipes.HammerRecipes;
import net.minecraftforge.common.config.Configuration;

public class EN2Crafting {
	private static final String CATEGORY_CRAFTING_OPTIONS = "crafting options";
	
	private static boolean barrels_allowed;
	private static boolean crooks_allowed;
	private static boolean hammers_allowed;
	
	public static void initialize(Configuration config)
	{
		barrels_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow barrels", true).getBoolean(true);
		crooks_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow crooks", true).getBoolean(true);
		hammers_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow hammers", true).getBoolean(true);
	}

	public static void registerRecipes()
	{
		if (barrels_allowed)
			BarrelRecipes.register();
		
		if (crooks_allowed)
			CrookRecipes.register();
		
		if (hammers_allowed)
			HammerRecipes.register();
	}
}
