package exnihilo2.blocks.barrels.states.compost.logic;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import exnihilo2.blocks.barrels.BarrelStates;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class CompostStateLogicComplete extends BarrelLogic{
	
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {
		if (barrel.getTimerStatus() >= 1.0d)
		{
			barrel.setState(BarrelStates.output);
			barrel.setContents(new ItemStack(Blocks.dirt, 1));
			
			return true;
		}
		
		return false;
	}
}
