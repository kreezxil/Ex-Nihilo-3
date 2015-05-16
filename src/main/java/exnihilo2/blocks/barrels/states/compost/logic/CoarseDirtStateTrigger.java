package exnihilo2.blocks.barrels.states.compost.logic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.states.compost.BarrelStateCoarseDirt;
import exnihilo2.blocks.barrels.states.compost.BarrelStateGrass;
import exnihilo2.blocks.barrels.states.compost.BarrelStateMycelium;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.registries.composting.CompostRegistry;
import exnihilo2.registries.composting.CompostRegistryEntry;

public class CoarseDirtStateTrigger extends BarrelLogic{
	
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) 
	{
		if (barrel.getTimerStatus() == -1.0d)
		{
			if (((BarrelStateCoarseDirt)BarrelStates.coarse_dirt).isIngredient(item))
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		if (barrel.getTimerStatus() == -1.0d)
		{
			if (((BarrelStateCoarseDirt)BarrelStates.coarse_dirt).isIngredient(item))
				barrel.setState(BarrelStates.coarse_dirt);
		}
		
		return false;
	}
}
