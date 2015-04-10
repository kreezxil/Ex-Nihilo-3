package exnihilo2.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
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
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack item, EntityPlayer player, Entity entity)
	{
		if (!player.worldObj.isRemote)
		{
			double distance = Math.sqrt(Math.pow(player.posX - entity.posX, 2) + Math.pow(player.posZ - entity.posZ, 2));

			double scalarX = (player.posX - entity.posX) / distance;
			double scalarZ = (player.posZ - entity.posZ) / distance;

			double velX = 0 - scalarX * pushingForce;
			double velY = 0;
			double velZ = 0 - scalarZ * pushingForce;
			
			
			if (player.posY < entity.posY)
				velY = 0.5d;
			
			entity.addVelocity(velX, velY, velZ);
		}
		
		//Don't do damage
		item.damageItem(1, player);
		return true;
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityPlayer player, EntityLivingBase entity)
    {
		if (!player.worldObj.isRemote)
		{
			double distance = Math.sqrt(Math.pow(player.posX - entity.posX, 2) + Math.pow(player.posZ - entity.posZ, 2));

			double scalarX = (player.posX - entity.posX) / distance;
			double scalarZ = (player.posZ - entity.posZ) / distance;

			double velX = scalarX * pullingForce;
			double velY = 0;
			double velZ = scalarZ * pullingForce;
			
			if (player.posY > entity.posY)
				velY = 0.5d;
			
			entity.addVelocity(velX, velY, velZ);
		}

		item.damageItem(1, player);
		return true;
    }
	
	@Override
	public boolean canHarvestBlock(Block block)
    {
		return block.getMaterial() == Material.leaves;
    }
	
	@Override
	public float getStrVsBlock(ItemStack item, Block block)
    {
		if (block.getMaterial() == Material.leaves)
		{
			return material.getEfficiencyOnProperMaterial() + 1;
		}

		return 1.0F;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack item, BlockPos pos, EntityPlayer player)
    {
		Block block = player.worldObj.getBlockState(pos).getBlock();
		
		if (block.getMaterial() == Material.leaves || block.getMaterial() == Material.vine)
		{
			//Simulate a block break to cause the first round of items to drop.
			block.dropBlockAsItem(player.worldObj, pos, player.worldObj.getBlockState(pos), 0);
		}
		
		//Returning false causes the leaves/grass to break as normal and causes items to drop a second time.
        return false;
    }
	
	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, Block block, BlockPos pos, EntityLivingBase player)
    {
		item.damageItem(1, player);
		return true;
    }
}
