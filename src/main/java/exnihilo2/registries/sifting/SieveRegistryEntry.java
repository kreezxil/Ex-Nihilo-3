package exnihilo2.registries.sifting;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import exnihilo2.registries.hammering.HammerRegistryEntry;
import exnihilo2.registries.hammering.HammerReward;
import exnihilo2.registries.hammering.pojos.HammerRecipe;
import exnihilo2.registries.hammering.pojos.HammerRecipeReward;
import exnihilo2.registries.sifting.pojos.SieveRecipe;
import exnihilo2.registries.sifting.pojos.SieveRecipeReward;
import exnihilo2.util.enums.EnumMetadataBehavior;
import exnihilo2.util.helpers.GameRegistryHelper;

public class SieveRegistryEntry {
	private IBlockState input;
	private EnumMetadataBehavior behavior = EnumMetadataBehavior.SPECIFIC;
	private ArrayList<SieveReward> rewards = new ArrayList<SieveReward>();
	
	public SieveRegistryEntry(IBlockState input, EnumMetadataBehavior behavior)
	{
		this.input = input;
		this.behavior = behavior;
	}
	
	public IBlockState getInput()
	{
		return input;
	}
	
	public void addReward(ItemStack item, int base_chance)
	{
		this.rewards.add(new SieveReward(item, base_chance));
	}
	
	public ArrayList<SieveReward> getRewards()
	{
		return rewards;
	}
	
	public EnumMetadataBehavior getMetadataBehavior()
	{
		return this.behavior;
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
	
	public static SieveRegistryEntry fromRecipe(SieveRecipe recipe)
	{
		Block block = GameRegistryHelper.findBlock(recipe.getId());

		if (block != null)
		{
			IBlockState state = block.getStateFromMeta(recipe.getMeta());

			if (state != null)
			{
				SieveRegistryEntry entry = new SieveRegistryEntry(state, recipe.getBehavior());

				for (SieveRecipeReward reward : recipe.getRewards())
				{
					Item item = GameRegistryHelper.findItem(reward.getId());
					
					if (item != null)
					{
						entry.addReward(new ItemStack(item, reward.getAmount(), reward.getMeta()), reward.getBaseChance());
					}
				}
				
				return entry;
			}
		}

		return null;
	}
}
