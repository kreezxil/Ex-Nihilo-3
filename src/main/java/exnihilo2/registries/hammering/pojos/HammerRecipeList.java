package exnihilo2.registries.hammering.pojos;

import java.util.ArrayList;

import exnihilo2.registries.composting.pojos.CompostRecipe;

public class HammerRecipeList {
	private ArrayList<HammerRecipe> recipes = new ArrayList<HammerRecipe>();

	public ArrayList<HammerRecipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(ArrayList<HammerRecipe> recipes) {
		this.recipes = recipes;
	}
}
