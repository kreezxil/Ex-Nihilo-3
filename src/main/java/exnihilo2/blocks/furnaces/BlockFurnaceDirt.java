package exnihilo2.blocks.furnaces;

import java.util.Random;

import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.furnaces.tileentity.TileEntityFurnaceDirt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//Most of this code was taken directly from vanilla and modified.
//I'll clean it up sometime. It's really messy, probably from the decompilation process.
//At least I got it down to one single block instead of two.
public class BlockFurnaceDirt extends BlockContainer{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool BURNING = PropertyBool.create("burning");

	public BlockFurnaceDirt()
	{
		super(Material.ground);
		this.setHardness(2.0f);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(EN2Blocks.furnace_dirt);
	}

	@Override 
	public String getHarvestTool(IBlockState state)
	{
		return null;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			Block block = worldIn.getBlockState(pos.north()).getBlock();
			Block block1 = worldIn.getBlockState(pos.south()).getBlock();
			Block block2 = worldIn.getBlockState(pos.west()).getBlock();
			Block block3 = worldIn.getBlockState(pos.east()).getBlock();
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
			{
				enumfacing = EnumFacing.SOUTH;
			}
			else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
			{
				enumfacing = EnumFacing.NORTH;
			}
			else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
			{
				enumfacing = EnumFacing.EAST;
			}
			else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
			{
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if ((Boolean) state.getValue(BURNING))
		{
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = (double)pos.getZ() + 0.5D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;

			switch (BlockFurnaceDirt.SwitchEnumFacing.FACING_LOOKUP[enumfacing.ordinal()])
			{
			case 1:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case 2:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case 3:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case 4:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}
		else
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityFurnaceDirt)
			{
				playerIn.displayGUIChest((TileEntityFurnaceDirt)tileentity);
			}

			return true;
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState preState = worldIn.getBlockState(pos);
		IBlockState postState = EN2Blocks.furnace_dirt.getDefaultState().withProperty(FACING, preState.getValue(FACING)).withProperty(BURNING, active);

		TileEntityFurnaceDirt tileentity = (TileEntityFurnaceDirt)worldIn.getTileEntity(pos);

		worldIn.setBlockState(pos, postState, 3);
		
		if (tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityFurnaceDirt();
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityFurnaceDirt)
		{
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFurnaceDirt)tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}
		
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World worldIn, BlockPos pos)
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos)
	{
		return Item.getItemFromBlock(Blocks.furnace);
	}

	@Override
	public int getRenderType()
	{
		return 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.getHorizontal(meta & 0xf);

		if (facing.getAxis() == EnumFacing.Axis.Y)
		{
			facing = EnumFacing.NORTH;
		}
		
		Boolean burning = (((meta & 0x30) >> 2) == 1);
		
		return this.getDefaultState().withProperty(FACING, facing).withProperty(BURNING, burning);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int facing = ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
		int burning = (((Boolean)state.getValue(BURNING)) ? 1 : 0) << 2;
		
		return facing | burning;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {FACING, BURNING});
	}

	@SideOnly(Side.CLIENT)
	static final class SwitchEnumFacing
	{
		static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];

		static
		{
			try
			{
				FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 1;
			}
			catch (NoSuchFieldError var4)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
			}
			catch (NoSuchFieldError var3)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 3;
			}
			catch (NoSuchFieldError var2)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 4;
			}
			catch (NoSuchFieldError var1)
			{
				;
			}
		}
	}
	
	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos) {
		IBlockState furnace = world.getBlockState(pos);
		
		if (furnace != null)
		{
			return !(Boolean)(furnace.getValue(BlockFurnaceDirt.BURNING)) ? 0 : 13;
		}
		
		return 0;
	}
}
