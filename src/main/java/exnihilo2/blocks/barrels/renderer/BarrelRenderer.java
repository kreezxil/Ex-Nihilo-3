package exnihilo2.blocks.barrels.renderer;

import org.lwjgl.opengl.GL11;

import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.Color;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IFlexibleBakedModel;

public class BarrelRenderer extends TileEntitySpecialRenderer {
	public static final double MIN_RENDER_CAPACITY = 0.1d;
	public static final double MAX_RENDER_CAPACITY = 0.9d;
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f, int i) 
	{
		TileEntityBarrel barrel = (TileEntityBarrel) te;
		BarrelState state = barrel.getState();
		
		if (state != null)
		{
			state.render(barrel, x, y, z);
		}
	}
	
	public static void renderContentTexture(TextureAtlasSprite texture, double x, double y, double z, double fullness)
	{
		renderContentTexture(texture, x, y, z, fullness, new Color("FFFFFF"));
	}
	
	public static void renderContentTexture(TextureAtlasSprite texture, double x, double y, double z, double fullness, Color color)
	{
		GlStateManager.pushMatrix();
		RenderHelper.disableStandardItemLighting();
		
		GlStateManager.translate(x + 0.1d, y + getAdjustedVolume(fullness), z + 0.1d);
		GlStateManager.scale(0.8d, 1.0d, 0.8d);
		
		double length = 1.0d;
		double width = 1.0d;
		
		double xa = 0;
		double ya = 0;
		double za = 0;
		
		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer renderer = tessellator.getWorldRenderer();
		
		renderer.startDrawingQuads();
		renderer.setColorRGBA_F(color.r, color.g, color.b, color.a);
		renderer.addVertexWithUV(xa + width,	ya,  	za + length, 	maxU, maxV);
		renderer.addVertexWithUV(xa + width,	ya,  	za, 			maxU, minV);
		renderer.addVertexWithUV(xa,  			ya,  	za, 			minU, minV);
		renderer.addVertexWithUV(xa,       		ya,  	za + length, 	minU, maxV);
		tessellator.draw();
		
		GlStateManager.popMatrix();
	}
	
	public static double getAdjustedVolume(double fullness)
	{
		double capacity = MAX_RENDER_CAPACITY - MIN_RENDER_CAPACITY;
		double adjusted = fullness * capacity;		
		adjusted += MIN_RENDER_CAPACITY;
		return adjusted;
	}
}
