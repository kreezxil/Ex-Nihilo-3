package exnihilo2;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import exnihilo2.data.*;

@Mod(name = EN2Data.NAME, modid = EN2Data.MODID, version = EN2Data.VERSION)
public class EN2
{
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
