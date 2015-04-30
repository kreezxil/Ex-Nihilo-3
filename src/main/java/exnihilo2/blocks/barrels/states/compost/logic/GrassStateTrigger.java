package exnihilo2.blocks.barrels.states.compost.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import exnihilo2.EN2;
import exnihilo2.blocks.barrels.BarrelStates;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.compost.BarrelStateGrass;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class GrassStateTrigger extends BarrelLogic{
	
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) 
	{
		if (barrel.getTimerStatus() == -1.0d)
		{
			if (((BarrelStateGrass)BarrelStates.grass).isIngredient(item))
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		if (barrel.getTimerStatus() == -1.0d)
		{
			if (((BarrelStateGrass)BarrelStates.grass).isIngredient(item))
				barrel.setState(BarrelStates.grass);
		}
		
		return false;
	}
}
