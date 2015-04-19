package exnihilo2.barrels.tileentity.layers;

import java.util.ArrayList;
import java.util.Iterator;

import exnihilo2.EN2;
import exnihilo2.barrels.architecture.BarrelState;
import exnihilo2.barrels.tileentity.TileEntityBarrel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fluids.FluidStack;

public class BarrelInventoryLayer extends BarrelFluidLayer implements ISidedInventory{
	protected ArrayList<ItemStack> output = new ArrayList<ItemStack>();

	public void addOutput(ItemStack item)
	{
		if (item != null && item.stackSize > 0)
		{
			output.add(item);
		}
	}
	
	@Override
	public int getSizeInventory() 
	{
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
		if (index == 0 && output.size() > 0)
		{
			return output.get(0);
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
		EN2.log.error("setInventorySlotContents");
		if (stack == null || stack.getItem() == null)
		{
			if (index == 0 && output.size() > 0)
			{
				output.remove(0);
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
		TileEntityBarrel barrel = (TileEntityBarrel)this;
		BarrelState state = barrel.getState();
		
		return state.canUseItem(barrel, stack);
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
		EN2.log.error("Attempt to insert item. Index: " + index + ", Direction: " + direction.toString() + ", Item: " + stack.getDisplayName());
		
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
			return (output.size() > 0);
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
	}
}
