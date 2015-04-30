package exnihilo2;

import exnihilo2.items.ItemCrook;
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
	
	public static void initialize()
	{
		crook_wood = new ItemCrook(EN2ToolMaterials.Stick).setUnlocalizedName("crook_wood");
		crook_bone = new ItemCrook(EN2ToolMaterials.Bone).setUnlocalizedName("crook_bone");
	}
	
	public static void registerItems()
	{
		registerItem(crook_wood);
		registerItem(crook_bone);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
