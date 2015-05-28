package exnihilo2.crafting.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import exnihilo2.items.EN2Items;

public class MiscRecipes {
	public static void register()
	{
		registerSmeltingRecipes();
		
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
		GameRegistry.addSmelting(new ItemStack(Items.coal, 1, 1), new ItemStack(EN2Items.ash, 1), 0);
	}
}
