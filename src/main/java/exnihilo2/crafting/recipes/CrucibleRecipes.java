package exnihilo2.crafting.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.items.EN2Items;

public class CrucibleRecipes {
	public static void register()
	{
		GameRegistry.addRecipe(new ItemStack(EN2Blocks.crucible_raw, 1),
				"x x",
				"x x",
				"xxx",
				'x', new ItemStack(EN2Items.porcelain, 1));
		
		GameRegistry.addSmelting(new ItemStack(EN2Blocks.crucible_raw, 1), new ItemStack(EN2Blocks.crucible, 1), 0);
	}
}
