package exnihilo2.crafting.recipes;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class MobDrops {
	private static Random rand = new Random();
	
	public static void onMobDeath(LivingDropsEvent event)
	{
		if(event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			if (event.entity instanceof EntityCreeper)
			{
				if (rand.nextInt(40) == 0)
				{
					EntityItem entityitem = createMobDrop(event.entity, new ItemStack(Blocks.cactus, 1));
					
					event.drops.add(entityitem);
				}
			}
		}
	}
	
	
	public static EntityItem createMobDrop(Entity entity, ItemStack item)
	{
		EntityItem entityitem = new EntityItem(entity.getEntityWorld(), 
				entity.getPosition().getX() + 0.5f, 
				entity.getPosition().getY() + 0.5f, 
				entity.getPosition().getZ() + 0.5f,
				item);

		entityitem.motionX = rand.nextGaussian() * 0.05F;
		entityitem.motionY = (0.2d);
		entityitem.motionZ = rand.nextGaussian() * 0.05F;
		entityitem.setDefaultPickupDelay();
		
		return entityitem;
	}
}
