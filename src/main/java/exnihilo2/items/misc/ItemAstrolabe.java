package exnihilo2.items.misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	
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
				if (item.hasDisplayName())
				{
					TemplateWorldExporter.generate(item.getDisplayName(), world, player);
				}
				else
				{
					TemplateWorldExporter.generate("export_" + format.format(new Date()), world, player);
				}
			}
			
			player.setCurrentItemOrArmor(0, null);
		}
		
		return item;
	}
}
