package exnihilo2.blocks.barrels.tileentity;

import java.lang.reflect.Method;

import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.layers.BarrelFluidLayer;
import exnihilo2.blocks.barrels.tileentity.layers.BarrelInventoryLayer;
import exnihilo2.blocks.barrels.tileentity.layers.BarrelStateLayer;
import exnihilo2.util.Color;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityBarrel extends BarrelInventoryLayer implements ITickable
{
	protected int luminosity = 0;
	protected int volume = 0;
	protected int MAX_VOLUME = 1000;
	protected Color color = new Color("FFFFFF");
	
	protected int generalTimer = 0;
	protected int generalTimerMax = 0;
	
	protected int updateTimer = 0;
	protected int updateTimerMax = 8; //Sync if an update is required.
	protected boolean updateQueued = false;
	protected boolean updateTimerRunning = false;
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) 
	{
		return !oldState.getBlock().equals(newState.getBlock());
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
		
		if (state != null)
		{
			setLuminosity(state.getLuminosity(this));
		}
		
		//Update timer used by states.
		if (generalTimerMax != 0 && generalTimer < generalTimerMax)
		{
			generalTimer++;
		}
		
		//Update packet throttling system
		if (!this.worldObj.isRemote && updateTimerRunning)
		{
			updateTimer++;
			
			if (updateTimer > updateTimerMax)
			{
				updateTimer = 0;
				if (updateQueued)
				{
					updateQueued = false;
					
					getWorld().markBlockForUpdate(this.getPos());
				}
				else
				{
					updateTimerRunning = false;
				}
			}
		}
	}
	
	public void requestSync()
	{
		if (getWorld() != null && !getWorld().isRemote)
		{
			if (!updateTimerRunning)
			{
				updateTimerRunning = true;
				this.getWorld().markBlockForUpdate(this.getPos());
			}
			else
			{
				this.updateQueued = true;
			}
		}
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
			
			if (getWorld() != null)
			{
				getWorld().checkLight(getPos());
			}
		}
	}
	
	public int getVolume()
	{
		return volume;
	}
	
	public void setVolume(int volume)
	{
		this.volume = volume;
		
		if (this.volume > this.getVolumeMax())
		{
			this.volume = getVolumeMax();
		}
	}
	
	public int getVolumeMax()
	{
		return this.MAX_VOLUME;
	}
	
	public double getVolumeProportion()
	{
		return (double)this.getVolume() / (double)this.getVolumeMax();
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		generalTimer = compound.getInteger("timer");
		generalTimerMax = compound.getInteger("timermax");
		setLuminosity(compound.getInteger("luminosity"));
		volume = compound.getInteger("volume");
		color = new Color(compound.getInteger("color"));
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setInteger("timer", generalTimer);
		compound.setInteger("timermax", generalTimerMax);
		compound.setInteger("luminosity", getLuminosity());
		compound.setInteger("volume", volume);
		compound.setInteger("color", this.color.toInt());
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

  @Override
  public ItemStack removeStackFromSlot(int index)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getName()
  {
    // TODO Auto-generated method stub
    return null;
  }
}
