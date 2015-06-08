package exnihilo2.items.ores;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import exnihilo2.util.enums.EnumOre;

public class ItemOreSalts extends Item{
	public ItemOreSalts()
	{
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List list)
    {
        EnumOre[] ores = EnumOre.values();

        for (int j = 0; j < ores.length; ++j)
        {
        	EnumOre ore = ores[j];
            list.add(new ItemStack(itemIn, 1, ore.getMetadata()));
        }
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
	    return super.getUnlocalizedName() + "_" + EnumOre.fromMetadata(stack.getItemDamage()).getName();
	}
	
	@Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
