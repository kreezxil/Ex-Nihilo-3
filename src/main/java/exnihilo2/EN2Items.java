package exnihilo2;

import exnihilo2.data.EN2Data;
import exnihilo2.items.crooks.WoodCrook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EN2Items {

	public static Item wood_crook;
	
	public static void Initialize()
	{
		wood_crook = new WoodCrook().setUnlocalizedName("wood_crook");
	}
	
	public static void RegisterItems()
	{
		RegisterItem(wood_crook);
	}
	
	public static void RegisterItemRenderers()
	{
		RegisterItemRenderer(wood_crook);
	}
	
	private static void RegisterItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
	
	private static void RegisterItemRenderer(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(EN2Data.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
