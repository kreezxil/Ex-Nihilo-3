package exnihilo2.crafting.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import exnihilo2.items.EN2Items;

public class BucketRecipes {
	public static void register()
	{
		//porcelain bucket
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(EN2Items.bucket_porcelain_raw, 1),
						"x x",
						" x ",
						'x', new ItemStack(EN2Items.porcelain, 1)));

		//porcelain bucket smelting
		GameRegistry.addSmelting(new ItemStack(EN2Items.bucket_porcelain_raw, 1), new ItemStack(EN2Items.bucket_porcelain_empty, 1), 0);

	}
}
