package exnihilo2.crafting.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import exnihilo2.items.EN2Items;

public class CrookRecipes {
	public static void register()
	{
		//Crook!
		GameRegistry.addRecipe(
				new ShapedOreRecipe(
						new ItemStack(EN2Items.crook_wood, 1, 0),
						new Object[]
								{
							"xx",
							" x",
							" x",
							'x', "stickWood"
								}));

		//Bone Crook!
		GameRegistry.addRecipe(
				new ShapedOreRecipe(
						new ItemStack(EN2Items.crook_bone, 1, 0),
						new Object[]
								{
							"xx",
							" x",
							" x",
							'x', Items.bone
								}));
	}
}
