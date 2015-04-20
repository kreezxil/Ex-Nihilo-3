package exnihilo2.blocks.barrels.tileentity.layers;

import java.util.ArrayList;
import java.util.Iterator;

import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class BarrelInventoryLayer extends BarrelFluidLayer implements ISidedInventory{
	protected ArrayList<ItemStack> output = new ArrayList<ItemStack>();
	protected ItemStack block = null;
	protected int MAX_OUTPUT_QUEUE_SIZE = 1;

	public void addOutput(ItemStack item)
	{
		if (item != null && item.stackSize > 0)
		{
			output.add(item);
		}
	}
	
	public void setBlock(ItemStack item)
	{
		this.block = item;
	}
	
	public ItemStack getBlock()
	{
		return block;
	}
	
	@Override
	public int getSizeInventory() 
	{
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
		if (index == 0)
		{
			if ( output.size() > 0)
			{
				return output.get(0);
			}
			else if (block != null)
			{
				return block;
			}
		}
		
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		ItemStack item = getStackInSlot(index);
		
        if (item != null && count > 0) 
        {
            if (item.stackSize <= count) 
            {
                setInventorySlotContents(index, null);
            }
            else 
            {
                item = item.splitStack(count);
            }
        }
        
        return item;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) 
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		if (stack == null || stack.getItem() == null)
		{
			if (index == 0 && output.size() > 0)
			{
				output.remove(0);
			}
			else if (block != null)
			{
				block = null;
			}
		}
		else
		{
			if (index == 1)
			{
				TileEntityBarrel barrel = (TileEntityBarrel)this;
				
				barrel.getState().useItem(null, barrel, stack);
			}
		}
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		if (this.output.size() < MAX_OUTPUT_QUEUE_SIZE)
		{
			TileEntityBarrel barrel = (TileEntityBarrel)this;
			BarrelState state = barrel.getState();
			
			return state.canUseItem(barrel, stack);
		}
		else
		{
			EN2.log.error("insert rejected due to output being full.");
		}
		
		return false;
	}

	@Override
	public int getField(int id) 
	{
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() 
	{
		return 0;
	}

	@Override
	public void clear() 
	{
		output.clear();
	}

	@Override
	public String getName() 
	{
		TileEntityBarrel barrel = (TileEntityBarrel)this;
		return barrel.getBlockType().getUnlocalizedName();
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

	@Override
	public IChatComponent getDisplayName() 
	{
		return null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) 
	{
		if (side == EnumFacing.DOWN)
		{
			return new int[]{0};
		}
		
		if (side == EnumFacing.UP)
		{
			return new int[]{1};
		}
		
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) 
	{
		if (direction == EnumFacing.UP && index == 1)
		{
			TileEntityBarrel barrel = (TileEntityBarrel)this;
			BarrelState state = barrel.getState();
			
			if (state != null)
				return state.canUseItem(barrel, stack);
		}
		
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) 
	{
		if (direction == EnumFacing.DOWN && index == 0)
		{
			return (output.size() > 0 || block!= null);
		}
		
		return false;
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		compound.setInteger("items", output.size());
		
		Iterator<ItemStack> iterator = output.iterator();
        while (iterator.hasNext()) 
        {
            iterator.next().writeToNBT(compound);
        }
        
        compound.setBoolean("block", block!=null);
        if (block != null)
        {
        	block.writeToNBT(compound);
        }
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		int items = compound.getInteger("items");
		
		for (int x = 0; x < items; x++)
		{
			output.add(ItemStack.loadItemStackFromNBT(compound));
		}
		
		if (compound.getBoolean("block"))
		{
			block = ItemStack.loadItemStackFromNBT(compound);
		}
	}
}