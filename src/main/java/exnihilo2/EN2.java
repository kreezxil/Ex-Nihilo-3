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
import exnihilo2.items.materials.EN2ToolMaterials;
import exnihilo2.proxy.Proxy;

@Mod(name = EN2Data.NAME, modid = EN2Data.MODID, version = EN2Data.VERSION)
public class EN2
{
	 @SidedProxy(serverSide = "exnihilo2.proxy.ServerProxy", clientSide = "exnihilo2.proxy.ClientProxy")
	 public static Proxy proxy;
	 
	 public static Logger log = LogManager.getLogger(EN2Data.NAME);
	
    @EventHandler
	public void PreInitialize(FMLPreInitializationEvent event)
	{
    	EN2ToolMaterials.Initialize();
    	
    	EN2Items.Initialize();
    	EN2Items.RegisterItems();
	}
    
    @EventHandler
    public void Initialize(FMLInitializationEvent event)
    {
    	proxy.RegisterItemRenderers();
    }
    
    @EventHandler
	public void PostInitialize(FMLPostInitializationEvent event)
	{
    	proxy.Test();
	}
}
