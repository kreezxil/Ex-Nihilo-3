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
		addVanillaRecipes();
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
					recipes.remove(s);
				}
			}
		}
	}
	
	public static void removeRecipe(ItemStack item)
	{
		CompostRegistryEntry recipe = getRecipe(item);
		
		if (recipe != null)
		{
			recipes.remove(getRecipeKey(recipe));
		}
	}
	
	public static boolean isCompostable(ItemStack item)
	{
		return getRecipe(item) != null;
	}
	
	public static CompostRegistryEntry getRecipe(ItemStack item)
	{
		CompostRegistryEntry recipe = recipes.get(GameRegistry.findUniqueIdentifierFor(item.getItem())  + ":*");
		
		if (recipe != null)
		{
			return recipe;
		}
		else
		{
			return recipes.get(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":" + item.getMetadata());
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
	
	public static void addVanillaRecipes()
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

		//rotten flesh
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.ghast_tear, 1), 5, new Color("FFFFFF"), MetadataBehavior.IGNORED));
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.rotten_flesh, 1), 100, new Color("C45631"), MetadataBehavior.IGNORED));
		
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.golden_apple, 1), 250, new Color("CCCC00"), MetadataBehavior.SPECIFIC));
		addRecipe(new CompostRegistryEntry(new ItemStack(Items.golden_apple, 1, 1), 1000, new Color("CCCC00"), MetadataBehavior.SPECIFIC));
		
//		register(Items.rotten_flesh, 0, 0.10f, ColorRegistry.color("rotten_flesh"));
//		//spider eye
//		register(Items.spider_eye, 0, 0.08f, ColorRegistry.color("spider_eye"));
//		
//		//wheat
//		register(Items.wheat, 0, 0.08f, ColorRegistry.color("wheat"));
//		//bread
//		register(Items.bread, 0, 0.16f, ColorRegistry.color("bread"));
//		
//		//dandelion
//		register(Item.getItemFromBlock(Blocks.yellow_flower), 0, 0.10f, ColorRegistry.color("dandelion"));
//		//poppy
//		register(Item.getItemFromBlock(Blocks.red_flower), 0, 0.10f, ColorRegistry.color("poppy"));
//		//blue orchid
//		register(Item.getItemFromBlock(Blocks.red_flower), 1, 0.10f, ColorRegistry.color("blue_orchid"));
//		//allium
//		register(Item.getItemFromBlock(Blocks.red_flower), 2, 0.10f, ColorRegistry.color("allium"));
//		//azure bluet
//		register(Item.getItemFromBlock(Blocks.red_flower), 3, 0.10f, ColorRegistry.color("azure_bluet"));
//		//red_tulip
//		register(Item.getItemFromBlock(Blocks.red_flower), 4, 0.10f, ColorRegistry.color("red_tulip"));
//		//orange tulip
//		register(Item.getItemFromBlock(Blocks.red_flower), 5, 0.10f, ColorRegistry.color("orange_tulip"));
//		//white tulip
//		register(Item.getItemFromBlock(Blocks.red_flower), 6, 0.10f, ColorRegistry.color("white_tulip"));
//		//pink tulip
//		register(Item.getItemFromBlock(Blocks.red_flower), 7, 0.10f, ColorRegistry.color("pink_tulip"));
//		//oxeye daisy
//		register(Item.getItemFromBlock(Blocks.red_flower), 8, 0.10f, ColorRegistry.color("oxeye_daisy"));
//		
//		//sunflower
//		register(Item.getItemFromBlock(Blocks.double_plant), 0, 0.10f, ColorRegistry.color("sunflower"));
//		//lilac
//		register(Item.getItemFromBlock(Blocks.double_plant), 1, 0.10f, ColorRegistry.color("lilac"));
//		//rose buse
//		register(Item.getItemFromBlock(Blocks.double_plant), 4, 0.10f, ColorRegistry.color("rose"));
//		//peony
//		register(Item.getItemFromBlock(Blocks.double_plant), 5, 0.10f, ColorRegistry.color("peony"));
//		
//		//mushroom_brown
//		register(Item.getItemFromBlock(Blocks.brown_mushroom), 0, 0.10f, ColorRegistry.color("mushroom_brown"));
//		//mushroom_red
//		register(Item.getItemFromBlock(Blocks.red_mushroom), 0, 0.10f, ColorRegistry.color("mushroom_red"));
//		
//		//pumpkin pie
//		register(Items.pumpkin_pie, 0, 0.16f, ColorRegistry.color("pumpkin_pie"));
//		
//		//pork
//		register(Items.porkchop, 0, 0.2f, ColorRegistry.color("pork_raw"));
//		//cooked pork
//		register(Items.cooked_porkchop, 0, 0.2f, ColorRegistry.color("pork_cooked"));
//		
//		//beef
//		register(Items.beef, 0, 0.2f, ColorRegistry.color("beef_raw"));
//		//cooked beef
//		register(Items.cooked_beef, 0, 0.2f, ColorRegistry.color("beef_cooked"));
//		
//		//chicken
//		register(Items.chicken, 0, 0.2f, ColorRegistry.color("chicken_raw"));
//		//cooked chicken
//		register(Items.cooked_chicken, 0, 0.2f, ColorRegistry.color("chicken_cooked"));
//		
//		//fish
//		register(Items.fish, 0, 0.15f, ColorRegistry.color("fish_raw"));
//		//cooked fish
//		register(Items.cooked_fished, 0, 0.15f, ColorRegistry.color("fish_cooked"));
//		
//		//salmon
//		register(Items.fish, 1, 0.15f, ColorRegistry.color("salmon_raw"));
//		//cooked salmon
//		register(Items.cooked_fished, 1, 0.15f, ColorRegistry.color("salmon_cooked"));
//		
//		//clownfish
//		register(Items.fish, 2, 0.15f, ColorRegistry.color("clownfish"));
//		//blowfish
//		register(Items.fish, 3, 0.15f, ColorRegistry.color("pufferfish"));
//		
//		//cooked silkworms
//		register(ENItems.Silkworm, 0, 0.04f, ColorRegistry.color("silkworm_raw"));
//		//cooked silkworms
//		register(ENItems.SilkwormCooked, 0, 0.04f, ColorRegistry.color("silkworm_cooked"));
//		
//		//apple
//		register(Items.apple, 0, 0.10f, ColorRegistry.color("apple"));
//		//melon slice
//		register(Items.melon, 0, 0.04f, ColorRegistry.color("melon"));
//		//melon
//		register(Item.getItemFromBlock(Blocks.melon_block), 0, 1.0f / 6, ColorRegistry.color("melon"));
//		//pumpkin
//		register(Item.getItemFromBlock(Blocks.pumpkin), 0, 1.0f / 6, ColorRegistry.color("pumpkin"));
//		//jack o lantern
//		register(Item.getItemFromBlock(Blocks.lit_pumpkin), 0, 1.0f / 6, ColorRegistry.color("pumpkin"));
//		//cactus
//		register(Item.getItemFromBlock(Blocks.cactus), 0, 0.10f, ColorRegistry.color("cactus"));
//		
//		//carrot
//		register(Items.carrot, 0, 0.08f, ColorRegistry.color("carrot"));
//		//potato
//		register(Items.potato, 0, 0.08f, ColorRegistry.color("potato"));
//		//baked potato
//		register(Items.baked_potato, 0, 0.08f, ColorRegistry.color("potato_baked"));
//		//poison potato
//		register(Items.poisonous_potato, 0, 0.08f, ColorRegistry.color("potato_poison"));
//		
//		//waterlily
//		register(Item.getItemFromBlock(Blocks.waterlily), 0, 0.10f, ColorRegistry.color("waterlily"));
//		//vine
//		register(Item.getItemFromBlock(Blocks.vine), 0, 0.10f, ColorRegistry.color("vine"));
//		//tall grass
//		register(Item.getItemFromBlock(Blocks.tallgrass), 1, 0.08f, ColorRegistry.color("tall_grass"));
//		//egg
//		register(Items.egg, 0, 0.08f, ColorRegistry.color("egg"));
//		//netherwart
//		register(Items.nether_wart, 0, 0.10f, ColorRegistry.color("netherwart"));
//		//sugar cane
//		register(Items.reeds, 0, 0.08f, ColorRegistry.color("sugar_cane"));
//		//string
//		register(Items.string, 0, 0.04f, ColorRegistry.color("white"));
	}
}