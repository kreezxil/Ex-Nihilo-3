package exnihilo2.crafting.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import exnihilo2.items.EN2Items;

public class MiscRecipes {
	public static void register()
	{
		//cobble from stones
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(Blocks.cobblestone, 1),
						"xx",
						"xx",
						'x', new ItemStack(EN2Items.stone, 1)));
	}
}
