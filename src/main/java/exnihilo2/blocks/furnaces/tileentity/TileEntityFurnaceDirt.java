package exnihilo2.blocks.furnaces.tileentity;

import exnihilo2.EN2;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.furnaces.BlockFurnaceDirt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//Most of this code was taken directly from vanilla and modified.
//I'll clean it up sometime. It's really messy, probably from the decompilation process.
//At least I got it down to one single block instead of two.
public class TileEntityFurnaceDirt extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory{
	    private static final int[] slotsTop = new int[] {0};
	    private static final int[] slotsBottom = new int[] {2, 1};
	    private static final int[] slotsSides = new int[] {1};
	    private ItemStack[] furnaceItemStacks = new ItemStack[3];
	    private int furnaceBurnTime;
	    private int currentItemBurnTime;
	    private int cookTime;
	    private int totalCookTime;

	    public int getSizeInventory()
	    {
	        return this.furnaceItemStacks.length;
	    }

		public ItemStack getStackInSlot(int index)
	    {
	        return this.furnaceItemStacks[index];
	    }

	    public ItemStack decrStackSize(int index, int count)
	    {
	        if (this.furnaceItemStacks[index] != null)
	        {
	            ItemStack itemstack;

	            if (this.furnaceItemStacks[index].stackSize <= count)
	            {
	                itemstack = this.furnaceItemStacks[index];
	                this.furnaceItemStacks[index] = null;
	                return itemstack;
	            }
	            else
	            {
	                itemstack = this.furnaceItemStacks[index].splitStack(count);

	                if (this.furnaceItemStacks[index].stackSize == 0)
	                {
	                    this.furnaceItemStacks[index] = null;
	                }

	                return itemstack;
	            }
	        }
	        else
	        {
	            return null;
	        }
	    }

	    public ItemStack getStackInSlotOnClosing(int index)
	    {
	        if (this.furnaceItemStacks[index] != null)
	        {
	            ItemStack itemstack = this.furnaceItemStacks[index];
	            this.furnaceItemStacks[index] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	    }

	    public void setInventorySlotContents(int index, ItemStack stack)
	    {
	        boolean flag = stack != null && stack.isItemEqual(this.furnaceItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.furnaceItemStacks[index]);
	        this.furnaceItemStacks[index] = stack;

	        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
	        {
	            stack.stackSize = this.getInventoryStackLimit();
	        }

	        if (index == 0 && !flag)
	        {
	            this.totalCookTime = this.getRequiredCookTime(stack);
	            this.cookTime = 0;
	            this.markDirty();
	        }
	    }
	    
	    @Override
		public String getCommandSenderName() {
			return this.getBlockType().getUnlocalizedName();
		}
	    
	    public boolean hasCustomName()
	    {
	        return false;
	    }

	    public void readFromNBT(NBTTagCompound compound)
	    {
	        super.readFromNBT(compound);
	        NBTTagList nbttaglist = compound.getTagList("Items", 10);
	        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

	        for (int i = 0; i < nbttaglist.tagCount(); ++i)
	        {
	            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
	            byte b0 = nbttagcompound1.getByte("Slot");

	            if (b0 >= 0 && b0 < this.furnaceItemStacks.length)
	            {
	                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
	            }
	        }

	        this.furnaceBurnTime = compound.getShort("BurnTime");
	        this.cookTime = compound.getShort("CookTime");
	        this.totalCookTime = compound.getShort("CookTimeTotal");
	        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
	    }

	    public void writeToNBT(NBTTagCompound compound)
	    {
	        super.writeToNBT(compound);
	        compound.setShort("BurnTime", (short)this.furnaceBurnTime);
	        compound.setShort("CookTime", (short)this.cookTime);
	        compound.setShort("CookTimeTotal", (short)this.totalCookTime);
	        NBTTagList nbttaglist = new NBTTagList();

	        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
	        {
	            if (this.furnaceItemStacks[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
	                nbttaglist.appendTag(nbttagcompound1);
	            }
	        }

	        compound.setTag("Items", nbttaglist);
	    }

	    public int getInventoryStackLimit()
	    {
	        return 64;
	    }

	    public boolean isBurning()
	    {
	        return this.furnaceBurnTime > 0;
	    }

	    @SideOnly(Side.CLIENT)
	    public static boolean isBurning(IInventory inv)
	    {
	        return inv.getField(0) > 0;
	    }

	    public void update()
	    {
	        boolean flag = this.isBurning();
	        boolean flag1 = false;

	        if (this.isBurning())
	        {
	            --this.furnaceBurnTime;
	        }

	        if (!this.worldObj.isRemote)
	        {
	            if (!this.isBurning() && (this.furnaceItemStacks[1] == null || this.furnaceItemStacks[0] == null))
	            {
	                if (!this.isBurning() && this.cookTime > 0)
	                {
	                    this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
	                }
	            }
	            else
	            {
	                if (!this.isBurning() && this.canSmelt())
	                {
	                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

	                    if (this.isBurning())
	                    {
	                        flag1 = true;

	                        if (this.furnaceItemStacks[1] != null)
	                        {
	                            --this.furnaceItemStacks[1].stackSize;

	                            if (this.furnaceItemStacks[1].stackSize == 0)
	                            {
	                                this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
	                            }
	                        }
	                    }
	                }

	                if (this.isBurning() && this.canSmelt())
	                {
	                    ++this.cookTime;

	                    if (this.cookTime == this.totalCookTime)
	                    {
	                        this.cookTime = 0;
	                        this.totalCookTime = this.getRequiredCookTime(this.furnaceItemStacks[0]);
	                        this.smeltItem();
	                        flag1 = true;
	                    }
	                }
	                else
	                {
	                    this.cookTime = 0;
	                }
	            }

	            if (flag != this.isBurning())
	            {
	                flag1 = true;
	                BlockFurnaceDirt.setState(this.isBurning(), this.worldObj, this.pos);
	            }
	        }

	        if (flag1)
	        {
	            this.markDirty();
	        }
	    }

	    public int getRequiredCookTime(ItemStack item)
	    {
	        return 400;
	    }
	    
	    public static int getItemBurnTime(ItemStack item)
	    {
			int time = TileEntityFurnace.getItemBurnTime(item);
			
			if (time > 0)
			{
				return time;
			}

			return 0;
	    }

	    private boolean canSmelt()
	    {
	        if (this.furnaceItemStacks[0] == null)
	        {
	            return false;
	        }
	        else
	        {
	            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);
	            if (itemstack == null) return false;
	            if (this.furnaceItemStacks[2] == null) return true;
	            if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) return false;
	            int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
	            return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize();
	        }
	    }

	    public void smeltItem()
	    {
	        if (this.canSmelt())
	        {
	            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);

	            if (this.furnaceItemStacks[2] == null)
	            {
	                this.furnaceItemStacks[2] = itemstack.copy();
	            }
	            else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem())
	            {
	                this.furnaceItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
	            }

	            if (this.furnaceItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.sponge) && this.furnaceItemStacks[0].getMetadata() == 1 && this.furnaceItemStacks[1] != null && this.furnaceItemStacks[1].getItem() == Items.bucket)
	            {
	                this.furnaceItemStacks[1] = new ItemStack(Items.water_bucket);
	            }

	            --this.furnaceItemStacks[0].stackSize;

	            if (this.furnaceItemStacks[0].stackSize <= 0)
	            {
	                this.furnaceItemStacks[0] = null;
	            }
	        }
	    }

	    public static boolean isItemFuel(ItemStack item)
	    {
	        return getItemBurnTime(item) > 0;
	    }

	    public boolean isUseableByPlayer(EntityPlayer player)
	    {
	        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	    }

	    public void openInventory(EntityPlayer player) {}

	    public void closeInventory(EntityPlayer player) {}

	    /**
	     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	     */
	    public boolean isItemValidForSlot(int index, ItemStack stack)
	    {
	        return index == 2 ? false : (index != 1 ? true : isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack));
	    }

	    public int[] getSlotsForFace(EnumFacing side)
	    {
	        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
	    }

	    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	    {
	        return this.isItemValidForSlot(index, itemStackIn);
	    }

	    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	    {
	        if (direction == EnumFacing.DOWN && index == 1)
	        {
	            Item item = stack.getItem();

	            if (item != Items.water_bucket && item != Items.bucket)
	            {
	                return false;
	            }
	        }

	        return true;
	    }

	    public String getGuiID()
	    {
	        return "minecraft:furnace";
	    }

	    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	    {
	        return new ContainerFurnace(playerInventory, this);
	    }

	    public int getField(int id)
	    {
	        switch (id)
	        {
	            case 0:
	                return this.furnaceBurnTime;
	            case 1:
	                return this.currentItemBurnTime;
	            case 2:
	                return this.cookTime;
	            case 3:
	                return this.totalCookTime;
	            default:
	                return 0;
	        }
	    }

	    public void setField(int id, int value)
	    {
	        switch (id)
	        {
	            case 0:
	                this.furnaceBurnTime = value;
	                break;
	            case 1:
	                this.currentItemBurnTime = value;
	                break;
	            case 2:
	                this.cookTime = value;
	                break;
	            case 3:
	                this.totalCookTime = value;
	        }
	    }

	    public int getFieldCount()
	    {
	        return 4;
	    }

	    public void clear()
	    {
	        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
	        {
	            this.furnaceItemStacks[i] = null;
	        }
	    }

//	    @Override
//		public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) 
//		{
//			return !oldState.getBlock().equals(newState.getBlock());
//		}
	}
