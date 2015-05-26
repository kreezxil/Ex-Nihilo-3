package exnihilo2.items.meshs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

public class ItemMeshSilk extends ItemMesh implements ISieveMesh {

	@Override
	public TextureAtlasSprite getMeshTexture() 
	{
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("exnihilo2:blocks/sieve_mesh_silk_white");	
	}

	@Override
	public int getMaxUses() 
	{
		return 64;
	}
}
