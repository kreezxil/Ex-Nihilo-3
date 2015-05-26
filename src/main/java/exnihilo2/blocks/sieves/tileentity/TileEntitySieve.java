package exnihilo2.blocks.sieves.tileentity;

import exnihilo2.EN2;
import exnihilo2.items.meshs.ISieveMesh;
import exnihilo2.util.Color;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySieve extends TileEntity implements IUpdatePlayerListBox {
	ItemStack mesh;
	
	@Override
	public void update() {
		//EN2.log.error("Sieve tile entity updating!");
	}
	
	public void setMesh(ItemStack mesh)
	{
		this.mesh = mesh;
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
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
	}
 
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
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
