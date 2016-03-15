package exnihilo2.registries.sifting.files;

import java.util.ArrayList;

import exnihilo2.registries.composting.pojos.CompostRecipe;
import exnihilo2.registries.composting.pojos.CompostRecipeList;
import exnihilo2.registries.hammering.pojos.HammerRecipe;
import exnihilo2.registries.hammering.pojos.HammerRecipeList;
import exnihilo2.registries.hammering.pojos.HammerRecipeReward;
import exnihilo2.registries.sifting.pojos.SieveRecipe;
import exnihilo2.registries.sifting.pojos.SieveRecipeList;
import exnihilo2.registries.sifting.pojos.SieveRecipeReward;
import exnihilo2.util.enums.EnumMetadataBehavior;

public abstract class SieveRecipeExample {
	public static SieveRecipeList getExampleRecipeList()
	{
		SieveRecipeList example = new SieveRecipeList();
		
		SieveRecipe gravel = new SieveRecipe("minecraft:gravel", 0, EnumMetadataBehavior.IGNORED);
		gravel.getRewards().add(new SieveRecipeReward("minecraft:flint", 0, 1, 100));
		example.getRecipes().add(gravel);
		
		SieveRecipe oakleaves = new SieveRecipe("minecraft:leaves", 0, EnumMetadataBehavior.SPECIFIC);
		oakleaves.getRewards().add(new SieveRecipeReward("minecraft:sapling", 0, 1, 50));
		example.getRecipes().add(oakleaves);
		
		return example;
	}
}
