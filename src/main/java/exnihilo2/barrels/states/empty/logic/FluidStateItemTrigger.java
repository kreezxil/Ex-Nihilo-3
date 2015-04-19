package exnihilo2.barrels.states.empty.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.EN2;
import exnihilo2.barrels.architecture.BarrelLogic;
import exnihilo2.barrels.states.fluid.BarrelStateFluid;
import exnihilo2.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.InventoryUtil;

public class FluidStateItemTrigger extends BarrelLogic {
	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) 
	{
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(item);

		if (fluid != null)
		{
			if (item.getItem() == Items.potionitem && item.getItemDamage() == 0)
			{
				//Without this line, the glass bottle would be consumed.
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.glass_bottle, 1, 0));
			}else
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, InventoryUtil.getContainer(item));
			}
			
			barrel.fill(fluid, true);
			return true;
		}
		
		return false;
	}
}
