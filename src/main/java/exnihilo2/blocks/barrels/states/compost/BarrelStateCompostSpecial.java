package exnihilo2.blocks.barrels.states.compost;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import exnihilo2.util.enums.EnumMetadataBehavior;

public class BarrelStateCompostSpecial extends BarrelStateCompost{
	private ArrayList<String> ingredients = new ArrayList<String>();
	
	public void addIngredient(ItemStack item, EnumMetadataBehavior behavior)
	{
		if (item != null && behavior != null)
		{
			if (behavior == EnumMetadataBehavior.IGNORED)
			{
				ingredients.add(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":*");
			}
			
			if (behavior == EnumMetadataBehavior.IGNORED)
			{
				ingredients.add(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":" + item.getMetadata());
			}
		}
	}

	public void removeIngredient(ItemStack item)
	{
		ingredients.remove(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":*");
		ingredients.remove(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":" + item.getMetadata());
	}
	
	public boolean isIngredient(ItemStack item)
	{
		if (ingredients.contains(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":*") || ingredients.contains(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":" + item.getMetadata()))
		{
			return true;
		}
		
		return false;
	}
}
