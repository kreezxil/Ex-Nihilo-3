package exnihilo2.blocks.barrels.tileentity.layers;

import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class BarrelFluidLayer extends BarrelStateLayer implements IFluidTank, IFluidHandler
{
	private static final int CAPACITY = 1000;
	
	protected FluidStack fluid;
	
	//IFluidTank
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
		BarrelState state = barrel.getState();
		
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
            barrel.setState(BarrelStates.fluid);

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
            barrel.requestSync();
        }
        else
        {
            fluid.amount = this.CAPACITY;
            barrel.requestSync();
        }

        return avaliable;
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain)
    {
        TileEntityBarrel barrel = (TileEntityBarrel)this;
		BarrelState state = barrel.getState();
		
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
                setState(BarrelStates.empty);
            }
            else
            {
            	barrel.requestSync();
            }
        }
        return stack;
    }
    
    public void clearFluid()
    {
    	this.fluid = null;
    }
    
    public void transformFluidTo(FluidStack input)
    {
    	this.fluid = input;
    }

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		if (compound.getBoolean("fluid"))
		{
			fluid = FluidStack.loadFluidStackFromNBT(compound);
		}
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		compound.setBoolean("fluid", fluid != null);
		if (fluid != null)
		{
			fluid.writeToNBT(compound);
		}
	}

	/* IFluidHandler */
    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill)
    {
        return fill(resource, doFill);
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(getFluid()))
        {
            return null;
        }
        
        return drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
    {
        return drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid)
    {
        return true;
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid)
    {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from)
    {
        return new FluidTankInfo[] { getInfo() };
    }
}
