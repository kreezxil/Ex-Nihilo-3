package exnihilo2.blocks.crucibles.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.crucibles.tileentity.layers.CrucibleInventoryLayer;
import exnihilo2.blocks.furnaces.BlockFurnaceDirt;

public class TileEntityCrucible extends CrucibleInventoryLayer implements ITickable{
	protected int updateTimer = 0;
	protected int updateTimerMax = 8; //Sync if an update is required.
	protected boolean updateQueued = false;
	protected boolean updateTimerRunning = false;
	
	protected int solidContent = 0;
	protected int solidContentProcessed = 0;
	protected int solidContentMax = 200000;
	protected int solidFluidExchangeRate = 100;
	
	private int luminosity = 0;
	
	public void addSolid(int amount)
	{
		this.solidContent += (amount * 200);
	}
	
	public boolean hasSpaceFor(int amount)
	{
		return solidContent + (amount * 200) <= solidContentMax;
	}
	
	public double getSolidFullness()
	{
		return ((double)this.solidContent / (double)this.solidContentMax);
	}
	
	public double getFluidFullness()
	{
		return (double)this.fluid.amount / (double)FLUID_MAX;
	}
	
	public ItemStack getLastItemAdded()
	{
		return this.lastItemAdded;
	}
	
	public FluidStack getCurrentFluid()
	{
		return this.fluid;
	}

	@Override
	public void update() 
	{
		//process solids
		if (this.solidContent > 0)
		{
			int speed = this.getMeltingSpeed();
			
			if (speed > solidContent * 2)
			{
				speed = solidContent / 2;
			}
			
			this.solidContentProcessed += speed;
			this.solidContent -= speed * 2;
		}
		
		//transfer solids to fluids
		while(this.fluid.amount < FLUID_MAX && this.solidContentProcessed >= solidFluidExchangeRate)
		{
			this.solidContentProcessed -= solidFluidExchangeRate;
			this.fluid.amount++;
		}
		
		//Packet throttling
		if (!this.worldObj.isRemote)
		{
			if (updateTimerRunning)
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
		
		updateLightLevel();
	}

	//Send update packets to each client.
	public void sync()
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
	
	public int getMeltingSpeed()
	{
		if (getWorld().isAirBlock(getPos().down()))
			return 0;
		
		IBlockState state = getWorld().getBlockState(getPos().down());

		if (state.getBlock() == Blocks.torch)
		{
			return 5;
		}
		else if (state.getBlock() == Blocks.lava)
		{
			float percent = 1.0f - BlockLiquid.getLiquidHeightPercent(Blocks.lava.getMetaFromState(state));
			return (int)(16.0f * percent) + 2;
		}
		else if (state.getBlock() == Blocks.fire)
		{
			return 20;
		}
		else if(state.getBlock() == Blocks.lit_furnace)
		{
			return 12;
		}
		else if(state.getBlock() == EN2Blocks.furnace_dirt)
		{
			Boolean burning = (Boolean)state.getValue(BlockFurnaceDirt.BURNING);
			if (burning)
				return 12;
			else
				return 0;
			
		}
		else
		{
			return 0;
		}
	}
	
	public int getLuminosity()
	{
		return this.luminosity;
	}
	
	public void setLuminosity(int level)
	{
		if (this.luminosity != level)
		{
			this.luminosity = level;
			this.getWorld().checkLight(this.getPos());
		}
	}
	
	public void updateLightLevel()
	{
		if (fluid != null && fluid.getFluid() != null && getFluidFullness() > getSolidFullness())
		{
			setLuminosity(this.fluid.getFluid().getLuminosity());
		}
		else
		{
			setLuminosity(0);
		}
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) 
	{
		return !oldState.getBlock().equals(newState.getBlock());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.solidContent = compound.getInteger("solid");
		this.solidContentProcessed = compound.getInteger("processed");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setInteger("solid", solidContent);
		compound.setInteger("processed", solidContentProcessed);
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
