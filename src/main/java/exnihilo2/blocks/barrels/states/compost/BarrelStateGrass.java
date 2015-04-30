package exnihilo2.blocks.barrels.states.compost;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.chunk.Chunk;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.Color;

public class BarrelStateGrass extends BarrelStateCompost{
	private static ItemStack grass = new ItemStack(Blocks.grass, 1);
	
	@Override
	public String getUniqueIdentifier() {
		return "barrel.grass";
	}
	
	@Override
	protected void renderBlockTexture(TileEntityBarrel barrel)
	{
		double timer = barrel.getTimerStatus();

		if (timer > 0.0d)
		{
			BarrelRenderer.renderContentsFromItemStack(grass);
		}
	}
}
