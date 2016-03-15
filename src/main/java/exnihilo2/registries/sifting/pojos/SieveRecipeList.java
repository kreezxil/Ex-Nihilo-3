package exnihilo2.registries.sifting.pojos;

import java.util.ArrayList;

import exnihilo2.registries.composting.pojos.CompostRecipe;

public class SieveRecipeList {
	private ArrayList<SieveRecipe> recipes = new ArrayList<SieveRecipe>();

	public ArrayList<SieveRecipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(ArrayList<SieveRecipe> recipes) {
		this.recipes = recipes;
	}
}
