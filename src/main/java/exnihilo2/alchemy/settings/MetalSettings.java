package exnihilo2.alchemy.settings;

import exnihilo2.util.helpers.GameRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MetalSettings {
	private static String name;
	private static Block ore;
	private static ItemStack crystal;
	private static int value;
	
	public MetalSettings(String name)
	{
		this.name = name;
	}
	
	public void load(Configuration config)
	{
		String category = this.name + " settings";
		
		//LOAD ORE BLOCK;
		String block_name_default = "";
		if (this.ore != null)
		{
			block_name_default = GameRegistry.findUniqueIdentifierFor(ore).toString();
		}
		
		String block_name = config.get(category, "ore", block_name_default).getString();
		
		if (block_name != block_name_default)
		{
			Block b = GameRegistryHelper.findBlock(block_name);
			
			if (b != null)
				this.ore = b;
		}
		
		//LOAD VALUE
		this.value = config.get(category, "value", value).getInt(value);
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		MetalSettings.name = name;
	}

	public static Block getOre() {
		return ore;
	}

	public static void setOre(Block ore) {
		MetalSettings.ore = ore;
	}

	public static ItemStack getCrystal() {
		return crystal;
	}

	public static void setCrystal(ItemStack crystal) {
		MetalSettings.crystal = crystal;
	}

	public static int getValue() {
		return value;
	}

	public static void setValue(int value) {
		MetalSettings.value = value;
	}
}
