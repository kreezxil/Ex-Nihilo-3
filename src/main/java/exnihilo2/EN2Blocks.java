package exnihilo2;

import exnihilo2.blocks.BlockBarrel;
import exnihilo2.blocks.tileentities.TileEntityBarrel;
import exnihilo2.blocks.tileentities.renderers.RendererBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EN2Blocks {
	
	public static Block oak_barrel;
	public static Block spruce_barrel;

	public static void Initialize()
	{
		oak_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("oak_barrel");
		spruce_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("spruce_barrel");
	}
	
	public static void RegisterBlocks()
	{
		RegisterBlock(oak_barrel);
		RegisterBlock(spruce_barrel);
	}
	
	public static void RegisterBlockRenderers()
	{
		RegisterRenderer(oak_barrel);
		RegisterRenderer(spruce_barrel);

		TileEntitySpecialRenderer barrelRenderer = new RendererBarrel();
	   	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, barrelRenderer);
	}
	
	public static void RegisterTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityBarrel.class, EN2Data.MODID + ":tile_entity_barrel");
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
