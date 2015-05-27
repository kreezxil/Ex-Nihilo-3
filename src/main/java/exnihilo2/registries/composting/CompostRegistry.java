package exnihilo2.registries.composting;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exnihilo2.EN2;
import exnihilo2.registries.EN2Registries;
import exnihilo2.registries.composting.files.CompostRecipeLoader;
import exnihilo2.util.Color;
import exnihilo2.util.enums.EnumMetadataBehavior;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CompostRegistry {
	private static HashMap<String, CompostRegistryEntry> entries;
	
	public static void initialize()
	{
		entries = new HashMap<String, CompostRegistryEntry>();
		
		if (EN2Registries.loadCompostDefaults)
			registerVanillaRecipes();
		
		List<CompostRegistryEntry> loaded = CompostRecipeLoader.load(EN2.path + File.separator + "recipes" + File.separator + "compost" + File.separator);
	
		if (loaded != null && !loaded.isEmpty())
		{
			for (CompostRegistryEntry entry : loaded)
			{
				if (entry.getVolume() > 0)
				{
					add(entry);
				}
				else
				{
					remove(entry);
				}
			}
		}
	}
	
	public static void add(CompostRegistryEntry entry)
	{
		if (entry != null)
		{
			entries.put(entry.getKey(), entry);
		}
	}
	
	public static void remove(CompostRegistryEntry entry)
	{
		entries.remove(entry.getKey());
	}
	
	public static boolean isCompostable(ItemStack item)
	{
		return getEntryForItemStack(item) != null;
	}
	
	public static CompostRegistryEntry getEntryForItemStack(ItemStack item)
	{
		CompostRegistryEntry entry = entries.get(GameRegistry.findUniqueIdentifierFor(item.getItem()) + ":" + item.getMetadata());
		
		if (entry != null)
		{
			return entry;
		}
		else
		{
			return entries.get(GameRegistry.findUniqueIdentifierFor(item.getItem())  + ":*");
		}
	}
	
	public static void registerVanillaRecipes()
	{
		//saplings
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 0), 125, new Color("35A82A"), EnumMetadataBehavior.SPECIFIC)); //oak
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 1), 125, new Color("2E8042"), EnumMetadataBehavior.SPECIFIC)); //spruce
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 2), 125, new Color("6CC449"), EnumMetadataBehavior.SPECIFIC)); //birch
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 3), 125, new Color("22A116"), EnumMetadataBehavior.SPECIFIC)); //jungle
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 4), 125, new Color("B8C754"), EnumMetadataBehavior.SPECIFIC)); //acacia
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 5), 125, new Color("378030"), EnumMetadataBehavior.SPECIFIC)); //dark_oak

		//leaves
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 0), 125, new Color("35A82A"), EnumMetadataBehavior.SPECIFIC)); //oak
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 1), 125, new Color("2E8042"), EnumMetadataBehavior.SPECIFIC)); //spruce
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 2), 125, new Color("6CC449"), EnumMetadataBehavior.SPECIFIC)); //birch
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves), 1, 3), 125, new Color("22A116"), EnumMetadataBehavior.SPECIFIC)); //jungle
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves2), 1, 0), 125, new Color("B8C754"), EnumMetadataBehavior.SPECIFIC)); //acacia
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.leaves2), 1, 1), 125, new Color("378030"), EnumMetadataBehavior.SPECIFIC)); //dark_oak

		//ghast tear
		add(new CompostRegistryEntry(new ItemStack(Items.ghast_tear, 1), 5, new Color("FFFFFF"), EnumMetadataBehavior.IGNORED));
		//rotten flesh
		add(new CompostRegistryEntry(new ItemStack(Items.rotten_flesh, 1), 85, new Color("C45631"), EnumMetadataBehavior.IGNORED));
		//spider eye
		add(new CompostRegistryEntry(new ItemStack(Items.spider_eye, 1), 85, new Color("963E44"), EnumMetadataBehavior.IGNORED));
		//spider eye fermented
		add(new CompostRegistryEntry(new ItemStack(Items.fermented_spider_eye, 1), 85, new Color("963E44"), EnumMetadataBehavior.IGNORED));
		
		//dandelion
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.yellow_flower), 1), 100, new Color("FFF461"), EnumMetadataBehavior.SPECIFIC));
		//poppy
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 0), 100, new Color("FF1212"), EnumMetadataBehavior.SPECIFIC));
		//blue orchid
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 1), 100, new Color("33CFFF"), EnumMetadataBehavior.SPECIFIC));
		//allium
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 2), 100, new Color("F59DFA"), EnumMetadataBehavior.SPECIFIC));
		//azure bluet
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 3), 100, new Color("E3E3E3"), EnumMetadataBehavior.SPECIFIC));
		//red_tulip
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 4), 100, new Color("FF3D12"), EnumMetadataBehavior.SPECIFIC));
		//orange tulip
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 5), 100, new Color("FF7E29"), EnumMetadataBehavior.SPECIFIC));
		//white tulip
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 6), 100, new Color("FFFFFF"), EnumMetadataBehavior.SPECIFIC));
		//pink tulip
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 7), 100, new Color("F5C4FF"), EnumMetadataBehavior.SPECIFIC));
		//oxeye daisy
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_flower), 1, 8), 100, new Color("E9E9E9"), EnumMetadataBehavior.SPECIFIC));

		//sunflower
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 0), 100, new Color("FFDD00"), EnumMetadataBehavior.SPECIFIC));
		//lilac
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 1), 100, new Color("FCC7F0"), EnumMetadataBehavior.SPECIFIC));
		//tall grass
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 2), 100, new Color("23630E"), EnumMetadataBehavior.SPECIFIC));
		//large fern
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 3), 100, new Color("23630E"), EnumMetadataBehavior.SPECIFIC));
		//rose bush
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 4), 100, new Color("FF1212"), EnumMetadataBehavior.SPECIFIC));
		//peony
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.double_plant), 1, 5), 100, new Color("F3D2FC"), EnumMetadataBehavior.SPECIFIC));

		//mushroom_brown
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.brown_mushroom), 1), 100, new Color("CFBFB6"), EnumMetadataBehavior.IGNORED));
		//mushroom_red
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.red_mushroom), 1), 100, new Color("D6A8A5"), EnumMetadataBehavior.IGNORED));
		
		//wheat
		add(new CompostRegistryEntry(new ItemStack(Items.wheat, 1), 85, new Color("E3E162"), EnumMetadataBehavior.IGNORED));
		//bread
		add(new CompostRegistryEntry(new ItemStack(Items.bread, 1), 125, new Color("D1AF60"), EnumMetadataBehavior.IGNORED));

		//pumpkin pie
		add(new CompostRegistryEntry(new ItemStack(Items.pumpkin_pie, 1), 175, new Color("E39A6D"), EnumMetadataBehavior.IGNORED));
		//egg
		add(new CompostRegistryEntry(new ItemStack(Items.egg, 1), 80, new Color("FFFA66"), EnumMetadataBehavior.IGNORED));

		//pork
		add(new CompostRegistryEntry(new ItemStack(Items.porkchop, 1), 175, new Color("FFA091"), EnumMetadataBehavior.IGNORED));
		//cooked pork
		add(new CompostRegistryEntry(new ItemStack(Items.cooked_porkchop, 1), 175, new Color("FFFDBD"), EnumMetadataBehavior.IGNORED));
	
		//beef
		add(new CompostRegistryEntry(new ItemStack(Items.beef, 1), 175, new Color("FF4242"), EnumMetadataBehavior.IGNORED));
		//cooked beef
		add(new CompostRegistryEntry(new ItemStack(Items.cooked_beef, 1), 175, new Color("80543D"), EnumMetadataBehavior.IGNORED));

		//mutton
		add(new CompostRegistryEntry(new ItemStack(Items.mutton, 1), 175, new Color("FF4242"), EnumMetadataBehavior.IGNORED));
		//cooked mutton
		add(new CompostRegistryEntry(new ItemStack(Items.cooked_mutton, 1), 175, new Color("80543D"), EnumMetadataBehavior.IGNORED));

		//chicken
		add(new CompostRegistryEntry(new ItemStack(Items.chicken, 1), 175, new Color("FFE8E8"), EnumMetadataBehavior.IGNORED));
		//cooked chicken
		add(new CompostRegistryEntry(new ItemStack(Items.cooked_chicken, 1), 175, new Color("FA955F"), EnumMetadataBehavior.IGNORED));

		//rabbit
		add(new CompostRegistryEntry(new ItemStack(Items.rabbit, 1), 175, new Color("FFE8E8"), EnumMetadataBehavior.IGNORED));
		//cooked rabbit
		add(new CompostRegistryEntry(new ItemStack(Items.cooked_rabbit, 1), 175, new Color("FA955F"), EnumMetadataBehavior.IGNORED));

		//fish
		add(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 0), 120, new Color("6DCFB3"), EnumMetadataBehavior.SPECIFIC));
		//cooked fish
		add(new CompostRegistryEntry(new ItemStack(Items.cooked_fish, 1, 0), 120, new Color("D8EBE5"), EnumMetadataBehavior.SPECIFIC));

		//salmon
		add(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 1), 120, new Color("FF2E4A"), EnumMetadataBehavior.SPECIFIC));
		//cooked salmon
		add(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 1), 120, new Color("E87A3F"), EnumMetadataBehavior.SPECIFIC));

		//clownfish
		add(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 2), 120, new Color("FF771C"), EnumMetadataBehavior.SPECIFIC));
		//blowfish
		add(new CompostRegistryEntry(new ItemStack(Items.fish, 1, 3), 120, new Color("DBFAFF"), EnumMetadataBehavior.SPECIFIC));

		//apple
		add(new CompostRegistryEntry(new ItemStack(Items.apple, 1), 100, new Color("FFF68F"), EnumMetadataBehavior.IGNORED));
		//golden apple
		add(new CompostRegistryEntry(new ItemStack(Items.golden_apple, 1), 200, new Color("CCCC00"), EnumMetadataBehavior.IGNORED));
		//melon slice
		add(new CompostRegistryEntry(new ItemStack(Items.melon, 1), 40, new Color("FF443B"), EnumMetadataBehavior.IGNORED));
		//melon
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.melon_block), 1), 150, new Color("FF443B"), EnumMetadataBehavior.IGNORED));
		//pumpkin
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.pumpkin), 1), 150, new Color("FFDB66"), EnumMetadataBehavior.IGNORED));
		//jack o lantern
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.lit_pumpkin), 1), 150, new Color("FFDB66"), EnumMetadataBehavior.IGNORED));
		//cactus
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.cactus), 1), 100, new Color("DEFFB5"), EnumMetadataBehavior.IGNORED));

		//carrot
		add(new CompostRegistryEntry(new ItemStack(Items.carrot, 1), 80, new Color("FF9B0F"), EnumMetadataBehavior.IGNORED));
		//potato
		add(new CompostRegistryEntry(new ItemStack(Items.potato, 1), 80, new Color("FFF1B5"), EnumMetadataBehavior.IGNORED));
		//baked potato
		add(new CompostRegistryEntry(new ItemStack(Items.baked_potato, 1), 80, new Color("FFF1B5"), EnumMetadataBehavior.IGNORED));
		//poison potato
		add(new CompostRegistryEntry(new ItemStack(Items.poisonous_potato, 1), 80, new Color("E0FF8A"), EnumMetadataBehavior.IGNORED));

		//waterlily
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.waterlily), 1), 80, new Color("269900"), EnumMetadataBehavior.IGNORED));
		//vine
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.vine), 1), 80, new Color("23630E"), EnumMetadataBehavior.IGNORED));
		//tall grass
		add(new CompostRegistryEntry(new ItemStack(Item.getItemFromBlock(Blocks.tallgrass), 1), 80, new Color("23630E"), EnumMetadataBehavior.IGNORED));
		//netherwart
		add(new CompostRegistryEntry(new ItemStack(Items.nether_wart, 1), 80, new Color("FF2B52"), EnumMetadataBehavior.IGNORED));
		//sugar cane
		add(new CompostRegistryEntry(new ItemStack(Items.reeds, 1), 80, new Color("9BFF8A"), EnumMetadataBehavior.IGNORED));
		//string
		add(new CompostRegistryEntry(new ItemStack(Items.string, 1), 40, new Color("FFFFFF"), EnumMetadataBehavior.IGNORED));
	}
}
