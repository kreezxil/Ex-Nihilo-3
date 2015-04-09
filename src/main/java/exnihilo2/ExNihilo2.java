package exnihilo2;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExNihilo2.MODID, version = ExNihilo2.VERSION)
public class ExNihilo2
{
    public static final String MODID = "exnihilo2";
    public static final String VERSION = "1.0";
    
    @EventHandler
	public void PreInitialize(FMLPreInitializationEvent event)
	{
    	//pre
	}
    
    @EventHandler
    public void Initialize(FMLInitializationEvent event)
    {
		//init
    }
    
    @EventHandler
	public void PostInitialize(FMLPostInitializationEvent event)
	{
    	//post
	}
}
