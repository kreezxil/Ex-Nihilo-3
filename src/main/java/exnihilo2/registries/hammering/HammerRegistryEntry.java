package exnihilo2.registries.hammering;

import java.util.ArrayList;

import exnihilo2.registries.hammering.pojos.HammerRecipe;
import exnihilo2.registries.hammering.pojos.HammerRecipeReward;
import exnihilo2.util.enums.EnumMetadataBehavior;
import exnihilo2.util.helpers.GameRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HammerRegistryEntry {
	private IBlockState input;
	private EnumMetadataBehavior behavior = EnumMetadataBehavior.SPECIFIC;
	private ArrayList<HammerReward> rewards = new ArrayList<HammerReward>();
	
	public HammerRegistryEntry(IBlockState input, EnumMetadataBehavior behavior)
	{
		this.input = input;
		this.behavior = behavior;
	}
	
	public void addReward(ItemStack item, int base_chance, int luck_modifier)
	{
		this.rewards.add(new HammerReward(item, base_chance, luck_modifier));
	}
	
	public ArrayList<HammerReward> getRewards()
	{
		return rewards;
	}
	
	public EnumMetadataBehavior getMetadataBehavior()
	{
		return this.behavior;
	}
	
	public void dropRewards(EntityPlayer player, BlockPos pos)
	{
		for (HammerReward reward : rewards)
		{
			reward.dropReward(player, pos);
		}
	}
	
	public String getKey()
	{
		String s = GameRegistry.findUniqueIdentifierFor(input.getBlock()).toString();
		
		if (behavior == EnumMetadataBehavior.IGNORED)
		{
			return s + ":*";
		}
		else 
		{
			return s + ":" + input.getBlock().getMetaFromState(input);
		}
	}
	
	public static HammerRegistryEntry fromRecipe(HammerRecipe recipe)
	{
		Block block = GameRegistryHelper.findBlock(recipe.getId());

		if (block != null)
		{
			IBlockState state = block.getStateFromMeta(recipe.getMeta());

			if (state != null)
			{
				HammerRegistryEntry entry = new HammerRegistryEntry(state, recipe.getBehavior());

				for (HammerRecipeReward reward : recipe.getRewards())
				{
					Item item = GameRegistryHelper.findItem(reward.getId());
					
					if (item != null)
					{
						entry.addReward(new ItemStack(item, reward.getAmount(), reward.getMeta()), reward.getBaseChance(), reward.getFortuneModifier());
					}
				}
				
				return entry;
			}
		}

		return null;
	}
	
	
}

