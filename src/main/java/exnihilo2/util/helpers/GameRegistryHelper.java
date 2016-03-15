package exnihilo2.util.helpers;

import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GameRegistryHelper {
	public static Block findBlock(String id)
	{
		String[] names = id.split(":");
		
		//GameRegistry is currently broken. Using an ugly method I found on minecraftforgeforums instead. :(
		//TODO: Uncomment this when someone fixes it.
		Block block = GameRegistry.findBlock(names[0], names[1]);
		//Block block = findBlock(names[0], names[1]);
		
		return block;
	}
	
	public static Item findItem(String id)
	{
		String[] names = id.split(":");
		
		Item item = GameRegistry.findItem(names[0], names[1]);
		
		return item;
	}
	
	//Method found @ http://www.minecraftforge.net/forum/index.php?topic=29989.0
	/**
	* Tries to approach all legit ways to get a block from the game registry
	* but will go hackish when needed to to get the desired result.
	* @param modid String The modname
	* @param blockname String the block name
	* @return Blocks.air on not finding anything or the desired block.
	*/
//	public static Block findBlock(String modid, String blockname) {
//		Block find = GameRegistry.findBlock(modid,blockname);
//		// legit way fails?
//		if(find == null) {
//			String searchkey = modid+":"+blockname;
//			// Lets fire up the reflection...
//			// You might want to put the map somewhere so you
//			// don't have to reflect every time. saves a lot of cpu time.
//			Class x = GameData.class;
//				try {
//					Method method = x.getDeclaredMethod("getMain");
//					method.setAccessible(true);
//					GameData gamedata = (GameData)method.invoke(null);
//					// and I mean saving the below list b
//					FMLControlledNamespacedRegistry<Block> b = gamedata.getBlockRegistry();
//					// lets be gracious and give it a chance to find it this way
//					if(b.containsKey(searchkey)) {
//						find = b.getObject(searchkey);
//					}
//					else {
//						// take a wild stab. returns air if nothing found
//						find = b.getObject(searchkey);
//						if(find != Blocks.air) {
//							//WhoTookMyCookies.log.warn("Chest found: "+GameRegistry.findUniqueIdentifierFor(b.getObject("minecraft:chest")).name);
//						}
//						else {
//							if(!searchkey.equals("minecraft:air")) {
//								return null;
//							}
//						}
//					}
//				}
//				catch(Exception ex) {
//					// your error handling here
//				}
//			}
//		
//		return find;
//	}
}
