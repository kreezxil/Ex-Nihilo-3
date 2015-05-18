package exnihilo2.crafting.recipes;

import exnihilo2.items.EN2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class HammerRecipes {
	public static void register()
	{
		//Hammers!
		GameRegistry.addRecipe(
				new ShapedOreRecipe(
						new ItemStack(EN2Items.hammer_wood, 1, 0),
						new Object[]
								{
							" x ",
							" yx",
							"y  ",
							'x', "plankWood", 
							'y', "stickWood"
								}));

		GameRegistry.addRecipe(
				new ShapedOreRecipe(
						new ItemStack(EN2Items.hammer_stone, 1, 0),
						new Object[]
								{
							" x ",
							" yx",
							"y  ",
							'x', Blocks.cobblestone, 
							'y', "stickWood"
								}));

		GameRegistry.addRecipe(
				new ShapedOreRecipe(
						new ItemStack(EN2Items.hammer_iron, 1, 0),
						new Object[]
								{
							" x ",
							" yx",
							"y  ",
							'x', Items.iron_ingot, 
							'y', "stickWood"
								}));

		GameRegistry.addRecipe(
				new ShapedOreRecipe(
						new ItemStack(EN2Items.hammer_gold, 1, 0),
						new Object[]
								{
							" x ",
							" yx",
							"y  ",
							'x', Items.gold_ingot, 
							'y', "stickWood"
								}));

		GameRegistry.addRecipe(
				new ShapedOreRecipe(
						new ItemStack(EN2Items.hammer_diamond, 1, 0),
						new Object[]
								{
							" x ",
							" yx",
							"y  ",
							'x', Items.diamond, 
							'y', "stickWood"
								}));
	}
}
