package exnihilo2.blocks.barrels.states.fluid.logic;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.util.helpers.InventoryHelper;

public class FluidSummonSlimeTrigger extends BarrelLogic{
	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = barrel.getFluid();
		
		if (fluid != null  
				&& fluid.getFluid() != null 
				&& fluid.getFluid() == FluidRegistry.WATER 
				&& barrel.getFluidAmount() == barrel.getCapacity()
				&& barrel.getWorld().getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			return item.getItem() == Items.milk_bucket;
		}
		
		return false;
	}

	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		FluidStack fluid = barrel.getFluid();
		
		if (fluid != null  
				&& fluid.getFluid() != null 
				&& fluid.getFluid() == FluidRegistry.WATER 
				&& barrel.getFluidAmount() == barrel.getCapacity()
				&& barrel.getWorld().getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			if (player != null)
			{
				if (!player.capabilities.isCreativeMode)
				{
					player.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
				}
			}
			else
			{
				barrel.addOutput(new ItemStack(Items.bucket, 1));
				item = null;
			}
			
			barrel.setState(BarrelStates.slime_green);
			
			return true;
		}
		
		return false;
	}
}
