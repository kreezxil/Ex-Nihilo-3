package exnihilo2.alchemy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exnihilo2.EN2;
import exnihilo2.alchemy.settings.MetalSettings;
import exnihilo2.items.EN2Items;
import exnihilo2.util.enums.EnumOre;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class EN2Alchemy {
	public static HashMap<String, MetalSettings> metals = new HashMap<String, MetalSettings>();
	public static MetalSettings iron_settings;
	public static MetalSettings gold_settings;
	
	public static void configure()
	{
		Configuration config = new Configuration(new File(EN2.path + File.separator + "alchemy" + File.separator + "alchemy.cfg"));
	
		iron_settings = new MetalSettings("iron");
		iron_settings.setOre(Blocks.iron_ore);
		iron_settings.setCrystal(new ItemStack(EN2Items.metal_crystals, EnumOre.IRON.getMetadata()));
		iron_settings.setValue(2);
		iron_settings.load(config);
		registerMetal("ingotIron", iron_settings);
		
		gold_settings = new MetalSettings("gold");
		gold_settings.setOre(Blocks.gold_ore);
		gold_settings.setCrystal(new ItemStack(EN2Items.metal_crystals, EnumOre.GOLD.getMetadata()));
		gold_settings.setValue(4);
		gold_settings.load(config);
		registerMetal("ingotGold", gold_settings);
		
		if (config.hasChanged())
			config.save();
	}
	
	public static void loadFromOreDictionary()
	{
		//TODO search the metals map for anything null, try to find a match in the ore dictionary for that ore.
	}
	
	public static void registerMetal(String oreDictionaryName, MetalSettings settings)
	{
		metals.put(oreDictionaryName, settings);
	}
	
	public static int getMetalValue(ItemStack stack)
	{
		int[] ids = OreDictionary.getOreIDs(stack);
		int value = 0;
		
		for (int id : ids)
		{
			String name = OreDictionary.getOreName(id);

			if (metals.containsKey(name))
			{
				int v = metals.get(name).getValue();
				if (value < v)
					value = v;
			}
		}
		
		return value;
	}
}
