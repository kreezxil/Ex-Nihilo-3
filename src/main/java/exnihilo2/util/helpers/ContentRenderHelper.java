package exnihilo2.util.helpers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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
		
		renderer.startDrawingQuads();
		renderer.setColorRGBA_F(color.r, color.g, color.b, color.a);
		renderer.addVertexWithUV(1.0d, 	height, 	1.0d, 	maxU, maxV);
		renderer.addVertexWithUV(1.0d, 	height, 	0, 		maxU, minV);
		renderer.addVertexWithUV(0, 	height, 	0, 		minU, minV);
		renderer.addVertexWithUV(0, 	height, 	1.0d, 	minU, maxV);
		tessellator.draw();
		
		GlStateManager.popMatrix();
	}
}
