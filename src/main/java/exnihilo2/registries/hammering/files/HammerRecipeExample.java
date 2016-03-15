package exnihilo2.registries.hammering.files;

import java.util.ArrayList;

import exnihilo2.registries.composting.pojos.CompostRecipe;
import exnihilo2.registries.composting.pojos.CompostRecipeList;
import exnihilo2.registries.hammering.pojos.HammerRecipe;
import exnihilo2.registries.hammering.pojos.HammerRecipeList;
import exnihilo2.registries.hammering.pojos.HammerRecipeReward;
import exnihilo2.util.enums.EnumMetadataBehavior;

public abstract class HammerRecipeExample {
	public static HammerRecipeList getExampleRecipeList()
	{
		HammerRecipeList example = new HammerRecipeList();
		
		HammerRecipe coal = new HammerRecipe("minecraft:coal_block", 0, EnumMetadataBehavior.IGNORED);
		coal.getRewards().add(new HammerRecipeReward("minecraft:coal", 0, 9, 100, 0));
		example.getRecipes().add(coal);
		
		HammerRecipe oak = new HammerRecipe("minecraft:log", 0, EnumMetadataBehavior.SPECIFIC);
		oak.getRewards().add(new HammerRecipeReward("minecraft:stick", 0, 7, 100, 0));
		oak.getRewards().add(new HammerRecipeReward("minecraft:stick", 0, 1, 75, 5));
		oak.getRewards().add(new HammerRecipeReward("minecraft:stick", 0, 1, 25, 10));
		example.getRecipes().add(oak);
		
		return example;
	}
}
