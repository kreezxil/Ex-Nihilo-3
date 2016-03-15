package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;

public class FluidCraftClayTrigger extends BarrelLogic{

	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		
		if (item.getItem() == Item.getItemFromBlock(EN2Blocks.dust) 
		    && barrel.getFluid().getFluid() == FluidRegistry.WATER
		    && barrel.getFluidAmount() == barrel.getCapacity())
		{
			return true;
		}
		
		return false;
	}

	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		if (item.getItem() == Item.getItemFromBlock(EN2Blocks.dust) 
		    && barrel.getFluid().getFluid() == FluidRegistry.WATER
		    && barrel.getFluidAmount() == barrel.getCapacity())
		{
			barrel.setState(BarrelStates.output);
			barrel.setContents(new ItemStack(Blocks.clay, 1));
			consumeItem(player, item);
			
			barrel.getWorld().playSoundEffect(barrel.getPos().getX() + 0.5f, barrel.getPos().getY() + 0.5f, barrel.getPos().getZ() + 0.5f, "random.splash", 0.12f, 4.5f);
			
			return true;
		}
		
		return false;
	}
	
}
