package exnihilo2.barrels.tileentity.logic;

import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.tileentity.TileEntityBarrel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class BarrelFluidLayer extends BarrelStateLayer implements IFluidTank 
{
	private static final int CAPACITY = 1000;
	
	protected FluidStack fluid;
	
	@Override
	public FluidStack getFluid() 
	{
		return fluid;
	}

	@Override
	public int getFluidAmount()
	{
		if (fluid == null)
        {
            return 0;
        }
		
        return fluid.amount;
	}

	@Override
	public int getCapacity() 
	{
		return this.CAPACITY;
	}

	@Override
	public FluidTankInfo getInfo() 
	{
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) 
	{
		TileEntityBarrel barrel = (TileEntityBarrel)this;
		BaseBarrelState state = barrel.getState();
		
        if (resource == null || state == null || !state.canManipulateFluids(barrel))
        {
            return 0;
        }

        if (!doFill)
        {
            if (fluid == null)
            {
                return Math.min(this.CAPACITY, resource.amount);
            }

            if (!fluid.isFluidEqual(resource))
            {
                return 0;
            }

            return Math.min(this.CAPACITY - fluid.amount, resource.amount);
        }

        if (fluid == null)
        {
            fluid = new FluidStack(resource, Math.min(this.CAPACITY, resource.amount));
            //TODO: change state to render fluid.

            return fluid.amount;
        }

        if (!fluid.isFluidEqual(resource))
        {
            return 0;
        }
        
        int avaliable = this.CAPACITY - fluid.amount;

        if (resource.amount < avaliable)
        {
            fluid.amount += resource.amount;
            avaliable = resource.amount;
        }
        else
        {
            fluid.amount = this.CAPACITY;
        }

        return avaliable;
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain)
    {
        TileEntityBarrel barrel = (TileEntityBarrel)this;
		BaseBarrelState state = barrel.getState();
		
        if (fluid == null || state == null || !state.canManipulateFluids(barrel))
        {
            return null;
        }

        int drained = maxDrain;
        if (fluid.amount < drained)
        {
            drained = fluid.amount;
        }

        FluidStack stack = new FluidStack(fluid, drained);
        if (doDrain)
        {
            fluid.amount -= drained;
            if (fluid.amount <= 0)
            {
                fluid = null;
                setState("empty");
            }
        }
        return stack;
    }

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		compound.setBoolean("fluid", fluid != null);
		if (fluid != null)
		{
			fluid.writeToNBT(compound);
		}
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		if (compound.getBoolean("fluid"))
		{
			fluid = FluidStack.loadFluidStackFromNBT(compound);
		}
	}
}
