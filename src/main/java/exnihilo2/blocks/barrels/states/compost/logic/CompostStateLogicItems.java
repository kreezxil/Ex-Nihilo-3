package exnihilo2.blocks.barrels.states.compost.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.registries.composting.CompostRegistry;
import exnihilo2.registries.composting.CompostRegistryEntry;
import exnihilo2.util.Color;

public class CompostStateLogicItems extends BarrelLogic{
	
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) 
	{
		if (CompostRegistry.isCompostable(item) && barrel.getVolume() < barrel.getVolumeMax())
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		CompostRegistryEntry recipe = CompostRegistry.getEntryForItemStack(item);
		
		if (recipe != null)
		{
			barrel.setVolume(barrel.getVolume() + recipe.getVolume());
			
			//Set the new color.
			Color colorA = recipe.getColor();
			Color colorB = barrel.getColor();
			
			float weightA = (float)recipe.getVolume() / (float)barrel.getVolume();
			float weightB = 1.0f - weightA;

			float r = weightA * colorA.r + weightB * colorB.r;
			float g = weightA * colorA.g + weightB * colorB.g;
			float b = weightA * colorA.b + weightB * colorB.b;
			float a = weightA * colorA.a + weightB * colorB.a;

			barrel.setColor(new Color(r,g,b,a));
			barrel.requestSync();
		}
		
		consumeItem(player, item);
		barrel.getWorld().playSoundEffect(barrel.getPos().getX() + 0.5f, barrel.getPos().getY() + 0.5f, barrel.getPos().getZ() + 0.5f, "step.snow", 0.7f, 0.15f);
		
		return false;
	}
	
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {
		if (barrel.getVolume() == barrel.getVolumeMax() && barrel.getTimerStatus() == -1)
			barrel.startTimer(1000);

		return false;
	}
}
