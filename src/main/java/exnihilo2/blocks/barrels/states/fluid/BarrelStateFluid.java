package exnihilo2.blocks.barrels.states.fluid;

import java.util.HashSet;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.Color;

public class BarrelStateFluid extends BarrelState{
	public static final double MIN_RENDER_CAPACITY = 0.05d;
	public static final double MAX_RENDER_CAPACITY = 0.95d;
	
	@Override
	public boolean canManipulateFluids(TileEntityBarrel barrel) {
		return true;
	}

	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {
		FluidStack fluid = barrel.getFluid();

		if (fluid != null)
		{
			GlStateManager.pushMatrix();
			
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			Minecraft mc = Minecraft.getMinecraft();
			mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			TextureAtlasSprite texture = fluid.getFluid().getIcon();

			if (barrel.getBlockType().getMaterial().isOpaque())
			{
				drawFluidSimple(texture, x, y, z, (double)barrel.getFluidAmount() / (double)barrel.getCapacity(), new Color("FFFFFF"));
			}
			else
			{
				drawFluidComplex(texture, x, y, z, (double)barrel.getFluidAmount() / (double)barrel.getCapacity(), new Color("FFFFFF"));
			}
			
			
			GlStateManager.popMatrix();
		}
	}
	
	private static void drawFluidSimple(TextureAtlasSprite texture, double x, double y, double z, double fullness, Color color)
	{
		GlStateManager.pushMatrix();
		RenderHelper.disableStandardItemLighting();
		
		GlStateManager.translate(x + 0.125d, y + getAdjustedVolume(fullness), z + 0.125d);
		GlStateManager.scale(0.75d, 1.0d, 0.75d);
		
		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer renderer = tessellator.getWorldRenderer();
		
		renderer.startDrawingQuads();
		renderer.setColorRGBA_F(color.r, color.g, color.b, color.a);
		renderer.addVertexWithUV(1.0d, 	0, 	1.0d, 	maxU, maxV);
		renderer.addVertexWithUV(1.0d, 	0, 	0, 		maxU, minV);
		renderer.addVertexWithUV(0, 	0, 	0, 		minU, minV);
		renderer.addVertexWithUV(0, 	0, 	1.0d, 	minU, maxV);
		tessellator.draw();
		
		GlStateManager.popMatrix();
	}

	private static void drawFluidComplex(TextureAtlasSprite texture, double x, double y, double z, double fullness, Color color)
	{
		GlStateManager.pushMatrix();
		RenderHelper.disableStandardItemLighting();

		GlStateManager.translate(x + 0.125d, y, z + 0.125d);
		GlStateManager.scale(0.75d, 1.0d, 0.75d);

		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		double height = getAdjustedVolume(fullness);
		
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
		drawPlane(renderer, texture, top, fullness, color);
		drawPlane(renderer, texture, bottom, fullness, color);
		drawPlane(renderer, texture, north, fullness, color);
		drawPlane(renderer, texture, east, fullness, color);
		drawPlane(renderer, texture, south, fullness, color);
		drawPlane(renderer, texture, west, fullness, color);
		tessellator.draw();
		
		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}
	
	private static void drawPlane(WorldRenderer renderer, TextureAtlasSprite texture, Vec3[] vertices, double fullness, Color color)
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
	
	private static double getAdjustedVolume(double fullness)
	{
		double capacity = MAX_RENDER_CAPACITY - MIN_RENDER_CAPACITY;
		double adjusted = fullness * capacity;		
		adjusted += MIN_RENDER_CAPACITY;
		return adjusted;
	}
}
