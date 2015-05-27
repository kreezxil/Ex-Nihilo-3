package exnihilo2.blocks.sieves.tileentity;

import exnihilo2.EN2;
import exnihilo2.items.meshs.ISieveMesh;
import exnihilo2.util.Color;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class TileEntitySieve extends TileEntity implements IUpdatePlayerListBox {
	protected ItemStack mesh;
	protected ItemStack contents;
	
	protected int work = 0;
	protected int work_max = 1000;
	protected int WORK_PER_CYCLE = 20;
	protected int damage = 0;
	
	protected int updateTimer = 0;
	protected int updateTimerMax = 8; //Sync if an update is required.
	protected boolean updateQueued = false;
	protected boolean updateTimerRunning = false;
	
	@Override
	public void update() {
		
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
					updateTimerRunning = false;
					
					getWorld().markBlockForUpdate(this.getPos());
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
	
	public void setMesh(ItemStack mesh)
	{
		this.mesh = mesh;
	}
	
	public ItemStack getContents()
	{
		return contents;
	}
	
	public void setContents(ItemStack input)
	{
		this.contents = input;
	}
	
	public boolean canWork()
	{
		return this.contents != null;
	}
	
	public void doWork()
	{
		if (!this.worldObj.isRemote)
		{
			work += WORK_PER_CYCLE;
			
			if (work > work_max)
			{
				//TODO generate drops.
				
				work = 0;
				contents = null;
				
				if (this.mesh != null)
				{
					damage++;
					
					if (damage > ((ISieveMesh)mesh.getItem()).getDurability())
					{
						mesh = null;
					}
				}
			}
			
			sync();
		}
	}
	
	public float getProgress()
	{
		return (float)work / (float)work_max;
	}
	
	//Subclasses which don't want to use the replacable meshes can override this directly.
	public TextureAtlasSprite getMeshTexture()
	{
		if (mesh != null)
			return ((ISieveMesh) mesh.getItem()).getMeshTexture();
		else
			return null;
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
		damage = compound.getInteger("damage");
		
		NBTTagList items = compound.getTagList("items", Constants.NBT.TAG_COMPOUND);
		
		NBTTagCompound meshTag = items.getCompoundTagAt(0);
		mesh = ItemStack.loadItemStackFromNBT(meshTag);
		
		NBTTagCompound contentsTag = items.getCompoundTagAt(1);
		contents = ItemStack.loadItemStackFromNBT(contentsTag);
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setInteger("work", work);
		compound.setInteger("damage", damage);
		
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
