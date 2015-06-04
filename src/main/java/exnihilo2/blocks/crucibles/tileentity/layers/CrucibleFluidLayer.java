package exnihilo2.blocks.crucibles.tileentity.layers;

import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.crucibles.tileentity.TileEntityCrucible;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class CrucibleFluidLayer extends TileEntity implements IFluidHandler{
	public static final int FLUID_MAX = 8000;
	protected FluidStack fluid = new FluidStack(FluidRegistry.LAVA, 0);
	protected FluidTankInfo[] tank = new FluidTankInfo[]{new FluidTankInfo(new FluidStack(FluidRegistry.LAVA, 0), FLUID_MAX)};
	
	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		if (fluid == null || resource == null || !resource.isFluidEqual(fluid))
        {
            return null;
        }
		else
		{
	        return drain(from, resource.amount, doDrain);
		}
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		if (fluid == null || maxDrain == 0)
        {
            return null;
        }
		else
		{
	        int drained = maxDrain;
	        
	        if (fluid.amount <= drained)
	        {
	            drained = fluid.amount;
	        }

	        FluidStack stack = new FluidStack(fluid, drained);
	        
	        if (doDrain)
	        {
	            fluid.amount -= drained;
	            ((TileEntityCrucible)this).sync();
	        }
	        
	        return stack;
		}
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		return (this.fluid.getFluid().equals(fluid) && this.fluid.amount > 0);
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return tank;
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
}
