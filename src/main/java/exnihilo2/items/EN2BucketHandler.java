package exnihilo2.items;

import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.fluids.EN2Fluids;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EN2BucketHandler {
	public static void registerBuckets()
	{
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(EN2Items.bucket_porcelain_water), new ItemStack(EN2Items.bucket_porcelain_empty));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.LAVA, new ItemStack(EN2Items.bucket_porcelain_lava), new ItemStack(EN2Items.bucket_porcelain_empty));
		FluidContainerRegistry.registerFluidContainer(EN2Fluids.witchwater, new ItemStack(EN2Items.bucket_porcelain_witchwater), new ItemStack(EN2Items.bucket_porcelain_empty));
		FluidContainerRegistry.registerFluidContainer(EN2Fluids.witchwater, new ItemStack(EN2Items.bucket_witchwater), new ItemStack(Items.bucket));
	}
	
	@SubscribeEvent
    public void onEntityInteraction(EntityInteractEvent event)
    {
		if(event.entityPlayer == null || event.target == null || !(event.target instanceof EntityCow))
            return;

        ItemStack equipped = event.entityPlayer.getCurrentEquippedItem();
        if(equipped == null || equipped.getItem() != EN2Items.bucket_porcelain_empty)
            return;

        EntityPlayer player = event.entityPlayer;

        if (!player.capabilities.isCreativeMode)
        {
        	if (equipped.stackSize-- == 1)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(EN2Items.bucket_porcelain_milk));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(EN2Items.bucket_porcelain_milk)))
            {
                player.dropPlayerItemWithRandomChoice(new ItemStack(EN2Items.bucket_porcelain_milk, 1), false);
            }
        }
    }
	
	@SubscribeEvent
    public void onBucketFill (FillBucketEvent event)
    {
		Block block = event.world.getBlockState(event.target.getBlockPos()).getBlock();
		
		if (event.current.getItem() == EN2Items.bucket_porcelain_empty && event.target.typeOfHit == MovingObjectType.BLOCK)
        {
			if (event.entityPlayer != null && !event.entityPlayer.canPlayerEdit(event.target.getBlockPos(), event.target.sideHit, event.current))
            {
                return;
            }
			
			if (block == Blocks.lava)
			{
				event.setResult(Event.Result.ALLOW);
				event.result = new ItemStack(EN2Items.bucket_porcelain_lava);
				event.world.setBlockToAir(event.target.getBlockPos());
				return;
			}
			
			if (block == Blocks.water)
			{
				event.setResult(Event.Result.ALLOW);
				event.result = new ItemStack(EN2Items.bucket_porcelain_water);
				event.world.setBlockToAir(event.target.getBlockPos());
				return;
			}
			
			if (block == EN2Blocks.witchwater)
			{
				event.setResult(Event.Result.ALLOW);
				event.result = new ItemStack(EN2Items.bucket_porcelain_witchwater);
				event.world.setBlockToAir(event.target.getBlockPos());
				return;
			}
			
			event.setCanceled(true);
        }
		else if (event.current.getItem() == Items.bucket && event.target.typeOfHit == MovingObjectType.BLOCK)
		{
			if (block == EN2Blocks.witchwater)
			{
				event.setResult(Event.Result.ALLOW);
				event.result = new ItemStack(EN2Items.bucket_witchwater);
				event.world.setBlockToAir(event.target.getBlockPos());
				return;
			}
		}
    }
}
