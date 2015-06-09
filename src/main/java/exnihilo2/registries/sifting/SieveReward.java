package exnihilo2.registries.sifting;

import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SieveReward {
	private static Random rand = new Random();
	
	private int base_chance;
	private ItemStack item;
	
	public SieveReward(ItemStack item, int base_chance)
	{
		this.item = item;
		this.base_chance = base_chance;
	}
	
	public ItemStack generateReward()
	{
		if (rand.nextInt(100) < base_chance)
		{
			return item.copy();
		}
		
		return null;
	}

	public int getBaseChance() {
		return base_chance;
	}

	public void setBaseChance(int base_chance) {
		this.base_chance = base_chance;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
}
