package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class FluidCraftNetherrackTrigger extends BarrelLogic{

	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		
		if (item.getItem() == Items.redstone && barrel.getFluid().fluidID == FluidRegistry.LAVA.getID() && barrel.getFluidAmount() == barrel.getCapacity())
		{
			return true;
		}
		
		return false;
	}

	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		if (item.getItem() == Items.redstone && barrel.getFluid().fluidID == FluidRegistry.LAVA.getID() && barrel.getFluidAmount() == barrel.getCapacity())
		{
			barrel.setState(BarrelStates.output);
			barrel.setContents(new ItemStack(Blocks.netherrack, 1));
			consumeItem(player, item);
			
			return true;
		}
		
		return false;
	}
	
}
