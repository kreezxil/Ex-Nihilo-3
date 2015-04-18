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

	public static Item wood_crook;
	public static Item bone_crook;
	
	public static void initialize()
	{
		wood_crook = new ItemCrook(EN2ToolMaterials.Stick).setUnlocalizedName("wood_crook");
		bone_crook = new ItemCrook(EN2ToolMaterials.Bone).setUnlocalizedName("bone_crook");
	}
	
	public static void registerItems()
	{
		registerItem(wood_crook);
		registerItem(bone_crook);
	}
	
	public static void registerItemRenderers()
	{
		registerItemRenderer(wood_crook);
		registerItemRenderer(bone_crook);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
	
	private static void registerItemRenderer(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(EN2Data.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
