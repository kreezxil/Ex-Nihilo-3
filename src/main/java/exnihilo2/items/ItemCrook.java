package exnihilo2.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import exnihilo2.EN2;
import exnihilo2.EN2Data;

public class ItemCrook extends Item {
	public static final double pullingForce = 1.5d;
	public static final double pushingForce = 1.5d;
	
	private final Item.ToolMaterial material;
	
	public ItemCrook(ToolMaterial material) {
		this.material = material;
	}
	
	@Override
	public boolean canHarvestBlock(Block block)
    {
		return block.getMaterial() == Material.leaves;
    }
	
	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
    {
		if (block.getMaterial() == Material.leaves)
		{
			return material.getEfficiencyOnProperMaterial() + 1;
		}

		return 1.0F;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (!player.worldObj.isRemote)
		{
			double distance = Math.sqrt(Math.pow(player.posX - entity.posX, 2) + Math.pow(player.posZ - entity.posZ, 2));

			double scalarX = (player.posX - entity.posX) / distance;
			double scalarY = (player.posY - entity.posY) / distance;
			double scalarZ = (player.posZ - entity.posZ) / distance;

			double velX = 0 - scalarX * pushingForce;
			double velY = 0 - scalarY * pushingForce;
			double velZ = 0 - scalarZ * pushingForce;
			
			entity.addVelocity(velX, velY, velZ);
		}
		
		//Don't do damage
		stack.damageItem(1, player);
		return true;
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity)
    {
		if (!player.worldObj.isRemote)
		{
			double distance = Math.sqrt(Math.pow(player.posX - entity.posX, 2) + Math.pow(player.posZ - entity.posZ, 2));

			double scalarX = (player.posX - entity.posX) / distance;
			double scalarY = (player.posY - entity.posY) / distance;
			double scalarZ = (player.posZ - entity.posZ) / distance;

			double velX = scalarX * pullingForce;
			double velY = scalarY * pullingForce;
			double velZ = scalarZ * pullingForce;
			
			entity.addVelocity(velX, velY, velZ);
		}

		stack.damageItem(1, player);
		return true;
    }
}
