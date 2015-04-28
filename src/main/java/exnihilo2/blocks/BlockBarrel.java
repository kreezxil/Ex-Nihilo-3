package exnihilo2.blocks;

import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBarrel extends BlockContainer 
{
	public BlockBarrel(Material material)
	{
		super(material);
		
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos) {
		TileEntityBarrel barrel = (TileEntityBarrel) world.getTileEntity(pos);

		if (barrel != null)
		{
			return barrel.getLuminosity();
		}
		
		return 0;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (player == null)
		{
			return false;
		}

		TileEntityBarrel barrel = (TileEntityBarrel) world.getTileEntity(pos);
		ItemStack item = player.getCurrentEquippedItem();

		if (barrel != null)
		{
			if (barrel.getState().canUseItem(barrel, item))
			{
				barrel.getState().useItem(player, barrel, item);
			}
			else if (barrel.canExtractItem(0, barrel.getStackInSlot(0), EnumFacing.DOWN))
			{
				if(!world.isRemote)
				{
					EntityItem entityitem = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), barrel.getStackInSlot(0));

					double f3 = 0.05F;
					entityitem.motionX = world.rand.nextGaussian() * f3;
					entityitem.motionY = (0.2d);
					entityitem.motionZ = world.rand.nextGaussian() * f3;

					world.spawnEntityInWorld(entityitem);
				}
				
				barrel.setInventorySlotContents(0, null);
			}
		}

		//Return true to keep buckets from pouring all over the damn place.
		return true;
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
	public EnumWorldBlockLayer getBlockLayer()
	{
		if (this.getMaterial().isOpaque())
		{
			return EnumWorldBlockLayer.SOLID;
		}
		else
		{
			return EnumWorldBlockLayer.CUTOUT_MIPPED;
		}
	}
	
	@Override
	public int getRenderType()
    {
        return 3;
    }
	
	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		TileEntityBarrel barrel = (TileEntityBarrel) world.getTileEntity(pos);
		barrel.setLuminosity(0);
		
		return super.removedByPlayer(world, pos, player, willHarvest);
	}
	
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
    {
		TileEntityBarrel barrel = (TileEntityBarrel) world.getTileEntity(pos);
		barrel.setLuminosity(0);
		
		super.onBlockExploded(world, pos, explosion);
    }
}
