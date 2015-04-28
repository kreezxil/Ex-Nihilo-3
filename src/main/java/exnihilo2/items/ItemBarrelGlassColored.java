package exnihilo2.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBarrelGlassColored extends ItemBlock{
	private static String[] names = {"white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "lightgray", "cyan", "purple", "blue", "brown", "green", "red", "black"};

	public ItemBarrelGlassColored(Block block) {
		super(block);
		
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
	    return super.getUnlocalizedName() + "_" + names[stack.getItemDamage()];
	}
	
	@Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
