package exnihilo2.items;

import exnihilo2.items.crooks.ItemCrook;
import exnihilo2.items.hammers.ItemHammer;
import exnihilo2.items.materials.EN2ToolMaterials;
import exnihilo2.items.meshs.ItemMeshSilk;
import exnihilo2.items.meshs.ItemMeshWood;
import exnihilo2.items.misc.ItemAsh;
import exnihilo2.items.misc.ItemPorcelain;
import exnihilo2.items.misc.ItemStone;
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
	
	public static Item mesh_silk_white;
	public static Item mesh_wood;
	
	public static Item ash;
	public static Item porcelain;
	public static Item stone;
	
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
		
		//Sieve meshes
		mesh_silk_white = new ItemMeshSilk().setUnlocalizedName("mesh_silk_white");
		mesh_wood = new ItemMeshWood().setUnlocalizedName("mesh_wood");
		
		//Misc
		ash = new ItemAsh().setUnlocalizedName("ash");
		porcelain = new ItemPorcelain().setUnlocalizedName("porcelain");
		stone = new ItemStone().setUnlocalizedName("stone");
		
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
		
		registerItem(mesh_silk_white);
		registerItem(mesh_wood);
		
		registerItem(ash);
		registerItem(porcelain);
		registerItem(stone);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
