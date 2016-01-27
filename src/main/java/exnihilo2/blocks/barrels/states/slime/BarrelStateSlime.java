package exnihilo2.blocks.barrels.states.slime;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.client.textures.files.TextureLocator;
import exnihilo2.util.Color;

public class BarrelStateSlime extends BarrelState{
	private Color white = new Color("FFFFFF");
	private Color slime = new Color("33ff22");
	private static String[] description = new String[]{""};
	
	@Override
	public String getUniqueIdentifier() {
		return "barrel.slime.green";
	}
	
	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {
		FluidStack fluid = barrel.getFluid();

		if (fluid != null && fluid.getFluid() != null)
		{
			GlStateManager.pushMatrix();
			RenderHelper.disableStandardItemLighting();
			
			GlStateManager.disableBlend();

			Minecraft mc = Minecraft.getMinecraft();
			mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			TextureAtlasSprite water = TextureLocator.find(fluid.getFluid().getStill());

			GlStateManager.translate(x + 0.125d, y, z + 0.125d);
			GlStateManager.scale(0.75d, 1.0d, 0.75d);
			
			if (barrel.getBlockType().getMaterial().isOpaque())
			{
				BarrelRenderer.renderContentsSimple(water, 1.0d, Color.average(white, slime, (float)barrel.getTimerStatus()));
			}
			else
			{
				BarrelRenderer.renderContentsComplex(water, 1.0d, Color.average(white, slime, (float)barrel.getTimerStatus()));
			}
			
			RenderHelper.enableStandardItemLighting();
			GlStateManager.popMatrix();
		}
	}
	
	@Override
	public String[] getWailaBody(TileEntityBarrel barrel)
	{
		if (barrel.getTimerStatus() >= 0 && barrel.getTimerStatus() < 1.0d )
		{
			description[0] = "Growing Slime " + String.format("%.0f", barrel.getTimerStatus() * 100) + "%";
			return description;
		}
		else if (barrel.getTimerStatus() >= 1.0d)
		{
			description[0] = "Slime Wants Out!";
			return description;
		}
		
		return null;
	}
}
