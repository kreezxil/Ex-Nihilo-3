package exnihilo2.crafting.recipes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomFishable;
import exnihilo2.EN2;

public class FishingRecipes {
	public static void register()
	{
		tryToAddFishingRecipe(new WeightedRandomFishable(new ItemStack(Items.reeds, 1), 1));
	}
	
	private static void tryToAddFishingRecipe(WeightedRandomFishable item) 
	{
        	List list = getFishingRewardList();
        	
            list.add(item);
            
            setFishingRewardList(list);
    }
	
	private static List getFishingRewardList()
	{
		try
		{
			Field field = EntityFishHook.class.getDeclaredField("TREASURE");
	    	field.setAccessible(true);
	    	
	    	Field modifiersField = Field.class.getDeclaredField("modifiers");
	        modifiersField.setAccessible(true);
	        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	    	
	    	List list = new ArrayList((List)field.get(field));
	    	return list;
		}
		catch (Exception e1)
		{
			try
			{
				Field field = EntityFishHook.class.getDeclaredField("field_146041_e");
		    	field.setAccessible(true);
		    	
		    	Field modifiersField = Field.class.getDeclaredField("modifiers");
		        modifiersField.setAccessible(true);
		        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		    	
		    	List list = new ArrayList((List)field.get(field));
		    	return list;
			}
			catch (Exception e2)
			{
				EN2.log.error("Unable to access list of vanilla fishing rewards!");
				
				return null;
			}
		}
	}
	
	private static void setFishingRewardList(List list)
	{
		try
		{
			Field field = EntityFishHook.class.getDeclaredField("TREASURE");
	    	field.setAccessible(true);
	    	
	    	Field modifiersField = Field.class.getDeclaredField("modifiers");
	        modifiersField.setAccessible(true);
	        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	        
	        field.set(null, list);
		}
		catch (Exception e1)
		{
			try
			{
				Field field = EntityFishHook.class.getDeclaredField("field_146041_e");
		    	field.setAccessible(true);

		    	Field modifiersField = Field.class.getDeclaredField("modifiers");
		        modifiersField.setAccessible(true);
		        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		        
		        field.set(null, list);
			}
			catch (Exception e2)
			{
				EN2.log.error("Unable to modify list of vanilla fishing rewards!");
			}
		}
	}
}
