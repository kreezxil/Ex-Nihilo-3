package exnihilo2.registries.recipes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import exnihilo2.util.Color;
import exnihilo2.util.enums.MetadataBehavior;

public class CompostRecipe {
	private Color color;
	private ItemStack input; 
	private int volume;
	private MetadataBehavior behavior = MetadataBehavior.SPECIFIC;
	
	public CompostRecipe(ItemStack input, int volume, Color color)
	{
		this.input = input;
		this.color = color;
		this.volume = volume;
	}
	
	public CompostRecipe(ItemStack input, int amount, Color color, MetadataBehavior meta)
	{
		this(input, amount, color);
		
		this.behavior = meta;
	}
	
	public Color getColor()
	{
		if (this.color != null)
			return this.color;
		else
			return new Color(1f, 1f, 1f, 1f); //White by default?
	}
	
	public ItemStack getInput()
	{
		return input;
	}
	
	public int getVolume()
	{
		if (this.volume > 1000)
			return 1000;
		
		if (this.volume < 0)
			return 0;
		
		return this.volume;
	}
	
	public MetadataBehavior getMetadataBehavior()
	{
		return this.behavior;
	}
}


