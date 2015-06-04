package exnihilo2.blocks.crucibles;

import exnihilo2.EN2;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.crucibles.tileentity.TileEntityCrucible;
import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import exnihilo2.items.meshs.ItemMesh;
import exnihilo2.registries.sifting.SieveRegistry;
import exnihilo2.util.helpers.InventoryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockCrucible extends BlockContainer{

	public BlockCrucible()
	{
		super(Material.clay);
		
		this.setHardness(1.0f);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (player == null)
		{
			return false;
		}

		TileEntityCrucible crucible = (TileEntityCrucible) world.getTileEntity(pos);
		ItemStack item = player.getCurrentEquippedItem();

		if (item != null && crucible != null)
		{
			if (FluidContainerRegistry.isEmptyContainer(item) )
			{
				ItemStack full = FluidContainerRegistry.fillFluidContainer(crucible.getCurrentFluid(), item);

				if (full != null)
				{
					if (player != null)
					{
						if (!player.capabilities.isCreativeMode)
						{
							if (item.stackSize > 1) 
							{
								item.stackSize--;
								InventoryHelper.giveItemStackToPlayer(player, full);
							} 
							else 
							{
								player.setCurrentItemOrArmor(0, full);
							}
						}
					}

					crucible.drain(EnumFacing.DOWN, 1000, true);
					return true;
				}
			}
			
			ItemStack contents = item.copy();
			contents.stackSize = 1;
			
			if (crucible.canInsertItem(0, contents, EnumFacing.UP))
			{
				crucible.setInventorySlotContents(0, contents);
				
				world.playSoundEffect(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, "step.stone", 0.5f, 1.0f);

				InventoryHelper.consumeItem(player, item);
			}
		}

		//Return true to keep buckets from pouring all over the damn place.
		return true;
	}
	
	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos) {
		TileEntityCrucible crucible = (TileEntityCrucible) world.getTileEntity(pos);

		if (crucible != null && crucible.getFluidFullness() > crucible.getSolidFullness())
		{
			FluidStack fluid = crucible.getCurrentFluid();
			
			if (fluid != null && fluid.getFluid() != null)
			{
				return crucible.getCurrentFluid().getFluid().getLuminosity();
			}
		}
		
		return 0;
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
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityCrucible();
	}
}
