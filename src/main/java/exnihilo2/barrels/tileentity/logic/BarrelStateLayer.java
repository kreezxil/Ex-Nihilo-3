package exnihilo2.barrels.tileentity.logic;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import exnihilo2.barrels.Barrels;
import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.interfaces.IBarrelState;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public class BarrelStateLayer extends TileEntity{
	protected BaseBarrelState state;
	
	public BaseBarrelState getState()
	{
		return state;
	}
	
	public void setState(String key)
	{
		BaseBarrelState newState = Barrels.getState(key);
		
		if (state != null)
		{
			state = newState;
		}
		else
		{
			state = Barrels.getState("empty");
		}
		
		TileEntityBarrel barrel = (TileEntityBarrel)this;
		state.activate(barrel);
		barrel.updateNeeded = true;
		
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
			compound.setString("state", state.getBarrelStateKey());
		}
	}
}