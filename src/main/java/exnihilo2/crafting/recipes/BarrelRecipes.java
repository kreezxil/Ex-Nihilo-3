package exnihilo2.crafting.recipes;

import exnihilo2.blocks.EN2Blocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BarrelRecipes {
	public static void register()
	{
		//wood barrels
		for(int i = 0; i < 6; i++)
		{
			GameRegistry.addRecipe(new ItemStack(EN2Blocks.barrel_wood, 1, i),
					"x x",
					"x x",
					"xyx",
					'x', new ItemStack(Blocks.planks, 1, i), 
					'y', new ItemStack(Blocks.wooden_slab, 1, i));
		}
		
		//glass barrel
		GameRegistry.addRecipe(new ItemStack(EN2Blocks.barrel_glass, 1, 0),
				"x x",
				"x x",
				"xyx",
				'x', new ItemStack(Blocks.glass, 1, 0), 
				'y', new ItemStack(Blocks.glass_pane, 1, 0));
		
		//stained glass barrels
		for(int i = 0; i < 16; i++)
		{
			GameRegistry.addRecipe(new ItemStack(EN2Blocks.barrel_glass_colored, 1, i),
					"x x",
					"x x",
					"xyx",
					'x', new ItemStack(Blocks.stained_glass, 1, i), 
					'y', new ItemStack(Blocks.stained_glass_pane, 1, i));
		}
	}
}
