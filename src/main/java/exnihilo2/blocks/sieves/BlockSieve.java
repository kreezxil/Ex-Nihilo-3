package exnihilo2.blocks.sieves;

import exnihilo2.EN2;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import exnihilo2.items.meshs.ItemMesh;
import exnihilo2.registries.sifting.SieveRegistry;
import exnihilo2.util.helpers.InventoryHelper;
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
			if (sieve.canWork())
			{
				sieve.doWork();
				world.playSoundEffect(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, "step.sand", 0.3f, 0.6f);
			}
			else
			{
				if (sieve.hasMesh())
				{
					if (player.isSneaking())
					{
						if (!world.isRemote)
						{
							EntityItem entity = new EntityItem(world, pos.getX() + 0.5f, pos.getY() + 1.2f, pos.getZ() + 0.5f, sieve.getMesh());
							world.spawnEntityInWorld(entity);
						}
						
						sieve.setMesh(null);
					}
					else if (item != null)
					{
						Block block = Block.getBlockFromItem(item.getItem());
						
						if (block != null && SieveRegistry.isSiftable(block.getStateFromMeta(item.getMetadata())))
						{
							ItemStack contents = item.copy();
							contents.stackSize = 1;

							world.playSoundEffect(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, "step.gravel", 0.5f, 1.0f);
							
							sieve.setContents(contents);
							InventoryHelper.consumeItem(player, item);
						}
					}
				}
				else
				{
					if (item != null && item.getItem() instanceof ItemMesh)
					{
						ItemStack mesh = item.copy();
						mesh.stackSize = 1;
						
						sieve.setMesh(mesh);
						player.setCurrentItemOrArmor(0, null);
					}
				}
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
