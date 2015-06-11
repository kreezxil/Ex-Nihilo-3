package exnihilo2.items.misc;

import exnihilo2.world.EN2World;
import exnihilo2.world.generation.templates.io.TemplateWorldExporter;
import exnihilo2.world.generation.templates.pojos.Template;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ItemAstrolabe extends Item{
	public ItemAstrolabe()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		if (!MinecraftServer.getServer().isDedicatedServer() && !world.isRemote)
		{
			if (EN2World.isWorldGenerationOverridden(world.provider.getDimensionId()))
			{
				//TODO see if this item has been renamed.
				TemplateWorldExporter.generate("test", world, player);
			}
			
			player.setCurrentItemOrArmor(0, null);
		}
		
		return item;
	}
}
