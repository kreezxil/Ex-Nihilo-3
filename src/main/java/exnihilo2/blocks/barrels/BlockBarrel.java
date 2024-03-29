package exnihilo2.blocks.barrels;

import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.states.BarrelStates;
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
		this.setHardness(1.2f);
		this.setBlockBounds(0.06f, 0.0f, 0.06f, 0.94f, 1.0f, 0.94f);
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
			if (item != null && barrel.getState().canUseItem(barrel, item))
			{
				barrel.getState().useItem(player, barrel, item);
			}
			else if (barrel.canExtractItem(0, barrel.getStackInSlot(0), EnumFacing.DOWN))
			{
				if(!world.isRemote)
				{
					EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, barrel.getStackInSlot(0));

					double f3 = 0.05F;
					entityitem.motionX = world.rand.nextGaussian() * f3;
					entityitem.motionY = (0.2d);
					entityitem.motionZ = world.rand.nextGaussian() * f3;
					entityitem.setDefaultPickupDelay();

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
		TileEntityBarrel barrel = new TileEntityBarrel();
		barrel.setState(BarrelStates.empty);
		
		return barrel;
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
			return EnumWorldBlockLayer.TRANSLUCENT;
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
	
	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
    {
		TileEntityBarrel barrel = (TileEntityBarrel) world.getTileEntity(pos);
		barrel.setLuminosity(0);
		
		super.onBlockExploded(world, pos, explosion);
    }
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		//Barrels should never break with the wrong tool.
		return true;
	}
}
