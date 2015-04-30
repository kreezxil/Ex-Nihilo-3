package exnihilo2.blocks.barrels.states.empty.logic;

import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.Color;

public class EmptyStateLogic extends BarrelLogic{
	@Override
	public boolean onActivate(TileEntityBarrel barrel) {
		barrel.drain(barrel.getCapacity(), true);
		barrel.setContents(null);
		barrel.setColor(new Color("FFFFFF"));
		barrel.setVolume(0);
		barrel.resetTimer();
		
		return false;
	}
}
