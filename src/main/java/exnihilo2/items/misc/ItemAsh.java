package exnihilo2.items.misc;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemAsh extends Item{
	public ItemAsh()
	{
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		IBlockState state = worldIn.getBlockState(pos);

		if (state.getBlock() instanceof IGrowable)
		{
			IGrowable igrowable = (IGrowable)state.getBlock();

			if (igrowable.canGrow(worldIn, pos, state, worldIn.isRemote))
			{
				if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, state))
				{
					if (!worldIn.isRemote)
					{
						worldIn.playAuxSFX(2005, pos, 0);
						
						if (worldIn.rand.nextInt(5) == 0)
						{
							igrowable.grow(worldIn, worldIn.rand, pos, state);
						}
					}
				}
				
				--stack.stackSize;
				return true;
			}
		}

        return false;
    }
}
