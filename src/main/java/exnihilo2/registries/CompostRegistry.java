package exnihilo2.registries;

import java.util.ArrayList;
import java.util.HashMap;

import exnihilo2.EN2;
import exnihilo2.registries.recipes.CompostRecipe;
import exnihilo2.util.Color;
import exnihilo2.util.enums.MetadataBehavior;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompostRegistry {
	private static HashMap<String, CompostRecipe> recipes = new HashMap<String, CompostRecipe>();
	
	public static void addRecipe(CompostRecipe recipe)
	{
		if (recipe != null)
		{
			String s = getRecipeKey(recipe);
			
			if (s != null && s.trim().length() > 0)
			{
				recipes.put(s, recipe);
			}
		}
	}
	
	public static void removeRecipe(ItemStack item)
	{
		CompostRecipe recipe = getRecipe(item);
		
		if (recipe != null)
		{
			recipes.remove(getRecipeKey(recipe));
		}
	}
	
	public static boolean isCompostable(ItemStack item)
	{
		return getRecipe(item) != null;
	}
	
	public static CompostRecipe getRecipe(ItemStack item)
	{
		CompostRecipe recipe = recipes.get(item.getUnlocalizedName() + ":*");
		
		if (recipe != null)
		{
			return recipe;
		}
		else
		{
			return recipes.get(item.getUnlocalizedName() + ":" + item.getMetadata());
		}
	}
	
	private static String getRecipeKey(CompostRecipe recipe)
	{
		String s = recipe.getInput().getUnlocalizedName();
		
		if (recipe.getMetadataBehavior() == MetadataBehavior.Ignored)
		{
			return s + ":*";
		}
		else if (recipe.getMetadataBehavior() == MetadataBehavior.Specified)
		{
			return s + ":" + recipe.getInput().getMetadata();
		}
		
		return null;
	}
	
	public static void addVanillaRecipes()
	{
		//saplings
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 0), 125, new Color("35A82A"), MetadataBehavior.Specified)); //oak
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 1), 125, new Color("2E8042"), MetadataBehavior.Specified)); //spruce
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 2), 125, new Color("6CC449"), MetadataBehavior.Specified)); //birch
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 3), 125, new Color("22A116"), MetadataBehavior.Specified)); //jungle
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 4), 125, new Color("B8C754"), MetadataBehavior.Specified)); //acacia
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 5), 125, new Color("378030"), MetadataBehavior.Specified)); //dark_oak

		//leaves
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 0), 125, new Color("35A82A"), MetadataBehavior.Specified)); //oak
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 1), 125, new Color("2E8042"), MetadataBehavior.Specified)); //spruce
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 2), 125, new Color("6CC449"), MetadataBehavior.Specified)); //birch
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 3), 125, new Color("22A116"), MetadataBehavior.Specified)); //jungle
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.leaves2), 1, 0), 125, new Color("B8C754"), MetadataBehavior.Specified)); //acacia
		addRecipe(new CompostRecipe(new ItemStack(Item.getItemFromBlock(Blocks.leaves2), 1, 1), 125, new Color("378030"), MetadataBehavior.Specified)); //dark_oak

		//rotten flesh
		addRecipe(new CompostRecipe(new ItemStack(Items.ghast_tear, 1), 5, new Color("FFFFFF"), MetadataBehavior.Ignored));
		addRecipe(new CompostRecipe(new ItemStack(Items.rotten_flesh, 1), 100, new Color("C45631"), MetadataBehavior.Ignored));
		
		addRecipe(new CompostRecipe(new ItemStack(Items.golden_apple, 1), 250, new Color("CCCC00"), MetadataBehavior.Specified));
		addRecipe(new CompostRecipe(new ItemStack(Items.golden_apple, 1, 1), 1000, new Color("CCCC00"), MetadataBehavior.Specified));
		
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
