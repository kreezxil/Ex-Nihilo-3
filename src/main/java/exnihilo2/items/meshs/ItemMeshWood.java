package exnihilo2.items.meshs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

public class ItemMeshWood extends ItemMesh implements ISieveMesh {

	@Override
	public TextureAtlasSprite getMeshTexture() 
	{
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("exnihilo2:blocks/sieve_mesh_wood");	
	}

	@Override
	public int getDurability() 
	{
		return 16;
	}
}
