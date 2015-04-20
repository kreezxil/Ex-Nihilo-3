package exnihilo2.util;

import net.minecraft.item.ItemStack;

public class Inventory {
	public static ItemStack getContainer(ItemStack item)
	{
		if (item.stackSize == 1) 
		{
			if (item.getItem().hasContainerItem(item)) 
			{
				return item.getItem().getContainerItem(item);
			} else 
			{
				return null;
			}
		} else 
		{
			item.splitStack(1);
			return item;
		}
	}
}