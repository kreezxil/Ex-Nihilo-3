package exnihilo2.registries.composting;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exnihilo2.EN2;
import exnihilo2.registries.composting.files.CompostRecipeLoader;
import exnihilo2.util.Color;
import exnihilo2.util.enums.MetadataBehavior;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CompostRegistry {
	private static HashMap<String, CompostRegistryEntry> recipes = new HashMap<String, CompostRegistryEntry>();
	
	public static void initialize()
	{
		registerVanillaRecipes();
		List<CompostRegistryEntry> entries = CompostRecipeLoader.load(EN2.path + File.separator + "recipes" + File.separator + "compost" + File.separator);
	
		if (entries != null && !entries.isEmpty())
		{
			for (CompostRegistryEntry entry : entries)
			{
				addRecipe(entry);
			}
		}
	}
	
	public static void addRecipe(CompostRegistryEntry recipe)
	{
		if (recipe != null)
		{
			String s = getRecipeKey(recipe);
			
			if (s != null && s.trim().length() > 0)
			{
				if (recipe.getVolume() > 0)
				{
					recipes.put(s, recipe);
				}
				else
				{
					removeRecipe(recipe.getInput(), recipe.getMetadataBehavior());
				}
			}
		}
	}
	
	public static void removeRecipe(ItemStack item, MetadataBehavior behavior)
	{
		if (behavior == MetadataBehavior.IGNORED)
			recipes.remove(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":*");
		
		if (behavior == MetadataBehavior.SPECIFIC)
			recipes.remove(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":" + item.getMetadata());
	}
	
	public static boolean isCompostable(ItemStack item)
	{
		return getRecipe(item) != null;
	}
	
	public static CompostRegistryEntry getRecipe(ItemStack item)
	{
		CompostRegistryEntry recipe = recipes.get(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":" + item.getMetadata());
		
		if (recipe != null)
		{
			return recipe;
		}
		else
		{
			return recipes.get(GameRegistry.findUniqueIdentifierFor(item.getItem())  + ":*");
		}
	}
	
	private static String getRecipeKey(CompostRegistryEntry recipe)
	{
		String s = GameRegistry.findUniqueIdentifierFor(recipe.getInput().getItem()).toString();
		
		if (recipe.getMetadataBehavior() == MetadataBehavior.IGNORED)
		{
			return s + ":*";
		}
		else if (recipe.getMetadataBehavior() == MetadataBehavior.SPECIFIC)
		{
			return s + ":" + recipe.getInput().getMetadata();
		}
		
		return null;
	}
	
	public static void registerVanillaRecipes()
	{
		//saplings
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 0), 125, new Color("35A82A"), MetadataBehavior.SPECIFIC)); //oak
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 1), 125, new Color("2E8042"), MetadataBehavior.SPECIFIC)); //spruce
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 2), 125, new Color("6CC449"), MetadataBehavior.SPECIFIC)); //birch
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 3), 125, new Color("22A116"), MetadataBehavior.SPECIFIC)); //jungle
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 4), 125, new Color("B8C754"), MetadataBehavior.SPECIFIC)); //acacia
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 5), 125, new Color("378030"), MetadataBehavior.SPECIFIC)); //dark_oak

		//leaves
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 0), 125, new Color("35A82A"), MetadataBehavior.SPECIFIC)); //oak
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 1), 125, new Color("2E8042"), MetadataBehavior.SPECIFIC)); //spruce
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 2), 125, new Color("6CC449"), MetadataBehavior.SPECIFIC)); //birch
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 3), 125, new Color("22A116"), MetadataBehavior.SPECIFIC)); //jungle
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves2), 1, 0), 125, new Color("B8C754"), MetadataBehavior.SPECIFIC)); //acacia
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves2), 1, 1), 125, new Color("378030"), MetadataBehavior.SPECIFIC)); //dark_oak

		//ghast tear
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.ghast_tear, 1), 5, new Color("FFFFFF"), MetadataBehavior.IGNORED));
		//rotten flesh
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.rotten_flesh, 1), 85, new Color("C45631"), MetadataBehavior.IGNORED));
		//spider eye
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.spider_eye, 1), 85, new Color("963E44"), MetadataBehavior.IGNORED));
		//spider eye fermented
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.fermented_spider_eye, 1), 85, new Color("963E44"), MetadataBehavior.IGNORED));
		
		//dandelion
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.yellow_flower), 1), 100, new Color("FFF461"), MetadataBehavior.SPECIFIC));
		//poppy
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 0), 100, new Color("FF1212"), MetadataBehavior.SPECIFIC));
		//blue orchid
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 1), 100, new Color("33CFFF"), MetadataBehavior.SPECIFIC));
		//allium
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 2), 100, new Color("F59DFA"), MetadataBehavior.SPECIFIC));
		//azure bluet
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 3), 100, new Color("E3E3E3"), MetadataBehavior.SPECIFIC));
		//red_tulip
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 4), 100, new Color("FF3D12"), MetadataBehavior.SPECIFIC));
		//orange tulip
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 5), 100, new Color("FF7E29"), MetadataBehavior.SPECIFIC));
		//white tulip
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 6), 100, new Color("FFFFFF"), MetadataBehavior.SPECIFIC));
		//pink tulip
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 7), 100, new Color("F5C4FF"), MetadataBehavior.SPECIFIC));
		//oxeye daisy
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 8), 100, new Color("E9E9E9"), MetadataBehavior.SPECIFIC));

		//sunflower
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 0), 100, new Color("FFDD00"), MetadataBehavior.SPECIFIC));
		//lilac
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 1), 100, new Color("FCC7F0"), MetadataBehavior.SPECIFIC));
		//tall grass
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 2), 100, new Color("23630E"), MetadataBehavior.SPECIFIC));
		//large fern
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 3), 100, new Color("23630E"), MetadataBehavior.SPECIFIC));
		//rose bush
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 4), 100, new Color("FF1212"), MetadataBehavior.SPECIFIC));
		//peony
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 5), 100, new Color("F3D2FC"), MetadataBehavior.SPECIFIC));

		//mushroom_brown
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.brown_mushroom), 1), 100, new Color("CFBFB6"), MetadataBehavior.IGNORED));
		//mushroom_red
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_mushroom), 1), 100, new Color("D6A8A5"), MetadataBehavior.IGNORED));
		
		//wheat
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.wheat, 1), 85, new Color("E3E162"), MetadataBehavior.IGNORED));
		//bread
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.bread, 1), 125, new Color("D1AF60"), MetadataBehavior.IGNORED));

		//pumpkin pie
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.pumpkin_pie, 1), 175, new Color("E39A6D"), MetadataBehavior.IGNORED));
		//egg
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.egg, 1), 80, new Color("FFFA66"), MetadataBehavior.IGNORED));

		//pork
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.porkchop, 1), 175, new Color("FFA091"), MetadataBehavior.IGNORED));
		//cooked pork
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.cooked_porkchop, 1), 175, new Color("FFFDBD"), MetadataBehavior.IGNORED));
	
		//beef
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.beef, 1), 175, new Color("FF4242"), MetadataBehavior.IGNORED));
		//cooked beef
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.cooked_beef, 1), 175, new Color("80543D"), MetadataBehavior.IGNORED));

		//mutton
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.mutton, 1), 175, new Color("FF4242"), MetadataBehavior.IGNORED));
		//cooked mutton
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.cooked_mutton, 1), 175, new Color("80543D"), MetadataBehavior.IGNORED));

		//chicken
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.chicken, 1), 175, new Color("FFE8E8"), MetadataBehavior.IGNORED));
		//cooked chicken
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.cooked_chicken, 1), 175, new Color("FA955F"), MetadataBehavior.IGNORED));

		//rabbit
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.rabbit, 1), 175, new Color("FFE8E8"), MetadataBehavior.IGNORED));
		//cooked rabbit
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.cooked_rabbit, 1), 175, new Color("FA955F"), MetadataBehavior.IGNORED));

		//fish
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 0), 120, new Color("6DCFB3"), MetadataBehavior.SPECIFIC));
		//cooked fish
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.cooked_fish, 1, 0), 120, new Color("D8EBE5"), MetadataBehavior.SPECIFIC));

		//salmon
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 1), 120, new Color("FF2E4A"), MetadataBehavior.SPECIFIC));
		//cooked salmon
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 1), 120, new Color("E87A3F"), MetadataBehavior.SPECIFIC));

		//clownfish
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 2), 120, new Color("FF771C"), MetadataBehavior.SPECIFIC));
		//blowfish
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 3), 120, new Color("DBFAFF"), MetadataBehavior.SPECIFIC));

		//apple
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.apple, 1), 100, new Color("FFF68F"), MetadataBehavior.IGNORED));
		//golden apple
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.golden_apple, 1), 200, new Color("CCCC00"), MetadataBehavior.IGNORED));
		//melon slice
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.melon, 1), 40, new Color("FF443B"), MetadataBehavior.IGNORED));
		//melon
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.melon_block), 1), 150, new Color("FF443B"), MetadataBehavior.IGNORED));
		//pumpkin
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.pumpkin), 1), 150, new Color("FFDB66"), MetadataBehavior.IGNORED));
		//jack o lantern
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.lit_pumpkin), 1), 150, new Color("FFDB66"), MetadataBehavior.IGNORED));
		//cactus
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.cactus), 1), 100, new Color("DEFFB5"), MetadataBehavior.IGNORED));

		//carrot
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.carrot, 1), 80, new Color("FF9B0F"), MetadataBehavior.IGNORED));
		//potato
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.potato, 1), 80, new Color("FFF1B5"), MetadataBehavior.IGNORED));
		//baked potato
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.baked_potato, 1), 80, new Color("FFF1B5"), MetadataBehavior.IGNORED));
		//poison potato
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.poisonous_potato, 1), 80, new Color("E0FF8A"), MetadataBehavior.IGNORED));

		//waterlily
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.waterlily), 1), 80, new Color("269900"), MetadataBehavior.IGNORED));
		//vine
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.vine), 1), 80, new Color("23630E"), MetadataBehavior.IGNORED));
		//tall grass
		addRecipe(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.tallgrass), 1), 80, new Color("23630E"), MetadataBehavior.IGNORED));
		//netherwart
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.nether_wart, 1), 80, new Color("FF2B52"), MetadataBehavior.IGNORED));
		//sugar cane
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.reeds, 1), 80, new Color("9BFF8A"), MetadataBehavior.IGNORED));
		//string
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.string, 1), 40, new Color("FFFFFF"), MetadataBehavior.IGNORED));
	}
}
