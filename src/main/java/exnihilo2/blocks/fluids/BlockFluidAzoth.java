package exnihilo2.blocks.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidAzoth extends BlockFluidClassic{
	public BlockFluidAzoth(Fluid fluid, Material material) {
		super(fluid, material);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) 
	{
		if(!world.isRemote && !entity.isDead)
		{
			if(entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)entity;
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 500, 1));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 500, 1));
				player.addPotionEffect(new PotionEffect(Potion.hunger.id, 1000, 3));
			}
		}
	}
}
