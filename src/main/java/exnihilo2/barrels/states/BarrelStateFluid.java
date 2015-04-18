package exnihilo2.barrels.states;

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
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public class BarrelStateFluid extends BaseBarrelState{

	@Override
	public void onUpdate(TileEntityBarrel barrel) {
		if (barrel.getFluid() != null)
		{
			World world = barrel.getWorld();
			
			//set the light level. Just in case the fluid has changed.
			barrel.setLuminosity(barrel.getFluid().getFluid().getLuminosity());
			
			//if the fluid is gaseous...
			if(barrel.getFluid().getFluid().isGaseous())
			{
				//and the space above the barrel is empty...
				BlockPos pos = barrel.getPos();
				BlockPos above = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
				
				if(world.isAirBlock(above))
				{
					//float free little cloud dude!
					Block fblock = barrel.getFluid().getFluid().getBlock();
					
					world.setBlockState(above, fblock.getDefaultState(), 3);
					world.notifyBlockOfStateChange(above, fblock);
					
					barrel.drain(barrel.getCapacity(), true);
				}
			}
			
			//if the fluid is hot...
			if(barrel.getFluid().getFluid().getTemperature() > 400)
			{
				//and the barrel is flammable...
				if (world.getBlockState(barrel.getPos()).getBlock().getMaterial().getCanBurn())
				{
					//buuuurn baby burn!
					if (barrel.getTimerStatus() == -1.0d)
					{
						barrel.startTimer(400);
					}
					
					if (barrel.getTimerStatus() > 0 && barrel.getTimerStatus() < 1.0d)
					{
						BlockPos pos = barrel.getPos();

						if (barrel.getTimerTime() % 30 == 0)
						{
							world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + Math.random(), (double)pos.getY() + 1.2D, (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
						}

						if (barrel.getTimerTime() % 5 == 0)
						{
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)pos.getX() + Math.random(), (double)pos.getY() + 1.2D, (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
						}
					}
						
					if (barrel.getTimerStatus() == 1.0d)
					{
						if (barrel.getFluidAmount() < barrel.getCapacity())
						{
							BlockPos pos = barrel.getPos();
							BlockPos above = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
							
							if (world.isAirBlock(above))
							{
								world.setBlockState(above, Blocks.fire.getDefaultState(), 3);
							}
						}
						else
						{
							Block fblock = barrel.getFluid().getFluid().getBlock();
							
							world.setBlockState(barrel.getPos(), fblock.getDefaultState(), 3);
							world.notifyBlockOfStateChange(barrel.getPos(), fblock);
						}
					}
				}
			}
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
		
		return super.canUseItem(barrel, item);
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
		
		super.onUseItem(player, barrel, item);
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
			GlStateManager.pushAttrib();
			
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			Minecraft mc = Minecraft.getMinecraft();
			mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			TextureAtlasSprite texture = fluid.getFluid().getIcon();
			
			renderContentTexture(texture, x, y, z, (double)barrel.getFluidAmount() / (double)barrel.getCapacity());
			
			GlStateManager.popAttrib();
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
