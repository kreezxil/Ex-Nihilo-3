package exnihilo2.blocks.sieves;

import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSieve extends BlockContainer {

	public BlockSieve(Material material) 
	{
		super(material);
		
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(1.0f);
		this.setBlockBounds(0.0f, 0.5f, 0.0f, 1.0f, 1.0f, 1.0f);
	}

	@Override
	public boolean isFullCube() 
	{ 
		return false; 
	}

	@Override
	public boolean isOpaqueCube() 
	{
		return false;
	}
	
	@Override
	public int getRenderType()
    {
        return 3;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntitySieve();
	}
}
