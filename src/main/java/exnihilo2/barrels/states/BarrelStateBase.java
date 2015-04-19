package exnihilo2.barrels.states;

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
import exnihilo2.barrels.interfaces.IBarrelState;
import exnihilo2.barrels.interfaces.IBarrelStateTrigger;
import exnihilo2.barrels.tileentity.TileEntityBarrel;

public abstract class BarrelStateBase implements IBarrelState 
{
	private static final double MIN_RENDER_CAPACITY = 0.1d;
	private static final double MAX_RENDER_CAPACITY = 0.9d;
	
	private HashMap<String, IBarrelStateTrigger> triggers = new HashMap<String, IBarrelStateTrigger>();
	
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
			for (Map.Entry<String, IBarrelStateTrigger> entry : triggers.entrySet()) 
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
			for (Map.Entry<String, IBarrelStateTrigger> entry : triggers.entrySet()) 
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
			for (Map.Entry<String, IBarrelStateTrigger> entry : triggers.entrySet()) 
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

	public void registerStateTrigger(String key, IBarrelStateTrigger trigger) 
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
	
	protected void renderContentTexture(TextureAtlasSprite texture, double x, double y, double z, double fullness)
	{
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();
		RenderHelper.disableStandardItemLighting();
		
		GlStateManager.translate(x + 0.1d, y + getAdjustedVolume(fullness), z + 0.1d);
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
		renderer.addVertexWithUV(xa + width,	ya,  	za + length, 	maxU, maxV);
		renderer.addVertexWithUV(xa + width,	ya,  	za, 			maxU, minV);
		renderer.addVertexWithUV(xa,  			ya,  	za, 			minU, minV);
		renderer.addVertexWithUV(xa,       		ya,  	za + length, 	minU, maxV);
		tessellator.draw();
		
		RenderHelper.enableStandardItemLighting();
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}
	
	private double getAdjustedVolume(double fullness)
	{
		double capacity = MAX_RENDER_CAPACITY - MIN_RENDER_CAPACITY;
		double adjusted = fullness * capacity;		
		adjusted += MIN_RENDER_CAPACITY;
		return adjusted;
	}
}
