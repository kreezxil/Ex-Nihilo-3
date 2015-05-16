package exnihilo2.blocks.barrels.states.compost;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import exnihilo2.EN2;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.registries.compost.CompostRecipeRegistry;
import exnihilo2.registries.recipes.CompostRecipe;
import exnihilo2.util.Color;

public class BarrelStateCompost extends BarrelState{
	protected static Color white = new Color("FFFFFF");
	
	@Override
	public String getUniqueIdentifier() {
		return "barrel.compost";
	}
	
	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {
		GlStateManager.pushMatrix();
		RenderHelper.disableStandardItemLighting();

		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		
		GlStateManager.translate(x + 0.125d, y, z + 0.125d);
		GlStateManager.scale(0.75d, 1.0d, 0.75d);
		
		renderBlockTexture(barrel);
		renderCompostTexture(barrel);

		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}
	
	protected void renderBlockTexture(TileEntityBarrel barrel)
	{
		double timer = barrel.getTimerStatus();

		if (timer > 0.0d)
		{
			TextureAtlasSprite dirt = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/dirt");
			
			if (barrel.getBlockType().getMaterial().isOpaque())
			{
				BarrelRenderer.renderContentsSimple(dirt, (double)barrel.getVolume() / (double)barrel.getVolumeMax(), white);
			}
			else
			{
				BarrelRenderer.renderContentsComplex(dirt, (double)barrel.getVolume() / (double)barrel.getVolumeMax(), white);
			}
		}
	}
	
	protected void renderCompostTexture(TileEntityBarrel barrel)
	{
		double timer = barrel.getTimerStatus();
		
		if (timer < 1.0d)
		{
			TextureAtlasSprite compost = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("exnihilo2:blocks/compost");
			
			Color colorA = barrel.getColor();
			Color colorB = new Color(colorA.r, colorA.g, colorA.b, colorA.a * (1.0f - (float)timer));
			
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			if (barrel.getBlockType().getMaterial().isOpaque())
			{
				BarrelRenderer.renderContentsSimple(compost, (double)barrel.getVolume() / (double)barrel.getVolumeMax(), colorB);
			}
			else
			{
				BarrelRenderer.renderContentsComplex(compost, (double)barrel.getVolume() / (double)barrel.getVolumeMax(), colorB);
			}
		}
	}
}
