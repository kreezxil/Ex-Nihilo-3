package exnihilo2.blocks;

import exnihilo2.EN2;
import exnihilo2.barrels.tileentity.TileEntityBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockBarrel extends BlockContainer {

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (player == null)
		{
			return false;
		}

		TileEntityBarrel barrel = (TileEntityBarrel) world.getTileEntity(pos);
		ItemStack item = player.getCurrentEquippedItem();

		if (barrel.canExtractItem(0, barrel.getStackInSlot(0), EnumFacing.DOWN))
		{
			//extract
			if(!world.isRemote)
			{
				EntityItem entityitem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), barrel.getStackInSlot(0));

				double f3 = 0.05F;
				entityitem.motionX = world.rand.nextGaussian() * f3;
				entityitem.motionY = (0.2d);
				entityitem.motionZ = world.rand.nextGaussian() * f3;

				world.spawnEntityInWorld(entityitem);
				
				barrel.setInventorySlotContents(0, null);
			}
		}
		else if (item != null && barrel.canInsertItem(1, item, EnumFacing.UP))
		{
			//insert
			barrel.getState().useItem(barrel, item);
		}

		//Return true to keep buckets from pouring all over the damn place.
		return true;
	}

	public BlockBarrel(Material material) 
	{
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityBarrel();
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
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
}
