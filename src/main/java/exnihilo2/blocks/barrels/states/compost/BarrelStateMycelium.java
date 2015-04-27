package exnihilo2.blocks.barrels.states.compost;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.registries.recipes.CompostRecipe;
import exnihilo2.util.enums.MetadataBehavior;

public class BarrelStateMycelium extends BarrelStateCompost{

	@Override
	public String getUniqueIdentifier() {
		return "barrel.mycelium";
	}
	
	@Override
	protected void renderBlockTexture(TileEntityBarrel barrel)
	{
		double timer = barrel.getTimerStatus();

		if (timer > 0.0d)
		{
			TextureAtlasSprite top = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/mycelium_top");
			TextureAtlasSprite side = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/mycelium_side");
			TextureAtlasSprite bottom = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/dirt");
			
			if (barrel.getBlockType().getMaterial().isOpaque())
			{
				BarrelRenderer.renderContentsSimple(top, (double)barrel.getVolume() / (double)barrel.getVolumeMax(), white);
			}
			else
			{
				BarrelRenderer.renderContentsMultiTexture(top, side, bottom, (double)barrel.getVolume() / (double)barrel.getVolumeMax(), white);

			}
		}
	}
	

}
