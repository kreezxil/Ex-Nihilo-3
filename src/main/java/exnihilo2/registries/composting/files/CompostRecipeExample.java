package exnihilo2.registries.composting.files;

import java.util.ArrayList;

import exnihilo2.registries.composting.pojos.CompostRecipe;
import exnihilo2.registries.composting.pojos.CompostRecipeList;
import exnihilo2.util.enums.MetadataBehavior;

public abstract class CompostRecipeExample {
	public static CompostRecipeList getExampleRecipeList()
	{
		CompostRecipeList example = new CompostRecipeList();
		example.getRecipes().add(new CompostRecipe("minecraft:apple", 0, MetadataBehavior.IGNORED, 100, "FFF68F"));
		example.getRecipes().add(new CompostRecipe("minecraft:sapling", 1, MetadataBehavior.SPECIFIC, 125, "2E8042"));
		
		return example;
	}
}
