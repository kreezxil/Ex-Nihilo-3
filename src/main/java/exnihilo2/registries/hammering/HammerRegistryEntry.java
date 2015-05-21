package exnihilo2.registries.hammering;

import java.util.ArrayList;

import exnihilo2.util.enums.EnumMetadataBehavior;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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
	
	private class HammerReward
	{
		private int base_chance;
		private int luck_modifier; //the effectiveness of Fortune enchantments.
		private ItemStack item;
		
		public HammerReward(ItemStack item, int base_chance, int luck_modifier)
		{
			this.item = item;
			this.base_chance = base_chance;
			this.luck_modifier = luck_modifier;
		}

		public void dropReward(EntityPlayer player, BlockPos pos)
		{
			World world = player.worldObj;
			int luck_level = EnchantmentHelper.getFortuneModifier(player);
			int chance = base_chance + (luck_modifier * luck_level);

			if (world.rand.nextInt(100) < chance)
			{
				EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, item.copy());

				entityitem.motionX = world.rand.nextGaussian() * 0.05F;
				entityitem.motionY = (0.2d);
				entityitem.motionZ = world.rand.nextGaussian() * 0.05F;

				world.spawnEntityInWorld(entityitem);
			}
		}
	}
}

