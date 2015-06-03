package exnihilo2.registries.sifting;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SieveReward {
	private int base_chance;
	private ItemStack item;
	
	public SieveReward(ItemStack item, int base_chance)
	{
		this.item = item;
		this.base_chance = base_chance;
	}

	public void dropReward(World world, BlockPos pos)
	{
		if (world.rand.nextInt(100) < base_chance)
		{
			EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, item.copy());

			entityitem.motionX = world.rand.nextGaussian() * 0.05F;
			entityitem.motionY = (0.2d);
			entityitem.motionZ = world.rand.nextGaussian() * 0.05F;
			entityitem.setDefaultPickupDelay();
			
			world.spawnEntityInWorld(entityitem);
		}
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
