package exnihilo2.blocks.barrels.states.compost;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.enums.MetadataBehavior;

public class BarrelStateCoarseDirt extends BarrelStateCompostSpecial{
	
	public BarrelStateCoarseDirt()
	{
		super();
		
		addIngredient(new ItemStack(Items.rotten_flesh, 1), MetadataBehavior.IGNORED);
	}

	@Override
	public String getUniqueIdentifier() {
		return "barrel.coarse_dirt";
	}
	
	@Override
	protected void renderBlockTexture(TileEntityBarrel barrel)
	{
		double timer = barrel.getTimerStatus();

		if (timer > 0.0d)
		{
			TextureAtlasSprite dirt = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/coarse_dirt");
			
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
}
