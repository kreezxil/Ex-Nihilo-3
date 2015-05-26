package exnihilo2.blocks.sieves;

import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import exnihilo2.items.meshs.ISieveMesh;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (player == null)
		{
			return false;
		}

		TileEntitySieve sieve = (TileEntitySieve) world.getTileEntity(pos);
		ItemStack item = player.getCurrentEquippedItem();

		if (sieve != null)
		{
			if (item.getItem() instanceof ISieveMesh)
			{
				sieve.setMesh(item);
			}
			else
			{
				
			}
		}

		//Return true to keep buckets from pouring all over the damn place.
		return true;
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
