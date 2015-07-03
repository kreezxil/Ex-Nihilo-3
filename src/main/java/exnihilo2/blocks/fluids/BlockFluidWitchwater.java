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

public class BlockFluidWitchwater extends BlockFluidClassic{

	public BlockFluidWitchwater(Fluid fluid, Material material) {
		super(fluid, material);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) 
	{
		if(!world.isRemote && !entity.isDead)
		{
			if (entity instanceof EntityVillager)
			{
				EntityVillager villager = (EntityVillager)entity;
				
				if (world.getDifficulty() != EnumDifficulty.PEACEFUL)
				{
					if (villager.getProfession() == 2 && !villager.isChild())
					{
						villager.setDead();
						
						EntityWitch witch = new EntityWitch(world);
						witch.setLocationAndAngles(villager.posX, villager.posY, villager.posZ, villager.rotationYaw, villager.rotationPitch);
						witch.renderYawOffset = villager.renderYawOffset;
						witch.setHealth(villager.getHealth());
						
						world.spawnEntityInWorld(witch);
					}
					else
					{
						villager.setDead();
						
						EntityZombie zombie = new EntityZombie(world);
						zombie.setLocationAndAngles(villager.posX, villager.posY, villager.posZ, villager.rotationYaw, villager.rotationPitch);
						zombie.renderYawOffset = villager.renderYawOffset;
						zombie.setHealth(villager.getHealth());
						zombie.setVillager(true);
						zombie.setChild(villager.isChild());
						
						world.spawnEntityInWorld(zombie);
					}
				}else
				{
					villager.onStruckByLightning(null);
				}
			}
			
			if (entity instanceof EntitySkeleton)
			{
				if (((EntitySkeleton)entity).getSkeletonType() == 0)
				{
					EntitySkeleton skeleton = (EntitySkeleton)entity;
					skeleton.setSkeletonType(1);
					skeleton.setHealth(skeleton.getMaxHealth());
				}
			}
			
			if (entity instanceof EntityCreeper)
			{
				if (!((EntityCreeper)entity).getPowered())
				{
					EntityCreeper creeper = (EntityCreeper)entity;
					creeper.onStruckByLightning(null);
					creeper.setHealth(creeper.getMaxHealth());
				}
			}
			
			if (entity instanceof EntitySpider && !(entity instanceof EntityCaveSpider))
			{
				EntitySpider spider = (EntitySpider)entity;
				spider.setDead();
				
				EntityCaveSpider caveSpider = new EntityCaveSpider(world);
				caveSpider.setLocationAndAngles(spider.posX, spider.posY, spider.posZ, spider.rotationYaw, spider.rotationPitch);
				caveSpider.renderYawOffset = spider.renderYawOffset;
				caveSpider.setHealth(caveSpider.getMaxHealth());
				
				world.spawnEntityInWorld(caveSpider);
			}
			
			if (entity instanceof EntitySquid)
			{
				EntitySquid squid = (EntitySquid)entity;
				squid.setDead();
				
				EntityGhast ghast = new EntityGhast(world);
				ghast.setLocationAndAngles(squid.posX, squid.posY + 2, squid.posZ, squid.rotationYaw, squid.rotationPitch);
				ghast.renderYawOffset = squid.renderYawOffset;
				ghast.setHealth(ghast.getMaxHealth());
				
				world.spawnEntityInWorld(ghast);
			}
			
			if(entity instanceof EntityAnimal)
			{
				EntityAnimal animal = (EntityAnimal)entity;
				animal.onStruckByLightning(null);
			}
			
			if(entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)entity;
				player.addPotionEffect(new PotionEffect(Potion.blindness.id, 210, 0));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 210, 2));
				player.addPotionEffect(new PotionEffect(Potion.wither.id, 210, 0));
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 210, 0));
			}
		}
	}
}
