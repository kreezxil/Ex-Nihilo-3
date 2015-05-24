package exnihilo2.util.helpers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class InventoryHelper {
	public static ItemStack getContainer(ItemStack full)
	{
		ItemStack empty;
		
		if (full.getItem().hasContainerItem(full)) 
		{
			empty = full.getItem().getContainerItem(full);
		} 
		else 
		{
			empty = FluidContainerRegistry.drainFluidContainer(full);
		}
		
		return empty;
	}
	
	public static void giveItemStackToPlayer(EntityPlayer player, ItemStack item)
	{
		boolean given = player.inventory.addItemStackToInventory(InventoryHelper.getContainer(item));
		
		if (!given && !player.worldObj.isRemote)
		{
			player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, item));
		}
	}
}
