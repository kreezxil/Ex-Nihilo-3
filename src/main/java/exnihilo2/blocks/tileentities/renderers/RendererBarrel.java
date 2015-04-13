package exnihilo2.blocks.tileentities.renderers;

import org.lwjgl.opengl.GL11;

import exnihilo2.blocks.tileentities.TileEntityBarrel;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IFlexibleBakedModel;

public class RendererBarrel extends TileEntitySpecialRenderer {
	//TODO: Fetch barrel texture from the TE state.
    private static final ResourceLocation texture = new ResourceLocation("exnihilo2:textures/blocks/oak_barrel.png");

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f, int i) 
	{
		TileEntityBarrel barrel = (TileEntityBarrel) te;
//		Minecraft mc = Minecraft.getMinecraft();
//		
//		GL11.glPushMatrix();
//		//GL11.glTranslatef((float)x + 0.5F,(float)y + 1.5F,(float)z + 0.5F);
//		GL11.glTranslatef((float)x + 0.5F,(float)y + 3.5F,(float)z + 0.5F);
//		GL11.glScalef(-0.8F, -1F, 0.8F);
//
//		bindTexture(texture);
//		model.simpleRender(0.0625F);
//		
//		GL11.glPopMatrix();
	}
}
