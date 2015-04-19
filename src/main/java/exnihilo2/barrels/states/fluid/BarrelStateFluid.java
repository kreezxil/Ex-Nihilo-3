package exnihilo2.barrels.states.fluid;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
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
import exnihilo2.barrels.design.bases.BarrelState;
import exnihilo2.barrels.renderer.RendererBarrel;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public class BarrelStateFluid extends BarrelState{

	@Override
	public void onUpdate(TileEntityBarrel barrel) {
		if (barrel.getFluid() != null)
		{
			World world = barrel.getWorld();
			
			//set the light level. Just in case the fluid has changed.
			barrel.setLuminosity(barrel.getFluid().getFluid().getLuminosity());
		}
	}

	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = barrel.getFluid();
		FluidStack ifluid = FluidContainerRegistry.getFluidForFilledItem(item);

		if (fluid != null )
		{
			if (ifluid != null && barrel.fill(ifluid, false) > 0)
			{
				return true;
			}
			
			if (FluidContainerRegistry.isEmptyContainer(item) && fluid.amount >= barrel.getCapacity())
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = barrel.getFluid();
		FluidStack ifluid = FluidContainerRegistry.getFluidForFilledItem(item);

		if (fluid != null )
		{
			if (ifluid != null && barrel.fill(ifluid, false) > 0)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, getContainer(item));
				barrel.fill(ifluid, true);
			}
			
			if (FluidContainerRegistry.isEmptyContainer(item) && fluid.amount >= barrel.getCapacity())
			{
				ItemStack full = FluidContainerRegistry.fillFluidContainer(fluid, item);
				
				if (full != null)
				{
					if (item.stackSize > 1) 
					{
						if (player.inventory.addItemStackToInventory(full)) 
						{
							item.stackSize -= 1;
						}
					} 
					else 
					{
						player.inventory.setInventorySlotContents(player.inventory.currentItem, full);
					}
					
					barrel.drain(barrel.getCapacity(), true);
				}
			}
		}
	}
	
	@Override
	public boolean canManipulateFluids(TileEntityBarrel barrel) {
		return true;
	}

	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {
		FluidStack fluid = barrel.getFluid();
		
		if (fluid != null)
		{
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			Minecraft mc = Minecraft.getMinecraft();
			mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			TextureAtlasSprite texture = fluid.getFluid().getIcon();
			
			RendererBarrel.renderContentTexture(texture, x, y, z, (double)barrel.getFluidAmount() / (double)barrel.getCapacity());
		}
	}
	
	public static ItemStack getContainer(ItemStack item)
	{
		if (item.stackSize == 1) 
		{
			if (item.getItem().hasContainerItem(item)) 
			{
				return item.getItem().getContainerItem(item);
			} else 
			{
				return null;
			}
		} else 
		{
			item.splitStack(1);
			return item;
		}
	}
}
