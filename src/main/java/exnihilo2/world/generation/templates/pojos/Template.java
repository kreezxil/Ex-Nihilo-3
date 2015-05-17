package exnihilo2.world.generation.templates.pojos;

import java.util.ArrayList;

import exnihilo2.EN2;
import exnihilo2.util.helpers.GameRegistryHelper;
import exnihilo2.util.helpers.PositionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class Template {
	private int spawnYLevel = 64;
	private ArrayList<TemplateBlock>blocks = new ArrayList<TemplateBlock>();

	public ArrayList<TemplateBlock> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<TemplateBlock> blocks) {
		this.blocks = blocks;
	}

	public int getSpawnYLevel() {
		return spawnYLevel;
	}

	public void setSpawnYLevel(int spawnYLevel) {
		this.spawnYLevel = spawnYLevel;
	}
	
	public void generate(World world, int xOffset, int zOffset)
	{
		ArrayList<TemplateBlock> blocks = this.getBlocks();
		
		for (TemplateBlock b : blocks)
		{
			Block block = GameRegistryHelper.findBlock(b.getId());
			
			if (block != null)
			{
				int x = b.getX() + xOffset;
				int y = b.getY() + this.getSpawnYLevel();
				int z = b.getZ() + zOffset;
				BlockPos pos = new BlockPos(x, y, z);
				
				setBlockWithoutUpdate(world, pos, block.getStateFromMeta(b.getMeta()));
				
				TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
				if (b.getContents() != null && te != null && te instanceof IInventory)
				{
					IInventory inv = (IInventory) te;
					
					if (inv != null)
					{
						int i = 0;
						int max = inv.getSizeInventory();
						
						for (TemplateItem contentItem : b.getContents())
						{
							if (i < max && contentItem.getCount() > 0)
							{
								Item item = GameRegistryHelper.findItem(contentItem.getId());
								
								if (item != null)
									inv.setInventorySlotContents(i, new ItemStack(item, contentItem.getCount(), contentItem.getMeta()));
							}
								
							i++;
						}
					}
				}
			}
			else
			{
				EN2.log.error("Unable to locate block (" + b.getId() + ").");
			}
		}
	}

	private void setBlockWithoutUpdate(World world, BlockPos pos, IBlockState state)
	{
		Chunk chunk =  world.getChunkFromBlockCoords(pos);
		ExtendedBlockStorage[] storage = chunk.getBlockStorageArray();
		
		if (storage[(pos.getY() >> 4)] == null)
		{
			//This call generates the sky light map and block storage so I don't have to.
			chunk.setLightFor(EnumSkyBlock.SKY, pos, 0);
		}
		
		storage[(pos.getY() >> 4)].set(pos.getX() & 15, pos.getY() & 15, pos.getZ() & 15, state);
		//world.checkLight(pos);
	}
}
