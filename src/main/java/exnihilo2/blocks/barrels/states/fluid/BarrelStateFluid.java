package exnihilo2.blocks.barrels.states.fluid;

import java.util.HashSet;

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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
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
	private static String[] description = new String[]{""};
	
	@Override
	public String getUniqueIdentifier() {
		return "barrel.fluid";
	}
	
	@Override
	public boolean canManipulateFluids(TileEntityBarrel barrel) {
		return true;
	}
	
	@Override
	public int getLuminosity(TileEntityBarrel barrel)
	{
		FluidStack fluid = barrel.getFluid();
		
		if (fluid != null)
			return fluid.getFluid().getLuminosity();
		
		return 0;
	}

	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {
		FluidStack fluid = barrel.getFluid();

		if (fluid != null && fluid.getFluid() != null)
		{
			ResourceLocation still = fluid.getFluid().getStill();
			
			if (still != null)
			{
				GlStateManager.pushMatrix();
				RenderHelper.disableStandardItemLighting();
				
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

				Minecraft mc = Minecraft.getMinecraft();
				mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

				TextureAtlasSprite texture = mc.getTextureMapBlocks().getAtlasSprite(still.toString());

				GlStateManager.translate(x + 0.125d, y, z + 0.125d);
				GlStateManager.scale(0.75d, 1.0d, 0.75d);
				
				if (barrel.getBlockType().getMaterial().isOpaque())
				{
					BarrelRenderer.renderContentsSimple(texture, (double)barrel.getFluidAmount() / (double)barrel.getCapacity(), new Color("FFFFFF"));
				}
				else
				{
					BarrelRenderer.renderContentsComplex(texture, (double)barrel.getFluidAmount() / (double)barrel.getCapacity(), new Color("FFFFFF"));
				}
				
				RenderHelper.enableStandardItemLighting();
				GlStateManager.popMatrix();
			}
		}
	}
	
	@Override
	public String[] getWailaBody(TileEntityBarrel barrel)
	{
		if (barrel.getFluid() != null)
		{
			description[0] = barrel.getFluid().getLocalizedName();
			return description;
		}
		else
		{
			return null;
		}
	}
}
