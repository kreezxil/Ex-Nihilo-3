package exnihilo2.barrels.states;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import exnihilo2.EN2;
import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.interfaces.IBarrelState;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

//Does nothing. Renders nothing. Is simply a container for triggers.
public class BarrelStateEmpty extends BaseBarrelState implements IBarrelState{

	@Override
	public boolean canManipulateFluids(TileEntityBarrel barrel) {
		return true;
	}
}
