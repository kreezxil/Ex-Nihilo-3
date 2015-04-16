package exnihilo2.barrels.tileentity;

import exnihilo2.EN2;
import exnihilo2.barrels.Barrels;
import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.interfaces.IBarrelState;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityBarrel extends TileEntity implements IUpdatePlayerListBox {
	
	protected BaseBarrelState state;
	
	protected int generalTimer = 0;
	protected int generalTimerMax = 0;
	
	protected int syncTimer = 0;
	protected final int syncTimerMax = 20; //Sync once per second if an update is required.
	
	protected FluidStack fluid;
	protected ItemStack output;
	
	boolean updateNeeded = false;
	
	public void TileEnitityBarrel()
	{
		state = Barrels.getState("empty");
	}
	
	public IBarrelState getState()
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
		
		state.activate(this);
	}
	
	public void startTimer(int maxTicks)
	{
		generalTimer = 0;
		generalTimerMax = maxTicks;
	}
	
	public double getTimerStatus()
	{
		if (generalTimerMax == 0 || generalTimer > generalTimerMax)
		{
			return 1.0d;
		}
		else
		{
			return (double)generalTimer / (double)generalTimerMax;
		}
	}
	
	@Override
	public void update() {
		//Update timer used by states.
		if (generalTimerMax != 0 && generalTimer > generalTimerMax)
		{
			generalTimer++;
			
			if (generalTimer > generalTimerMax)
			{
				generalTimerMax = 0;
			}
		}
		
		//Update the barrel state object.
		if (this.state != null)
		{
			state.update(this);
		}
		
		//Update timer used to sync client and server.
		syncTimer++;
		
		if (syncTimer > syncTimerMax)
		{
			syncTimer = 0;
			if (updateNeeded)
			{
				this.getWorld().markBlockForUpdate(this.getPos());
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		setState(compound.getString("state"));
		generalTimer = compound.getInteger("timer");
		
		compound.setBoolean("fluid", fluid != null);
		if (fluid != null)
		{
			fluid.writeToNBT(compound);
		}
		
		compound.setBoolean("item", output != null);
		if (output != null)
		{
			output.writeToNBT(compound);
		}
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		if (state != null)
		{
			compound.setString("state", state.getBarrelStateKey());
		}
		
		compound.setInteger("timer", generalTimer);
		
		if (compound.getBoolean("fluid"))
		{
			fluid = FluidStack.loadFluidStackFromNBT(compound);
		}
		
		if (compound.getBoolean("item"))
		{
			output = ItemStack.loadItemStackFromNBT(compound);
		}
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);

		return new S35PacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		NBTTagCompound tag = pkt.getNbtCompound();
		readFromNBT(tag);
	}

	
}
