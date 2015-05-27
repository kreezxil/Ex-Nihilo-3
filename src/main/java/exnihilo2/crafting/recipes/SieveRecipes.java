package exnihilo2.crafting.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.items.EN2Items;

public class SieveRecipes 
{
	public static void register()
	{
		//wood sieves
		for(int i = 0; i < 6; i++)
		{
			GameRegistry.addRecipe(
					new ShapedOreRecipe(new ItemStack(EN2Blocks.sieve_wood, 1, i),
					"x x",
					"xxx",
					"y y",
					'x', new ItemStack(Blocks.planks, 1, i), 
					'y', "stickWood"));
		}
		
		//meshes
		//wood
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(EN2Items.mesh_wood, 1),
				"xxx",
				"xxx",
				"xxx",
				'x', "stickWood"));
		
		//silk (white)
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(EN2Items.mesh_silk_white, 1),
				"xxx",
				"xxx",
				"xxx",
				'x', new ItemStack(Items.string, 1)));
	}
}
