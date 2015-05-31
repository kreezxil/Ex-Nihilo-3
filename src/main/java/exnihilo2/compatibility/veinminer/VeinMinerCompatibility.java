package exnihilo2.compatibility.veinminer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

import exnihilo2.EN2;
import exnihilo2.items.hammers.ItemHammer;
import exnihilo2.registries.hammering.HammerRegistry;
import exnihilo2.registries.hammering.HammerRegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VeinMinerCompatibility {
	private static boolean initialized = false;
	
	public static Class harvestFailure;
	private static Field playerField;
	private static Field permissionField;
	private static Object permissionGranted;
	
	static
	{
		try {
			harvestFailure = Class.forName("portablejim.veinminer.api.VeinminerHarvestFailedCheck");
			permissionField = harvestFailure.getDeclaredField("allowContinue");
			permissionGranted = permissionField.getType().getEnumConstants()[1];
			playerField = harvestFailure.getDeclaredField("player");
			
			initialized = true;
			EN2.log.info("Initialize VeinMiner: Success!");
		} 
		catch (Exception ex) 
		{
			EN2.log.error("Unable to initialize VeinMiner compatibility.");
		}
	}
	
	public static void initialize()
	{
		if (initialized)
		{
			registerEventHandler();
			registerBlocksAndTools();
		}
	}
	
	public static boolean isInitialized()
	{
		return initialized;
	}
	
	public static void registerEventHandler()
	{
		try
		{
			EventBus bus = MinecraftForge.EVENT_BUS;
			
			Class[] args = new Class[] {Class.class, Object.class, Method.class, ModContainer.class};
			Method register = bus.getClass().getDeclaredMethod("register", args);
			Method callback = VeinMinerCompatibility.class.getMethod("handleEvent", new Class[]{Event.class});
			register.setAccessible(true);
			register.invoke(bus, harvestFailure, new VeinMinerCompatibility(), callback, Loader.instance().getIndexedModList().get("exnihilo2"));
			
			EN2.log.info("Register VeinMiner event handler: SUCCESS!");
		}
		catch (Exception ex)
		{
			EN2.log.error("Error thrown during VeinMiner event registration: " + ex.getMessage());
		}
	}
	
	@SubscribeEvent
	public void handleEvent(Event e)
	{
		if (isInitialized())
		{
			try {
				if (e.getClass().equals(harvestFailure))
				{
					EntityPlayer player = (EntityPlayer)playerField.get(e);
					ItemStack item = player.getCurrentEquippedItem();

					if (item.getItem() instanceof ItemHammer)
					{
						permissionField.set(e, permissionGranted);
					}
				}
			} 
			catch (Exception ex) 
			{
				EN2.log.error("Error thrown during VeinMiner event handling");
			}
		}
	}
	
	public static void registerBlocksAndTools()
	{
		//crooks
		VeinMinerAPI.addToolType("crook", "Crook", "exnihilo2:crook_wood");
		VeinMinerAPI.addTool("crook", "exnihilo2:crook_wood");
		VeinMinerAPI.addTool("crook", "exnihilo2:crook_bone");
		
		//hammers
		VeinMinerAPI.addToolType("hammer", "Hammer", "exnihilo2:hammer_diamond");
		VeinMinerAPI.addTool("hammer", "exnihilo2:hammer_wood");
        VeinMinerAPI.addTool("hammer", "exnihilo2:hammer_stone");
        VeinMinerAPI.addTool("hammer", "exnihilo2:hammer_iron");
        VeinMinerAPI.addTool("hammer", "exnihilo2:hammer_gold");
        VeinMinerAPI.addTool("hammer", "exnihilo2:hammer_diamond");
         
        Iterator blocks = GameData.getBlockRegistry().iterator();
        while(blocks.hasNext()) 
        {
           Block block = (Block)blocks.next();
           
           if (block.getMaterial() == Material.leaves || block instanceof BlockTallGrass)
           {
        	   VeinMinerAPI.addBlock("crook", GameRegistry.findUniqueIdentifierFor(block).toString());
           }
        }
        
        Iterator smashables = HammerRegistry.getEntryMap().values().iterator();
        while(smashables.hasNext())
        {
        	HammerRegistryEntry entry = (HammerRegistryEntry)smashables.next();
        	
        	VeinMinerAPI.addBlock("hammer", GameRegistry.findUniqueIdentifierFor(entry.getInput().getBlock()).toString());
        }
	}
}
