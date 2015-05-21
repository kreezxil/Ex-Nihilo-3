package exnihilo2.items;

import exnihilo2.items.crooks.ItemCrook;
import exnihilo2.items.hammers.ItemHammer;
import exnihilo2.items.materials.EN2ToolMaterials;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EN2Items {
	public static Item crook_wood;
	public static Item crook_bone;
	public static Item hammer_wood;
	public static Item hammer_stone;
	public static Item hammer_iron;
	public static Item hammer_gold;
	public static Item hammer_diamond;
	
	public static void initialize()
	{
		//Crooks
		crook_wood = new ItemCrook(EN2ToolMaterials.Stick).setUnlocalizedName("crook_wood");
		crook_bone = new ItemCrook(EN2ToolMaterials.Bone).setUnlocalizedName("crook_bone");
		
		//Hammers
		hammer_wood = new ItemHammer(ToolMaterial.WOOD).setUnlocalizedName("hammer_wood");
		hammer_stone = new ItemHammer(ToolMaterial.STONE).setUnlocalizedName("hammer_stone");
		hammer_iron = new ItemHammer(ToolMaterial.IRON).setUnlocalizedName("hammer_iron");
		hammer_gold = new ItemHammer(ToolMaterial.GOLD).setUnlocalizedName("hammer_gold");
		hammer_diamond = new ItemHammer(ToolMaterial.EMERALD).setUnlocalizedName("hammer_diamond");
		
		registerItems();
	}
	
	private static void registerItems()
	{
		registerItem(crook_wood);
		registerItem(crook_bone);
		
		registerItem(hammer_wood);
		registerItem(hammer_stone);
		registerItem(hammer_iron);
		registerItem(hammer_gold);
		registerItem(hammer_diamond);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
