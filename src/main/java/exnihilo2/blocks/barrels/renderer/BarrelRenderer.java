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
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IFlexibleBakedModel;

public class BarrelRenderer extends TileEntitySpecialRenderer {
	public static final double MIN_RENDER_CAPACITY = 0.05d;
	public static final double MAX_RENDER_CAPACITY = 0.95d;
	
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
	
	public static void renderFluidSimple(TextureAtlasSprite texture, double height, Color color)
	{
		GlStateManager.pushMatrix();
		
		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		height = getAdjustedContentLevel(height);
		
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

	public static void renderFluidComplex(TextureAtlasSprite texture, double height, Color color)
	{
		GlStateManager.pushMatrix();

		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		height = getAdjustedContentLevel(height);
		
		Vec3[] vertices = 
			{
				new Vec3(1.0d, height, 1.0d),
				new Vec3(1.0d, height, 0),
				new Vec3(0, height, 0),
				new Vec3(0, height, 1.0d),
				new Vec3(0, MIN_RENDER_CAPACITY, 1.0d),
				new Vec3(0, MIN_RENDER_CAPACITY, 0),
				new Vec3(1.0d, MIN_RENDER_CAPACITY, 0),
				new Vec3(1.0d, MIN_RENDER_CAPACITY, 1.0d)
			};
		
		Vec3[] top = 
			{
				vertices[0],
				vertices[1],
				vertices[2],
				vertices[3]
			};
		
		Vec3[] bottom = 
			{
				vertices[4],
				vertices[5],
				vertices[6],
				vertices[7]
			};
		
		Vec3[] north = 
			{
				vertices[5],
				vertices[2],
				vertices[1],
				vertices[6]
			};
		
		Vec3[] east = 
			{
				vertices[6],
				vertices[1],
				vertices[0],
				vertices[7]
			};
		
		Vec3[] south = 
			{
				vertices[7],
				vertices[0],
				vertices[3],
				vertices[4]
			};
		
		Vec3[] west = 
			{
				vertices[4],
				vertices[3],
				vertices[2],
				vertices[5]
			};
		
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer renderer = tessellator.getWorldRenderer();
		
		renderer.startDrawingQuads();
		renderer.setColorRGBA_F(color.r, color.g, color.b, color.a);
		renderTexturedQuad(renderer, texture, top, color);
		renderTexturedQuad(renderer, texture, bottom, color);
		renderTexturedQuad(renderer, texture, north, color);
		renderTexturedQuad(renderer, texture, east, color);
		renderTexturedQuad(renderer, texture, south, color);
		renderTexturedQuad(renderer, texture, west, color);
		tessellator.draw();
		
		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}
	
	private static void renderTexturedQuad(WorldRenderer renderer, TextureAtlasSprite texture, Vec3[] vertices, Color color)
	{
		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		renderer.addVertexWithUV(vertices[0].xCoord, vertices[0].yCoord, vertices[0].zCoord, maxU, maxV);
		renderer.addVertexWithUV(vertices[1].xCoord, vertices[1].yCoord, vertices[1].zCoord, maxU, minV);
		renderer.addVertexWithUV(vertices[2].xCoord, vertices[2].yCoord, vertices[2].zCoord, minU, minV);
		renderer.addVertexWithUV(vertices[3].xCoord, vertices[3].yCoord, vertices[3].zCoord, minU, maxV);
	}
	
	public static double getAdjustedContentLevel(double fullness)
	{
		double capacity = MAX_RENDER_CAPACITY - MIN_RENDER_CAPACITY;
		double adjusted = fullness * capacity;		
		adjusted += MIN_RENDER_CAPACITY;
		return adjusted;
	}
}
