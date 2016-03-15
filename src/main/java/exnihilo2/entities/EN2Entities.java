package exnihilo2.entities;

import exnihilo2.EN2;
import exnihilo2.entities.items.EntityItemEmeraldBlack;
import exnihilo2.entities.thrown.stone.EntityStone;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EN2Entities {
	public static final int STONE_ID = 0;
	public static final int EMERALD_ID = 1;
	
	public static void configure()
	{
		EntityRegistry.registerModEntity(EntityStone.class, "stone", STONE_ID, EN2.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityItemEmeraldBlack.class, "emerald_black", EMERALD_ID, EN2.instance, 64, 10, true);
	}
}
