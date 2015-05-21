package exnihilo2.registries.composting;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import exnihilo2.EN2;
import exnihilo2.registries.composting.pojos.CompostRecipe;
import exnihilo2.util.Color;
import exnihilo2.util.enums.EnumMetadataBehavior;
import exnihilo2.util.helpers.GameRegistryHelper;

public class CompostRegistryEntry {
	private static final Color DEFAULT_COLOR = new Color(1f, 1f, 1f, 1f);
	
	private Color color;
	private ItemStack input; 
	private int value;
	private EnumMetadataBehavior behavior = EnumMetadataBehavior.SPECIFIC;
	
	public CompostRegistryEntry(ItemStack input, int value, Color color)
	{
		this.input = input;
		this.color = color;
		this.value = value;
	}
	
	public CompostRegistryEntry(ItemStack input, int value, Color color, EnumMetadataBehavior behavior)
	{
		this(input, value, color);
		
		this.behavior = behavior;
	}
	
	public static CompostRegistryEntry fromRecipe(CompostRecipe recipe)
	{
		Item item = GameRegistryHelper.findItem(recipe.getId());
		Color color = new Color(recipe.getColor());
		
		if (item != null)
		{
			ItemStack input = new ItemStack(item, 1, recipe.getMeta()); 
			return new CompostRegistryEntry(input, recipe.getValue(), color, recipe.getBehavior());
		}
		else
		{
			return null;
		}
	}
	
	public Color getColor()
	{
		if (this.color != null)
			return this.color;
		else
			return DEFAULT_COLOR;
	}
	
	public ItemStack getInput()
	{
		return input;
	}
	
	public int getVolume()
	{
		if (this.value > 1000)
			return 1000;
		
		if (this.value < 0)
			return 0;
		
		return this.value;
	}
	
	public EnumMetadataBehavior getMetadataBehavior()
	{
		return this.behavior;
	}

	public String getKey() {
		String s = GameRegistry.findUniqueIdentifierFor(input.getItem()).toString();
		
		if (behavior == EnumMetadataBehavior.IGNORED)
		{
			return s + ":*";
		}
		else 
		{
			return s + ":" + input.getMetadata();
		}
	}
}


