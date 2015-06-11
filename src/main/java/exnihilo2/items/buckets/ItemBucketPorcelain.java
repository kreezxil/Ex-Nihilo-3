package exnihilo2.items.buckets;

import exnihilo2.items.EN2Items;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBucketPorcelain extends ItemBucket{
	public ItemBucketPorcelain(Block block)
	{
		super(block);
		
		this.setMaxStackSize(16);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        ItemStack result = super.onItemRightClick(itemStack, world, player);

        if(result.getItem() == Items.bucket) {
            return new ItemStack(EN2Items.bucket_porcelain_empty);
        }
        
        return result;
    }
}
