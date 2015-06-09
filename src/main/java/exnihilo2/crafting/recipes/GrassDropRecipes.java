package exnihilo2.crafting.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class GrassDropRecipes {
	public static void register()
	{
		MinecraftForge.addGrassSeed(new ItemStack(Items.pumpkin_seeds, 1), 1);
		MinecraftForge.addGrassSeed(new ItemStack(Items.melon_seeds, 1), 1);
	}
}
