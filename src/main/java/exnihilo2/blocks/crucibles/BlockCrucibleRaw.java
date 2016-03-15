package exnihilo2.blocks.crucibles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumWorldBlockLayer;

public class BlockCrucibleRaw extends Block{

	public BlockCrucibleRaw()
	{
		super(Material.clay);
		
		this.setHardness(0.6f);
		this.setHarvestLevel("shovel", 0);
		this.setCreativeTab(CreativeTabs.tabDecorations);
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
}
