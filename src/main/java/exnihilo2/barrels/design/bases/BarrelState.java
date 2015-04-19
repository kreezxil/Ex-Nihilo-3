package exnihilo2.barrels.design.bases;

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
import exnihilo2.barrels.design.interfaces.IBarrelState;
import exnihilo2.barrels.design.interfaces.IBarrelLogic;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public abstract class BarrelState implements IBarrelState 
{
	private HashMap<String, BarrelLogic> triggers = new HashMap<String, BarrelLogic>();
	
	private String key;
	
	public void setKey(String keyIn)
	{
		this.key = keyIn;
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	public void activate(TileEntityBarrel barrel)
	{
		boolean triggered = false;
		
		if (!triggers.isEmpty())
		{
			for (Map.Entry<String, BarrelLogic> entry : triggers.entrySet()) 
			{
				triggered = entry.getValue().onActivate(barrel);
				
				if (triggered)
					break;
			}
		}
		
		if (!triggered)
			this.onActivate(barrel);
	}
	
	public void update(TileEntityBarrel barrel)
	{
		boolean triggered = false;
		
		if (!triggers.isEmpty())
		{
			for (Map.Entry<String, BarrelLogic> entry : triggers.entrySet()) 
			{
				triggered = entry.getValue().onUpdate(barrel);
				
				if (triggered)
					break;
			}
		}
		
		if (!triggered)
			this.onUpdate(barrel);
	}
	
	public void useItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item)
	{
		boolean triggered = false;

		if (!triggers.isEmpty())
		{
			for (Map.Entry<String, BarrelLogic> entry : triggers.entrySet()) 
			{
				triggered = entry.getValue().onUseItem(player, barrel, item);
				
				if (triggered)
					break;
			}
		}
		
		if (!triggered)
			this.onUseItem(player, barrel, item);
	}
	
	@Override
	public void onActivate(TileEntityBarrel barrel) {}

	@Override
	public void onUpdate(TileEntityBarrel barrel) {}

	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		return false;
	}
	
	@Override
	public void onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {}
	
	public boolean canExtractItem(TileEntityBarrel barrel)
	{
		return false;
	}
	
	public boolean canManipulateFluids(TileEntityBarrel barrel)
	{
		return false;
	}
	
	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {}

	public void registerStateLogic(String key, BarrelLogic trigger) 
	{
		if (trigger != null)
		{
			triggers.put(key, trigger);
		}
	}
	
	public void unregisterStateTrigger(String key) 
	{
		if (key != null && !key.isEmpty() && !key.trim().isEmpty())
		{
			if (triggers.containsKey(key))
			{
				triggers.remove(key);
			}
		}
	}
}
