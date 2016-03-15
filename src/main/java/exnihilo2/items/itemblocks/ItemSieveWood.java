package exnihilo2.items.itemblocks;

import exnihilo2.util.enums.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemSieveWood extends ItemBlock{
	public ItemSieveWood(Block block) {
		super(block);
		
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
	    return super.getUnlocalizedName() + "_" + EnumWood.fromMetadata(stack.getItemDamage()).getName();
	}
	
	@Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
