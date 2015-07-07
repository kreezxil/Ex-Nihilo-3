package exnihilo2.items.buckets;

import exnihilo2.blocks.EN2Blocks;
import exnihilo2.items.EN2Items;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemBucketAzoth extends ItemBucket{
	public ItemBucketAzoth(Block block)
	{
		super(block);
		
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        if (!playerIn.capabilities.isCreativeMode)
        {
            stack.stackSize--;
        }

        if (!worldIn.isRemote)
        {
        	playerIn.curePotionEffects(new ItemStack(Items.milk_bucket, 1));
        	playerIn.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1000, 1));
        	playerIn.addPotionEffect(new PotionEffect(Potion.weakness.id, 1000, 1));
        	playerIn.addPotionEffect(new PotionEffect(Potion.hunger.id, 2000, 3));
        }

        return stack.stackSize <= 0 ? new ItemStack(EN2Items.bucket_porcelain_empty) : stack;
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
    	ItemStack result = super.onItemRightClick(itemStackIn, worldIn, playerIn);

        if(result.getItem() == Items.bucket) {
            return new ItemStack(this.getContainerItem());
        }
        else
        {
        	playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        }
        
        return result;
    }
}
