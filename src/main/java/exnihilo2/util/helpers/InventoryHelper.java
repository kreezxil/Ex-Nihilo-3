package exnihilo2.util.helpers;

import exnihilo2.EN2;
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
		if(!player.worldObj.isRemote)
		{
			EntityItem entity = new EntityItem(player.worldObj, player.posX + 0.5d, player.posY + 0.5d, player.posZ + 0.5d, item);
			player.worldObj.spawnEntityInWorld(entity);
		}	
	}
	
	public static void consumeItem(EntityPlayer player, ItemStack item)
	{
		if (player != null && !player.capabilities.isCreativeMode)
		{
			if (item.stackSize > 1)
			{
				item.stackSize--;
			}
			else
			{
				if (player.getCurrentEquippedItem().equals(item))
				{
					player.setCurrentItemOrArmor(0, null);
				}else
				{
					ItemStack[] inventory = player.getInventory();
					
					for (int x = 0; x < inventory.length; x++)
					{
						if (inventory[x].equals(item) && inventory[x].stackSize == 1)
						{
							inventory[x] = null;
						}
					}
				}
			}
		}
	}
}
