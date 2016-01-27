package exnihilo2.blocks.barrels.renderer;

import org.lwjgl.opengl.GL11;

import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.Color;
import exnihilo2.util.helpers.ContentRenderHelper;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
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
	
	public static void renderContentsSimple(TextureAtlasSprite texture, double height, Color color)
	{
		height = ContentRenderHelper.getAdjustedContentLevel(MIN_RENDER_CAPACITY, MAX_RENDER_CAPACITY, height);
		
		ContentRenderHelper.renderContentsSimple(texture, height, color);
	}

	public static void renderContentsComplex(TextureAtlasSprite texture, double height, Color color)
	{
		renderContentsMultiTexture(texture, texture, texture, height, color);
	}
	
	public static void renderContentsMultiTexture(TextureAtlasSprite topTexture, TextureAtlasSprite sideTexture, TextureAtlasSprite bottomTexture, double height, Color color)
	{
		GlStateManager.pushMatrix();
		
		double heightA = getAdjustedContentLevel(height);
		
		Vec3[] vertices = 
			{
				new Vec3(1.0d, heightA, 1.0d),
				new Vec3(1.0d, heightA, 0),
				new Vec3(0, heightA, 0),
				new Vec3(0, heightA, 1.0d),
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
		GlStateManager.color(color.r, color.g, color.b, color.a);
		
		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		renderTexturedQuad(renderer, topTexture, top, color, 1.0d);
		renderTexturedQuad(renderer, bottomTexture, bottom, color, 1.0d);
		renderTexturedQuad(renderer, sideTexture, north, color, height);
		renderTexturedQuad(renderer, sideTexture, east, color, height);
		renderTexturedQuad(renderer, sideTexture, south, color, height);
		renderTexturedQuad(renderer, sideTexture, west, color, height);
		tessellator.draw();
		
		GlStateManager.popMatrix();
	}
	
	private static void renderTexturedQuad(WorldRenderer renderer, TextureAtlasSprite texture, Vec3[] vertices, Color color, double contentHeight)
	{
		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getInterpolatedV(texture.getIconHeight() - (texture.getIconHeight() * contentHeight));
		double maxV = (double)texture.getMaxV();
		
		renderer.pos(vertices[0].xCoord, vertices[0].yCoord, vertices[0].zCoord).tex(maxU, maxV).endVertex();
		renderer.pos(vertices[1].xCoord, vertices[1].yCoord, vertices[1].zCoord).tex(maxU, minV).endVertex();
		renderer.pos(vertices[2].xCoord, vertices[2].yCoord, vertices[2].zCoord).tex(minU, minV).endVertex();
		renderer.pos(vertices[3].xCoord, vertices[3].yCoord, vertices[3].zCoord).tex(minU, maxV).endVertex();
	}
	
	public static void renderContentsFromItemStack(ItemStack item)
	{
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		
		GlStateManager.translate(0.5d, 0.5d, 0.5d);
		GlStateManager.scale(0.999d, 0.8999d, 0.999d);

		Minecraft.getMinecraft().getItemRenderer().renderItem(null, item, TransformType.NONE);
		
		GlStateManager.popMatrix();
	}
	
	public static double getAdjustedContentLevel(double fullness)
	{
		double capacity = MAX_RENDER_CAPACITY - MIN_RENDER_CAPACITY;
		double adjusted = fullness * capacity;		
		adjusted += MIN_RENDER_CAPACITY;
		return adjusted;
	}
}
