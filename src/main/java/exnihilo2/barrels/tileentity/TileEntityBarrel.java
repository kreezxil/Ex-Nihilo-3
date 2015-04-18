package exnihilo2.barrels.tileentity;

import java.lang.reflect.Method;

import exnihilo2.EN2;
import exnihilo2.barrels.BarrelStateManager;
import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.interfaces.IBarrelState;
import exnihilo2.barrels.tileentity.logic.BarrelFluidLayer;
import exnihilo2.barrels.tileentity.logic.BarrelInventoryLayer;
import exnihilo2.barrels.tileentity.logic.BarrelStateLayer;
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
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityBarrel extends BarrelInventoryLayer implements IUpdatePlayerListBox 
{
	private int luminosity = 0;
	
	protected int generalTimer = 0;
	protected int generalTimerMax = 0;
	
	protected int syncTimer = 0;
	protected final int syncTimerMax = 20; //Sync once per second if an update is required.
	
	public boolean updateNeeded = false;
	
	public TileEntityBarrel()
	{
		state = BarrelStateManager.getState("empty");
	}
	
	public void startTimer(int maxTicks)
	{
		generalTimer = 0;
		generalTimerMax = maxTicks;
	}
	
	public int getTimerTime()
	{
		return generalTimer;
	}
	
	public double getTimerStatus()
	{
		if(generalTimerMax == 0)
		{
			return -1.0d;
		}
		
		if (generalTimer >= generalTimerMax)
		{
			return 1.0d;
		}
		else
		{
			return (double)generalTimer / (double)generalTimerMax;
		}
	}
	
	public void resetTimer()
	{
		generalTimer = 0;
		generalTimerMax = 0;
	}
	
	@Override
	public void update() 
	{
		super.update();
		
		//Update timer used by states.
		if (generalTimerMax != 0 && generalTimer < generalTimerMax)
		{
			generalTimer++;
		}
		
		//Update timer used to sync client and server.
		syncTimer++;
		
		if (syncTimer > syncTimerMax)
		{
			syncTimer = 0;
			if (updateNeeded)
			{
				this.getWorld().markBlockForUpdate(this.getPos());
				this.updateNeeded = false;
			}
		}
	}
	
	public void sync()
	{
		this.updateNeeded = true;
	}
	
	public int getLuminosity()
	{
		return luminosity;
	}
	
	//Call this as much as you want. 
	//The lighting calculations don't fire unless the value is actually changed.
	public void setLuminosity(int level)
	{
		if (luminosity != level)
		{
			luminosity = level;
			getWorld().checkLight(getPos());
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		generalTimer = compound.getInteger("timer");
		setLuminosity(compound.getInteger("luminosity"));
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setInteger("timer", generalTimer);
		compound.setInteger("luminosity", getLuminosity());
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
