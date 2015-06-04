package exnihilo2.blocks.crucibles.tileentity.layers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.crucibles.tileentity.TileEntityCrucible;

public class CrucibleInventoryLayer extends CrucibleFluidLayer implements ISidedInventory{
	protected ItemStack lastItemAdded = null;
	private final static int[] TOP_SLOTS = new int[] {0};

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (isItemValidForSlot(index, stack))
		{
			lastItemAdded = stack;
			
			TileEntityCrucible crucible = (TileEntityCrucible)this;
			crucible.addSolid(250);
			crucible.sync();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 0 && stack != null && stack.getItem().equals(Item.getItemFromBlock(Blocks.cobblestone)))
		{
			TileEntityCrucible crucible = (TileEntityCrucible)this;
			
			if (crucible.hasSpaceFor(250))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {}

	@Override
	public String getCommandSenderName() {
		return EN2Blocks.crucible.getUnlocalizedName();
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if (side == EnumFacing.UP)
		{
			return TOP_SLOTS;
		}
		
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
		if (direction == EnumFacing.UP)
		{
			return isItemValidForSlot(0, stack);
		}
		
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		NBTTagList items = compound.getTagList("items", Constants.NBT.TAG_COMPOUND);
		if (items.tagCount() > 0)
		{
			NBTTagCompound item = items.getCompoundTagAt(0);
			lastItemAdded = ItemStack.loadItemStackFromNBT(item);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		NBTTagList items = new NBTTagList();
		if (lastItemAdded != null)
		{
			NBTTagCompound item = new NBTTagCompound();
			lastItemAdded.writeToNBT(item);
			items.appendTag(item);
		}
		compound.setTag("items", items);
	}
}
