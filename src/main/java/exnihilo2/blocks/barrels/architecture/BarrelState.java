package exnihilo2.blocks.barrels.architecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.blocks.barrels.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public abstract class BarrelState
{
	private ArrayList<BarrelLogic> triggers = new ArrayList<BarrelLogic>();
	
	public abstract String getUniqueIdentifier();
	
	public BarrelState()
	{
		super();
		
		BarrelStates.registerState(this);
	}
	
	public void activate(TileEntityBarrel barrel)
	{
		boolean triggered = false;

		for (BarrelLogic entry : triggers) 
		{
			triggered = entry.onActivate(barrel);

			if (triggered)
				break;
		}

		if (!triggered)
			this.onActivate(barrel);
	}
	
	public void update(TileEntityBarrel barrel)
	{
		boolean triggered = false;

		for (BarrelLogic entry : triggers) 
		{
			triggered = entry.onUpdate(barrel);

			if (triggered)
				break;
		}

		if (!triggered)
			this.onUpdate(barrel);
	}
	
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item)
	{
		for (BarrelLogic entry : triggers) 
		{
			if (entry.canUseItem(barrel, item))
				return true;
		}

		return false;
	}
	
	public void useItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item)
	{
		boolean triggered = false;

		for (BarrelLogic entry : triggers) 
		{
			if (entry.canUseItem(barrel, item))
			{
				triggered = entry.onUseItem(player, barrel, item);

				if (triggered)
				{
					barrel.getWorld().markBlockForUpdate(barrel.getPos());

					break;
				}
			}
		}

		if (!triggered)
		{
			this.onUseItem(player, barrel, item);
		}
	}
	
	public void onActivate(TileEntityBarrel barrel) {}

	public void onUpdate(TileEntityBarrel barrel) {}
	
	public void onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {}
	
	public boolean canExtractItem(TileEntityBarrel barrel)
	{
		return false;
	}
	
	public boolean canManipulateFluids(TileEntityBarrel barrel)
	{
		return false;
	}

	public void render(TileEntityBarrel barrel, double x, double y, double z) {}

	public void addLogic(BarrelLogic logic) 
	{
		if (logic != null)
		{
			triggers.add(logic);
		}
	}

	public void removeLogic(BarrelLogic logic) 
	{
		triggers.remove(logic);
	}
}
