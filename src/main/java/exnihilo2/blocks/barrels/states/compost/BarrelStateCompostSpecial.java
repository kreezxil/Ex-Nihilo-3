package exnihilo2.blocks.barrels.states.compost;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import exnihilo2.util.enums.MetadataBehavior;

public class BarrelStateCompostSpecial extends BarrelStateCompost{
	private ArrayList<String> ingredients = new ArrayList<String>();
	
	public void addIngredient(ItemStack item, MetadataBehavior behavior)
	{
		if (item != null && behavior != null)
		{
			if (behavior == MetadataBehavior.IGNORED)
			{
				ingredients.add(item.getUnlocalizedName() + ":*");
			}
			
			if (behavior == MetadataBehavior.IGNORED)
			{
				ingredients.add(item.getUnlocalizedName() + ":" + item.getMetadata());
			}
		}
	}

	public void removeIngredient(ItemStack item)
	{
		ingredients.remove(item.getUnlocalizedName() + ":*");
		ingredients.remove(item.getUnlocalizedName() + ":" + item.getMetadata());
	}
	
	public boolean isIngredient(ItemStack item)
	{
		if (ingredients.contains(item.getUnlocalizedName() + ":*") || ingredients.contains(item.getUnlocalizedName() + ":" + item.getMetadata()))
		{
			return true;
		}
		
		return false;
	}
}
