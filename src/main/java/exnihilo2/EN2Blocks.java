package exnihilo2;

import exnihilo2.blocks.BlockBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EN2Blocks {
	
	public static Block wood_barrel;

	public static void Initialize()
	{
		wood_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("wood_barrel");
	}
	
	public static void RegisterBlocks()
	{
		RegisterBlock(wood_barrel);
	}
	
	public static void RegisterBlockRenderers()
	{
		RegisterRenderer(wood_barrel);
	}
	
	private static void RegisterBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	private static void RegisterRenderer(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(EN2Data.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
