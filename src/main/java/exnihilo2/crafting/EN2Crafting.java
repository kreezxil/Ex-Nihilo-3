package exnihilo2.crafting;

import exnihilo2.crafting.recipes.BarrelRecipes;
import exnihilo2.crafting.recipes.CrookRecipes;
import exnihilo2.crafting.recipes.CrucibleRecipes;
import exnihilo2.crafting.recipes.FishingRecipes;
import exnihilo2.crafting.recipes.GrassDrops;
import exnihilo2.crafting.recipes.HammerRecipes;
import exnihilo2.crafting.recipes.MiscRecipes;
import exnihilo2.crafting.recipes.SieveRecipes;
import net.minecraftforge.common.config.Configuration;

public class EN2Crafting {
	private static final String CATEGORY_CRAFTING_OPTIONS = "crafting options";
	
	public static boolean barrels_allowed;
	public static boolean crooks_allowed;
	public static boolean crucibles_allowed;
	public static boolean hammers_allowed;
	public static boolean sieves_allowed;
	public static boolean diamond_manufacture_allowed;
	
	public static void configure(Configuration config)
	{
		barrels_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow barrels", true).getBoolean(true);
		crooks_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow crooks", true).getBoolean(true);
		crucibles_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow crucibles", true).getBoolean(true);
		hammers_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow hammers", true).getBoolean(true);
		sieves_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow sieves", true).getBoolean(true);
		diamond_manufacture_allowed = config.get(CATEGORY_CRAFTING_OPTIONS, "allow creating diamonds", true).getBoolean(true);
	}

	public static void registerRecipes()
	{
		if (barrels_allowed)
			BarrelRecipes.register();
		
		if (crooks_allowed)
			CrookRecipes.register();
		
		if (crucibles_allowed)
			CrucibleRecipes.register();
		
		if (hammers_allowed)
			HammerRecipes.register();
		
		if (sieves_allowed)
			SieveRecipes.register();
		
		if (diamond_manufacture_allowed)
			MiscRecipes.registerDiamondRecipes();
		
		MiscRecipes.registerSmeltingRecipes();
		MiscRecipes.registerOtherRecipes();
		
		FishingRecipes.register();
		GrassDrops.register();
	}
}
