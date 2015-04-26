package exnihilo2.blocks.barrels.states.compost.logic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.registries.CompostRegistry;
import exnihilo2.registries.recipes.CompostRecipe;
import exnihilo2.util.Color;

public class PodzolStateTrigger extends BarrelLogic{
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) 
	{
		if (CompostRegistry.isCompostable(item) && barrel.getVolume() < barrel.getVolumeMax())
		{
			Block block = Block.getBlockFromItem(item.getItem());
			
			if (block != null && block.getMaterial() == Material.leaves)
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		CompostRecipe recipe = CompostRegistry.getRecipe(item);
		
		if (recipe != null)
		{
			Block block = Block.getBlockFromItem(item.getItem());
			
			if (block != null && block.getMaterial() == Material.leaves)
				barrel.setState("podzol");
		}
		
		return false;
	}
}
