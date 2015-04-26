package exnihilo2.blocks.barrels.states.compost;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.registries.CompostRegistry;
import exnihilo2.registries.recipes.CompostRecipe;
import exnihilo2.util.Color;

public class BarrelStateCompost extends BarrelState{
	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {
		GlStateManager.pushMatrix();
		RenderHelper.disableStandardItemLighting();

		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		TextureAtlasSprite texture = mc.getTextureMapBlocks().getAtlasSprite("exnihilo2:blocks/compost");
		
		GlStateManager.translate(x + 0.125d, y, z + 0.125d);
		GlStateManager.scale(0.75d, 1.0d, 0.75d);

		if (barrel.getBlockType().getMaterial().isOpaque())
		{
			BarrelRenderer.renderContentsSimple(texture, (double)barrel.getVolume() / (double)barrel.getVolumeMax(), barrel.getColor());
		}
		else
		{
			BarrelRenderer.renderContentsComplex(texture, (double)barrel.getVolume() / (double)barrel.getVolumeMax(), barrel.getColor());
		}

		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}
	
	@Override
	public void onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		if (CompostRegistry.isCompostable(item))
		{
			CompostRecipe recipe = CompostRegistry.getRecipe(item);
			
			barrel.setVolume(barrel.getVolume() + recipe.getVolume());
		}
	}
}
