package exnihilo2.blocks.barrels.states.compost.logic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import exnihilo2.blocks.barrels.BarrelStates;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.compost.BarrelStateMycelium;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.registries.CompostRegistry;
import exnihilo2.registries.recipes.CompostRecipe;

public class MyceliumStateTrigger extends BarrelLogic{
	
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) 
	{
		if (barrel.getVolume() < barrel.getVolumeMax())
		{
			if (CompostRegistry.isMyceliumIngredient(item))
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		if (barrel.getVolume() < barrel.getVolumeMax())
		{
			if (CompostRegistry.isMyceliumIngredient(item))
				barrel.setState(BarrelStates.mycelium);
		}
		
		return false;
	}
}
