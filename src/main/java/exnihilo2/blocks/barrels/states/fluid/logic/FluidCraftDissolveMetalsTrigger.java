package exnihilo2.blocks.barrels.states.fluid.logic;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import exnihilo2.EN2;
import exnihilo2.alchemy.EN2Alchemy;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.fluids.EN2Fluids;
import exnihilo2.items.EN2Items;

public class FluidCraftDissolveMetalsTrigger extends BarrelLogic{
	private static final int METAL_LOSS_PERCENT = 10;
	private static Random rng = new Random();

	@Override
	public boolean canUseItem(TileEntityBarrel barrel, ItemStack item) {
		
		if (EN2Alchemy.getMetalValue(item) > 0 && barrel.getFluid().getFluid().equals(EN2Fluids.witchwater) && barrel.getFluidAmount() == barrel.getCapacity())
		{
			return true;
		}
		
		return false;
	}

	@Override
	public boolean onUseItem(EntityPlayer player, TileEntityBarrel barrel, ItemStack item) {
		if (EN2Alchemy.getMetalValue(item) > 0 && barrel.getFluid().getFluid().equals(EN2Fluids.witchwater) && barrel.getFluidAmount() == barrel.getCapacity())
		{
			int amount = getMetalAmount(EN2Alchemy.getMetalValue(item));
			if (amount < 1)
				amount = 1;
			
			barrel.setState(BarrelStates.dissolve_metals);
			barrel.startTimer(10 * EN2Alchemy.getMetalValue(item));
			barrel.setContents(new ItemStack(EN2Items.metal_salts, amount));
			consumeItem(player, item);
			
			barrel.getWorld().playSoundEffect(barrel.getPos().getX() + 0.5f, barrel.getPos().getY() + 0.5f, barrel.getPos().getZ() + 0.5f, "random.fizz", 0.5f, 4.5f);
			
			return true;
		}
		
		return false;
	}
	
	private int getMetalAmount(int value)
	{
		int count = 0;
		
		for (int x = 0; x < value; x++)
		{
			if (rng.nextInt(100) > METAL_LOSS_PERCENT)
				count++;
		}
		
		return count;
	}
}
