package exnihilo2.blocks.sieves.renderer;

import org.lwjgl.opengl.GL11;

import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import exnihilo2.util.Color;
import exnihilo2.util.helpers.ContentRenderHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SieveRenderer extends TileEntitySpecialRenderer{
	public static final double MIN_RENDER_CAPACITY = 0.58d;
	public static final double MAX_RENDER_CAPACITY = 0.95d;
	private static EntityLivingBase entity;
	
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
			renderContents(sieve);
			
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
			ContentRenderHelper.renderContentsSimple(mesh, MIN_RENDER_CAPACITY, Color.WHITE);
			GlStateManager.enableCull();
		}
	}
	
	private void renderContents(TileEntitySieve sieve)
	{
		ItemStack contents = sieve.getContents();
		
		if (contents != null && Block.getBlockFromItem(contents.getItem()) != null)
		{
		  if (entity == null)
		  {
		    entity = new EntityCreeper(sieve.getWorld());
		  }
		  
			double top = ContentRenderHelper.getAdjustedContentLevel(MIN_RENDER_CAPACITY, MAX_RENDER_CAPACITY, 1.0d - (double)sieve.getProgress());
			double height = top - MIN_RENDER_CAPACITY;
			
			GlStateManager.translate(0.5d, 0.5d, 0.5d); //Get the block place correctly.
			GlStateManager.translate(0.0d, MIN_RENDER_CAPACITY + 0.01d - ((1.0d - height) * 0.5d), 0.0d); //Lift the block into the sifting box.
			GlStateManager.scale(0.94d, height, 0.94d); //Adjust the height to fit the progress.
			
			Minecraft.getMinecraft().getItemRenderer().renderItem(entity, contents, TransformType.NONE);
		}
	}
}
