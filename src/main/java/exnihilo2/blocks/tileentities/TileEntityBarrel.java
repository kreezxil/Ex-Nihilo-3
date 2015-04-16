package exnihilo2.blocks.tileentities;

import exnihilo2.EN2;
import exnihilo2.barrels.Barrels;
import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.interfaces.IBarrelState;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBarrel extends TileEntity {
	
	private BaseBarrelState state;
	
	public void TileEnitityBarrel()
	{
		this.state = Barrels.getState("empty");
		EN2.log.error("State set to empty.");
	}
	
	public void setState(String key)
	{
		reset();
		
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
	
	public IBarrelState getState()
	{
		return this.state;
	}
	
	private void reset()
	{
		//Reset all properties to defaults.
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		this.setState(compound.getString("state"));
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		if (state != null)
		{
			compound.setString("state", this.state.getBarrelStateKey());
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
