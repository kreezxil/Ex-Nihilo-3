package exnihilo2.compatibility;

import exnihilo2.compatibility.veinminer.VeinMinerCompatibility;
import exnihilo2.compatibility.waila.WailaCompatibility;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

public class EN2Compatibility {
	private static final String CATEGORY_COMPAT_OPTIONS = "compatibility options";
	public static boolean register_veinminer_tools;
	public static boolean register_veinminer_recipes_crook;
	public static boolean register_veinminer_recipes_hammer;
	
	public static void configure(Configuration config)
	{
		register_veinminer_tools = config.get(CATEGORY_COMPAT_OPTIONS, "VeinMiner: register tools", true).getBoolean(true);
		register_veinminer_recipes_crook = config.get(CATEGORY_COMPAT_OPTIONS, "VeinMiner: whitelist blocks for crook", true).getBoolean(true);
		register_veinminer_recipes_hammer = config.get(CATEGORY_COMPAT_OPTIONS, "VeinMiner: whitelist blocks for hammer", true).getBoolean(true);
	}
	
	public static void initialize()
	{
		if (Loader.isModLoaded("Waila"))
		{
			WailaCompatibility.initialize();
		}
		
		if (Loader.isModLoaded("VeinMiner"))
		{
			VeinMinerCompatibility.initialize();
		}
	}
}
