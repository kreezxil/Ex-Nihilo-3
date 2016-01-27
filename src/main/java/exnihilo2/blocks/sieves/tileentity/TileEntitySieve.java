package exnihilo2.blocks.sieves.tileentity;

import exnihilo2.EN2;
import exnihilo2.client.particles.ParticleSieve;
import exnihilo2.items.meshs.ItemMesh;
import exnihilo2.registries.sifting.SieveRegistry;
import exnihilo2.util.Color;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySieve extends TileEntity implements ITickable {
	protected ItemStack mesh;
	protected ItemStack contents;
	protected IBlockState contentsState;
	
	protected int work = 0;
	protected int workMax = 1000;
	protected int workSpeed = 30;
	
	protected int updateTimer = 0;
	protected int updateTimerMax = 8; //Sync if an update is required.
	protected boolean updateQueued = false;
	protected boolean updateTimerRunning = false;
	
	protected int workThisCycle = 0;
	protected int workPerCycleLimit = 120;
	protected int workCycleTimer = 0;
	protected int workCycleTimerMax = 20;
	
	protected boolean spawningParticles = false;
	protected int spawnParticlesTimer = 0;
	protected int spawnParticlesTimerMax = 5;
	
	@Override
	public void update() 
	{
		if (!this.worldObj.isRemote)
		{
			//Speed throttling.
			workCycleTimer++;
			
			if (workCycleTimer > workCycleTimerMax)
			{
				workThisCycle = 0;
				workCycleTimer = 0;
			}

			//Packet throttling
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
		else
		{
			if (spawningParticles)
			{
				generateParticles(contentsState);
				
				spawnParticlesTimer++;
				if (spawnParticlesTimer > spawnParticlesTimerMax)
				{
					spawningParticles = false;
				}
			}
		}
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
	
	public boolean hasMesh()
	{
		return this.mesh != null;
	}
	
	public ItemStack getMesh()
	{
		return this.mesh;
	}
	
	public void setMesh(ItemStack mesh)
	{
		this.mesh = mesh;
		sync();
	}
	
	public ItemStack getContents()
	{
		return contents;
	}
	
	public void setContents(ItemStack input)
	{
		this.contents = input;
		
		if (contents != null)
		{
			Block block = Block.getBlockFromItem(contents.getItem());

			if (block != null)
			{
				contentsState = block.getStateFromMeta(contents.getMetadata());
			}
		}
		else
		{
			contentsState = null;
		}
		
		sync();
	}
	
	public boolean canWork()
	{
		return this.contents != null;
	}
	
	public void doWork()
	{
		if (!this.worldObj.isRemote)
		{
			this.spawningParticles = true;
			addThrottledWork(workSpeed);
			
			if (work > workMax)
			{
				if (contentsState != null)
				{
					for (ItemStack i : SieveRegistry.generateRewards(contentsState))
					{
						EntityItem entityitem = new EntityItem(getWorld(), pos.getX() + 0.5f, pos.up().getY() + 0.5f, pos.getZ() + 0.5f, i);

						entityitem.motionX = getWorld().rand.nextGaussian() * 0.05F;
						entityitem.motionY = (0.2d);
						entityitem.motionZ = getWorld().rand.nextGaussian() * 0.05F;
						entityitem.setDefaultPickupDelay();
						
						getWorld().spawnEntityInWorld(entityitem);
					}
				}
				
				work = 0;
				contents = null;
				
				if (this.mesh != null)
				{
					if (mesh.attemptDamageItem(1, worldObj.rand))
					{
						getWorld().playSoundEffect(getPos().getX() + 0.5f, getPos().getY() + 0.5f, getPos().getZ() + 0.5f, "random.break", 0.5f, 2.5f);
						setMesh(null);
					}
				}
			}
			
			sync();
		}
	}
	
	private void addThrottledWork(int workIn)
	{
		if (workThisCycle + workIn > workPerCycleLimit)
		{
			this.work += workPerCycleLimit - workThisCycle;
		}
		else
		{
			this.work += workIn;
		}
	}
	
	public float getProgress()
	{
		return (float)work / (float)workMax;
	}
	
	//Subclasses which don't want to use the replacable meshes can override this directly.
	public TextureAtlasSprite getMeshTexture()
	{
		if (mesh != null)
			return ((ItemMesh) mesh.getItem()).getMeshTexture();
		else
			return null;
	}
	
	public void startSpawningParticles()
	{
		this.spawningParticles = true;
		this.spawnParticlesTimer = 0;
	}
	
	@SideOnly(Side.CLIENT)
	private void generateParticles(IBlockState block)
	{
		if (block != null)
		{
			TextureAtlasSprite texture = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(block);

			for (int x = 0; x < 6; x++)
			{	
				ParticleSieve dust = new ParticleSieve(worldObj, 
						pos.getX() + 0.8d * worldObj.rand.nextFloat() + 0.15d, 
						pos.getY() + 0.585d, 
						pos.getZ() + 0.8d * worldObj.rand.nextFloat() + 0.15d, 
						0.0d, 0.0d, 0.0d, texture);
				
				Minecraft.getMinecraft().effectRenderer.addEffect(dust);
			}
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
		
		work = compound.getInteger("work");
		if(compound.getBoolean("particles"))
			startSpawningParticles();
		
		NBTTagList items = compound.getTagList("items", Constants.NBT.TAG_COMPOUND);
		
		NBTTagCompound meshTag = items.getCompoundTagAt(0);
		setMesh(ItemStack.loadItemStackFromNBT(meshTag));
		
		NBTTagCompound contentsTag = items.getCompoundTagAt(1);
		setContents(ItemStack.loadItemStackFromNBT(contentsTag));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setInteger("work", work);
		compound.setBoolean("particles", spawningParticles);
		
		NBTTagList items = new NBTTagList();
		
		NBTTagCompound meshTag = new NBTTagCompound();
		if (mesh != null)
		{
			mesh.writeToNBT(meshTag);
		}
		items.appendTag(meshTag);
		
		NBTTagCompound contentsTag = new NBTTagCompound();
		if (contents != null)
		{
			contents.writeToNBT(contentsTag);
		}
		items.appendTag(contentsTag);
		
		compound.setTag("items", items);
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
