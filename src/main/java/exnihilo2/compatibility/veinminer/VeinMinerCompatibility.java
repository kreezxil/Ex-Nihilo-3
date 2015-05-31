package exnihilo2.compatibility.veinminer;

import java.lang.reflect.Field;
import java.util.Iterator;

import exnihilo2.EN2;
import exnihilo2.items.hammers.ItemHammer;
import exnihilo2.registries.hammering.HammerRegistry;
import exnihilo2.registries.hammering.HammerRegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VeinMinerCompatibility {
	private static boolean initialized = false;
	
	private static Class harvestFailure;
	private static Field playerField;
	private static Field permissionField;
	private static Object permissionGranted;
	
	public static void initialize()
	{
		try {
			harvestFailure = Class.forName("portablejim.veinminer.api.VeinminerHarvestFailedCheck");
			permissionField = harvestFailure.getDeclaredField("allowContinue");
			permissionGranted = permissionField.getType().getEnumConstants()[1];
			playerField = harvestFailure.getDeclaredField("player");
			
			initialized = true;
			EN2.log.info("Initialise VeinMiner: Success!");
		} catch (Exception ex) {
			EN2.log.error("Unable to initialize VeinMiner compatibility.");
		}
		
		if (initialized)
		{
			registerBlocksAndTools();
		}
	}
	
	public static boolean isInitialized()
	{
		return initialized;
	}
	
	public static void handleEvent(Event e)
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
			} catch (Exception ex) {
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
