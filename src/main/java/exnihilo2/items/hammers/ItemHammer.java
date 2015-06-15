package exnihilo2.items.hammers;

import java.util.Set;

import com.google.common.collect.Sets;

import exnihilo2.EN2;
import exnihilo2.registries.hammering.HammerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemHammer extends ItemTool{
	private final static Set EMPTY_SET = Sets.newHashSet(new Block[]{});
	private final Item.ToolMaterial material;
	
	public ItemHammer(ToolMaterial material) {
		super(3.0f, material, EMPTY_SET);
		this.material = material;
		this.maxStackSize = 1;
		this.setMaxDamage((int)(material.getMaxUses()));
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	

	@Override
	public float getDigSpeed(ItemStack itemstack, IBlockState state) 
	{
		if (HammerRegistry.isHammerable(state) 
				&& (state.getBlock().getMaterial().isToolNotRequired()
				|| state.getBlock().getHarvestLevel(state) <= this.material.getHarvestLevel()))
		{
			return this.material.getEfficiencyOnProperMaterial() * 0.8f;
		}
		else
		{
			return 0.8f;
		}
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack item, BlockPos pos, EntityPlayer player)
    {
		World world = player.worldObj;
		IBlockState state = world.getBlockState(pos);
		
		if (HammerRegistry.isHammerable(state) 
				&& (state.getBlock().getMaterial().isToolNotRequired()
				|| state.getBlock().getHarvestLevel(state) <= this.material.getHarvestLevel()))
		{
			if (!world.isRemote)
			{
				HammerRegistry.getEntryForBlockState(state).dropRewards(player, pos);
				world.destroyBlock(pos, false);
			}
			
			item.damageItem(1, player);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean canHarvestBlock(Block block)
    {
		return (block.getMaterial().isToolNotRequired()
				|| block.getHarvestLevel(block.getDefaultState()) <= this.material.getHarvestLevel());
    }
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.material.getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
}
