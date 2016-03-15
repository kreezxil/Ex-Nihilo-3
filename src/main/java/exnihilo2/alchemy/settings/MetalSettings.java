package exnihilo2.alchemy.settings;

import exnihilo2.util.helpers.GameRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MetalSettings {
	private String name;
	private Block ore;
	private ItemStack crystal;
	private int value;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Block getOre() {
		return ore;
	}

	public void setOre(Block ore) {
		this.ore = ore;
	}

	public ItemStack getCrystal() {
		return crystal;
	}

	public void setCrystal(ItemStack crystal) {
		this.crystal = crystal;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
