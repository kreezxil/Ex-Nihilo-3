package exnihilo2.blocks.barrels.tileentity.layers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import exnihilo2.EN2;
import exnihilo2.blocks.barrels.BarrelStateManager;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class BarrelStateLayer extends TileEntity{
	protected BarrelState state;
	
	public BarrelState getState()
	{
		return state;
	}
	
	public void setState(String key)
	{
		if (key != this.state.getKey())
		{
			BarrelState newState = BarrelStateManager.getState(key);
			
			if (newState != null)
			{
				state = newState;
			}
			else
			{
				state = BarrelStateManager.getState("empty");
			}
			
			TileEntityBarrel barrel = (TileEntityBarrel)this;
			barrel.resetTimer();
			barrel.setLuminosity(0);
			state.activate(barrel);
			barrel.requestSync();
		}
	}
	
	public void update()
	{
		//Update the barrel state object.
		if (this.state != null)
		{
			state.update((TileEntityBarrel) this);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		setState(compound.getString("state"));
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		if (state != null)
		{
			compound.setString("state", state.getKey());
		}
	}
}
