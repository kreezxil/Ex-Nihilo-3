package exnihilo2.blocks.barrels.states.fluid;

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
	@Override
	public boolean canManipulateFluids(TileEntityBarrel barrel) {
		return true;
	}

	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {
		FluidStack fluid = barrel.getFluid();

		if (fluid != null)
		{
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			Minecraft mc = Minecraft.getMinecraft();
			mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			TextureAtlasSprite texture = fluid.getFluid().getIcon();

			BarrelRenderer.renderContentTexture(texture, x, y, z, (double)barrel.getFluidAmount() / (double)barrel.getCapacity());
		}
	}

	public void drawFluidCube(TextureAtlasSprite texture, double x, double y, double z, double fullness, Color color)
	{
		GlStateManager.pushMatrix();
		RenderHelper.disableStandardItemLighting();

		GlStateManager.translate(x + 0.1d, y + BarrelRenderer.getAdjustedVolume(fullness), z + 0.1d);
		GlStateManager.scale(0.8d, 1.0d, 0.8d);

		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();

		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer renderer = tessellator.getWorldRenderer();
		
		renderPlane(texture, x, y, z, fullness, color);
		
		GlStateManager.popMatrix();
	}
	
	public void renderPlane(TextureAtlasSprite texture, double x, double y, double z, double fullness, Color color)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer renderer = tessellator.getWorldRenderer();
		
		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		renderer.startDrawingQuads();
		renderer.setColorRGBA_F(color.r, color.g, color.b, color.a);
		
		renderer.addVertexWithUV(1,	0,  1, 	maxU, maxV);
		renderer.addVertexWithUV(1,	0,  0, 	maxU, minV);
		renderer.addVertexWithUV(0, 0,  0, 	minU, minV);
		renderer.addVertexWithUV(0, 0,  1, 	minU, maxV);
		tessellator.draw();
	}
}
