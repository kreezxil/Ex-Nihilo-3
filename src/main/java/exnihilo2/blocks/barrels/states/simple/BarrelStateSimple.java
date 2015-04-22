package exnihilo2.blocks.barrels.states.simple;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
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

public class BarrelStateSimple extends BarrelState{	
	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {
		ItemStack contents = barrel.getContents();

		if (contents != null)
		{
			GlStateManager.pushMatrix();
			
			GlStateManager.translate(x + 0.5d, y + 0.5d, z + 0.5d);
			GlStateManager.scale(0.75d, 0.9d, 0.75d);

			Minecraft.getMinecraft().getItemRenderer().renderItem(null, contents, TransformType.NONE);

			GlStateManager.popMatrix();
		}
	}
}
