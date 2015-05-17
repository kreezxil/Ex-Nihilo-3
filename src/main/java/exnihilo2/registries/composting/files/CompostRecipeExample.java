package exnihilo2.registries.composting.files;

import java.util.ArrayList;

import exnihilo2.registries.composting.pojos.CompostRecipe;
import exnihilo2.registries.composting.pojos.CompostRecipeList;
import exnihilo2.util.enums.EnumMetadataBehavior;

public abstract class CompostRecipeExample {
	public static CompostRecipeList getExampleRecipeList()
	{
		CompostRecipeList example = new CompostRecipeList();
		example.getRecipes().add(new CompostRecipe("minecraft:apple", 0, EnumMetadataBehavior.IGNORED, 100, "FFF68F"));
		example.getRecipes().add(new CompostRecipe("minecraft:sapling", 1, EnumMetadataBehavior.SPECIFIC, 125, "2E8042"));
		
		return example;
	}
}
