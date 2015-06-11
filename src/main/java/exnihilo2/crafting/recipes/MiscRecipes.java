package exnihilo2.crafting.recipes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import exnihilo2.EN2;
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
		
		//web
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(Blocks.web, 1),
						"xxx",
						"xyx",
						"xxx",
						'x', new ItemStack(Items.string, 1),
						'y', new ItemStack(Items.slime_ball, 1)));
		
		//cobble from stones
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(Blocks.cobblestone, 1),
						"xx",
						"xx",
						'x', new ItemStack(EN2Items.stone, 1)));
		
		//porcelain
		GameRegistry.addShapelessRecipe(new ItemStack(EN2Items.porcelain, 1), 
				new ItemStack(Items.clay_ball, 1), 
				new ItemStack(EN2Items.ash), 
				new ItemStack(Items.dye, 1, 15)); //bonemeal

	}
	
	public static void registerSmeltingRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(Items.stick, 1), new ItemStack(EN2Items.ash, 1), 0);
		GameRegistry.addSmelting(new ItemStack(EN2Items.slimeball_black, 1), new ItemStack(Items.coal, 1, 0), 0);
		GameRegistry.addSmelting(new ItemStack(Blocks.mossy_cobblestone, 1, 0), new ItemStack(Blocks.cobblestone, 1, 0), 0);
		GameRegistry.addSmelting(new ItemStack(Blocks.stonebrick, 1, 1), new ItemStack(Blocks.stonebrick, 1, 2), 0);
		GameRegistry.addSmelting(new ItemStack(EN2Items.rust, 1), new ItemStack(Items.iron_ingot, 1), 1);
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
