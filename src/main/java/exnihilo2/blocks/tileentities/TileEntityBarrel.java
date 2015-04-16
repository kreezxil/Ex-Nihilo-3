package exnihilo2.blocks.tileentities;

import exnihilo2.EN2;
import exnihilo2.barrels.Barrels;
import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.interfaces.IBarrelState;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBarrel extends TileEntity implements IUpdatePlayerListBox {
	
	private BaseBarrelState state;
	
	private int generalTimer = 0;
	private int generalTimerMax = 0;
	
	private int syncTimer = 0;
	private final int syncTimerMax = 20; //Sync once per second if an update is required.
	
	boolean updateNeeded = false;
	
	public void TileEnitityBarrel()
	{
		this.state = Barrels.getState("empty");
		EN2.log.error("State set to empty.");
	}
	
	public IBarrelState getState()
	{
		return this.state;
	}
	
	public void setState(String key)
	{
		BaseBarrelState newState = Barrels.getState(key);
		
		if (state != null)
		{
			this.state = newState;
		}
		else
		{
			this.state = Barrels.getState("empty");
		}
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
