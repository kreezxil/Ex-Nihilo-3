package exnihilo2.items.hammers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemHammer extends Item{
	private final Item.ToolMaterial material;
	
	public ItemHammer(ToolMaterial material) {
		this.material = material;
		this.maxStackSize = 1;
		this.setMaxDamage((int)(material.getMaxUses()));
		this.setCreativeTab(CreativeTabs.tabTools);
	}
}
