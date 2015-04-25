package exnihilo2.registries;

import java.util.HashMap;

import exnihilo2.registries.recipes.CompostRecipe;
import exnihilo2.util.enums.MetadataBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompostRegistry {
	private HashMap<String, CompostRecipe> recipes = new HashMap<String, CompostRecipe>();
	
	public void addRecipe(CompostRecipe recipe)
	{
		if (recipe != null)
		{
			String s = getRecipeKey(recipe);
			
			if (s != null && s.trim().length() > 0)
			{
				recipes.put(s, recipe);
			}
		}
	}
	
	public boolean isCompostable(ItemStack item)
	{
		return getRecipe(item) != null;
	}
	
	public CompostRecipe getRecipe(ItemStack item)
	{
		CompostRecipe recipe = recipes.get(item.getUnlocalizedName() + ":*");
		
		if (recipe != null)
		{
			return recipe;
		}
		else
		{
			return recipes.get(item.getUnlocalizedName() + ":" + item.getMetadata());
		}
	}
	
	private String getRecipeKey(CompostRecipe recipe)
	{
		String s = recipe.getInput().getUnlocalizedName();
		
		if (recipe.getMetadataBehavior() == MetadataBehavior.Ignored)
		{
			return s + ":*";
		}
		else if (recipe.getMetadataBehavior() == MetadataBehavior.Specified)
		{
			return s + ":" + recipe.getInput().getMetadata();
		}
		
		return null;
	}
}
