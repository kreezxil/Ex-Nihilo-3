package exnihilo2.blocks.barrels.states.slime.logic;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class SlimeStateLogic extends BarrelLogic{
	@Override
	public boolean onUpdate(TileEntityBarrel barrel) {
		if (barrel.getTimerStatus() == -1)
		{
			barrel.startTimer(1000);
		}

		if (barrel.getTimerStatus() >= 1.0d 
				&& barrel.getWorld().getDifficulty() != EnumDifficulty.PEACEFUL
				&& barrel.getWorld().isAirBlock(barrel.getPos().up()))
		{
			if (!barrel.getWorld().isRemote)
			{
				EntitySlime slime = new EntitySlime(barrel.getWorld());
				slime.setPosition(barrel.getPos().getX() + 0.5d, barrel.getPos().getY() + 1.25d, barrel.getPos().getZ() + 0.5d);
				//slime.setRotation(barrel.getWorld().rand.nextFloat() * 360f, 0.0f);

				barrel.getWorld().spawnEntityInWorld(slime);
			}
			
			barrel.setState(BarrelStates.empty);
			
			return true;
		}
		
		return false;
	}
}
