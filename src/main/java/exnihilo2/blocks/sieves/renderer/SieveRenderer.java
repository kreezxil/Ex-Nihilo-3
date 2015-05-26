package exnihilo2.blocks.sieves.renderer;

import org.lwjgl.opengl.GL11;

import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import exnihilo2.util.Color;
import exnihilo2.util.helpers.ContentRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class SieveRenderer extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f, int i) 
	{
		if (te != null)
		{
			TileEntitySieve sieve = (TileEntitySieve) te;
			
			GlStateManager.pushMatrix();
			RenderHelper.disableStandardItemLighting();

			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			Minecraft mc = Minecraft.getMinecraft();
			mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			
			GlStateManager.translate(x + 0.025d, y, z + 0.025d);
			GlStateManager.scale(0.95d, 1.0d, 0.95d);
			
			renderMeshTexture(sieve);
			//TODO: Render contents.
			
			RenderHelper.enableStandardItemLighting();
			GlStateManager.popMatrix();
		}
	}
	
	private void renderMeshTexture(TileEntitySieve sieve)
	{
		TextureAtlasSprite mesh = sieve.getMeshTexture();
		
		if (mesh != null)
		{
			GlStateManager.disableCull();
			ContentRenderHelper.renderContentsSimple(mesh, 0.6d, new Color("FFFFFF"));
			GlStateManager.enableCull();
		}
	}
}
