package exnihilo2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import exnihilo2.blocks.barrels.BarrelStateManager;
import exnihilo2.items.materials.EN2ToolMaterials;
import exnihilo2.proxy.Proxy;
import exnihilo2.registries.CompostRegistry;

@Mod(name = EN2Data.NAME, modid = EN2Data.MODID, version = EN2Data.VERSION)
public class EN2
{
	 @SidedProxy(serverSide = "exnihilo2.proxy.ServerProxy", clientSide = "exnihilo2.proxy.ClientProxy")
	 public static Proxy proxy;
	 
	 public static Logger log = LogManager.getLogger(EN2Data.NAME);
	
    @EventHandler
	public void preInitialize(FMLPreInitializationEvent event)
	{
    	EN2ToolMaterials.initialize();
    	
    	EN2Items.initialize();
    	EN2Blocks.initialize();
    	
    	EN2Items.registerItems();
    	EN2Blocks.registerBlocks();
    	EN2Blocks.registerTileEntities();
    	
    	BarrelStateManager.buildBehaviorTree();
    	CompostRegistry.addVanillaRecipes();
	}
    
    @EventHandler
    public void doInitialize(FMLInitializationEvent event)
    {
    	proxy.registerRenderers();
    }
    
    @EventHandler
	public void postInitialize(FMLPostInitializationEvent event)
	{
    	
	}
}
