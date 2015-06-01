package exnihilo2.crafting.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.items.EN2Items;

public class MiscRecipes {
	public static void registerOtherRecipes()
	{
		//dirt furnace
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(EN2Blocks.furnace_dirt, 1),
						"xxx",
						"x x",
						"xxx",
						'x', new ItemStack(Blocks.dirt, 1, 0)));
		
		//cobble from stones
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(Blocks.cobblestone, 1),
						"xx",
						"xx",
						'x', new ItemStack(EN2Items.stone, 1)));
		
		GameRegistry.addShapelessRecipe(new ItemStack(EN2Items.porcelain, 1), 
				new ItemStack(Items.clay_ball, 1), 
				new ItemStack(EN2Items.ash), 
				new ItemStack(Items.dye, 1, 15)); //bonemeal
	}
	
	public static void registerSmeltingRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(Items.stick, 1), new ItemStack(EN2Items.ash, 1), 0);
	}
	
	public static void registerDiamondRecipes()
	{
		GameRegistry.addRecipe(
				new ShapelessOreRecipe(new ItemStack(EN2Items.slimeball_black),
						new ItemStack(Items.slime_ball),
						new ItemStack(Items.coal, 1, 1),
						"dyeBlack"));
		
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(EN2Items.emerald_black, 1),
						"xxx",
						"xyx",
						"xxx",
						'x', new ItemStack(EN2Items.slimeball_black, 1),
						'y', new ItemStack(Items.emerald, 1)));
	}
}
