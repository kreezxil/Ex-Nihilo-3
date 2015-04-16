package exnihilo2.barrels.bases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.barrels.interfaces.IBarrelState;
import exnihilo2.barrels.interfaces.IBarrelStateTrigger;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public abstract class BaseBarrelState implements IBarrelState {
	
	private HashMap<String, IBarrelStateTrigger> triggers = new HashMap<String, IBarrelStateTrigger>();
	
	//This must be unique!
	public abstract String getBarrelStateKey();
	
	public void activate(TileEntityBarrel barrel)
	{
		if (!triggers.isEmpty())
		{
			for (Map.Entry<String, IBarrelStateTrigger> entry : triggers.entrySet()) 
			{
				entry.getValue().activate(barrel);
			}
		}
		
		this.onActivate(barrel);
	}
	
	public void update(TileEntityBarrel barrel)
	{
		if (!triggers.isEmpty())
		{
			for (Map.Entry<String, IBarrelStateTrigger> entry : triggers.entrySet()) 
			{
				entry.getValue().update(barrel);
			}
		}
		
		this.onUpdate(barrel);
	}
	
	public void useItem(TileEntityBarrel barrel, ItemStack item)
	{
		if (!triggers.isEmpty())
		{
			for (Map.Entry<String, IBarrelStateTrigger> entry : triggers.entrySet()) 
			{
				entry.getValue().itemUse(barrel, item);
			}
		}
		
		this.onItemUse(barrel, item);
	}

	public void useFluid(TileEntityBarrel barrel, FluidStack fluid)
	{
		if (!triggers.isEmpty())
		{
			for (Map.Entry<String, IBarrelStateTrigger> entry : triggers.entrySet()) 
			{
				entry.getValue().fluidUse(barrel, fluid);
			}
		}
		
		this.onFluidUse(barrel, fluid);
	}
	
	@Override
	public void onActivate(TileEntityBarrel barrel) {}

	@Override
	public void onUpdate(TileEntityBarrel barrel) {}

	@Override
	public void onItemUse(TileEntityBarrel barrel, ItemStack item) {}

	@Override
	public void onFluidUse(TileEntityBarrel barrel, FluidStack fluid) {}
	
	@Override
	public void render(TileEntityBarrel barrel, double x, double y, double z) {}

	public void registerStateTrigger(IBarrelStateTrigger trigger) 
	{
		if (trigger != null)
		{
			triggers.put(trigger.getStateTriggerID(), trigger);
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
	
	protected void renderContentTexture(TextureAtlasSprite texture, double x, double y, double z, double fullness)
	{
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x + 0.1d, y + fullness, z + 0.1d);
		GlStateManager.scale(0.8d, 1.0d, 0.8d);
		
		double length = 1.0d;
		double width = 1.0d;
		
		double xa = 0;
		double ya = 0;
		double za = 0;
		
		double minU = (double)texture.getMinU();
		double maxU = (double)texture.getMaxU();
		double minV = (double)texture.getMinV();
		double maxV = (double)texture.getMaxV();
		
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer renderer = tessellator.getWorldRenderer();
		
		renderer.startDrawingQuads();
		renderer.setColorRGBA_F(1, 1, 1, 1);
		renderer.addVertexWithUV(xa + width,	ya,  za + length, maxU, maxV);
		renderer.addVertexWithUV(xa + width, ya,  za, 			maxU, minV);
		renderer.addVertexWithUV(xa,  		ya,  za, 			minU, minV);
		renderer.addVertexWithUV(xa,       	ya,  za + length, minU, maxV);
		tessellator.draw();
		
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}
}
