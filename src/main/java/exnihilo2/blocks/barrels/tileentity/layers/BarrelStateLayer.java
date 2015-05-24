package exnihilo2.blocks.barrels.tileentity.layers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class BarrelStateLayer extends TileEntity{
	protected BarrelState state;
	
	public BarrelState getState()
	{
		return state;
	}
	
	public void setState(BarrelState new_state)
	{
		String keyA = "";
		String keyB = "";
		
		if (state != null)
		{
			keyA = this.state.getUniqueIdentifier();
		}
		
		if (new_state != null)
		{
			state = new_state;
		}
		else
		{
			state = BarrelStates.empty;
		}
		
		keyB = this.state.getUniqueIdentifier();
		
		if (!keyA.equals(keyB))
		{
			TileEntityBarrel barrel = (TileEntityBarrel)this;

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

		this.setState(BarrelStates.getState(compound.getString("state")));
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		if (state != null)
		{
			compound.setString("state", state.getUniqueIdentifier());
		}
	}
}
