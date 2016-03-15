package exnihilo2.util.helpers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import exnihilo2.util.Color;

public class ContentRenderHelper 
{
	public static double getAdjustedContentLevel(double min, double max, double fullness)
	{
		double capacity = max - min;
		double adjusted = fullness * capacity;		
		adjusted += min;
		
		return adjusted;
	}
	
	public static void renderContentsSimple(TextureAtlasSprite texture, double height, Color color)
	{
		GlStateManager.pushMatrix();
		
		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer renderer = tessellator.getWorldRenderer();
		GlStateManager.color(color.r, color.g, color.b, color.a);
		
		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		renderer.pos(1.0d, 	height, 	1.0d).tex(maxU, maxV).endVertex();
		renderer.pos(1.0d, 	height, 	0).tex(maxU, minV).endVertex();
		renderer.pos(0, 	height, 	0).tex(minU, minV).endVertex();
		renderer.pos(0, 	height, 	1.0d).tex(minU, maxV).endVertex();
		tessellator.draw();
		
		GlStateManager.popMatrix();
	}
}
