package exnihilo2.blocks.sieves;

import java.util.List;

import exnihilo2.util.enums.EnumWood;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSieveWood extends BlockSieve{
	public static final PropertyEnum WOOD = PropertyEnum.create("wood", EnumWood.class);
	
	public BlockSieveWood(Material material) {
		super(material);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(WOOD, EnumWood.OAK));
	}

	@Override
	public int damageDropped(IBlockState state)
    {
        return ((EnumWood)state.getValue(WOOD)).getMetadata();
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
		EnumWood[] woods = EnumWood.values();

        for (int j = 0; j < woods.length; ++j)
        {
        	EnumWood wood = woods[j];
            list.add(new ItemStack(itemIn, 1, wood.getMetadata()));
        }
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(WOOD, EnumWood.fromMetadata(meta));
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumWood)state.getValue(WOOD)).getMetadata();
    }

	@Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {WOOD});
    }
}
