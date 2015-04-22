package exnihilo2;

import exnihilo2.blocks.BlockBarrel;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
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
	public static Block dark_oak_barrel;
	public static Block glass_barrel;

	public static void initialize()
	{
		oak_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("oak_barrel");
		spruce_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("spruce_barrel");
		dark_oak_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("dark_oak_barrel");
		glass_barrel = new BlockBarrel(Material.glass).setUnlocalizedName("glass_barrel");
	}
	
	public static void registerBlocks()
	{
		registerBlock(oak_barrel);
		registerBlock(spruce_barrel);
		registerBlock(dark_oak_barrel);
		registerBlock(glass_barrel);
	}
	
	public static void registerBlockRenderers()
	{
		registerRenderer(oak_barrel);
		registerRenderer(spruce_barrel);
		registerRenderer(dark_oak_barrel);
		registerRenderer(glass_barrel);

		TileEntitySpecialRenderer barrelRenderer = new BarrelRenderer();
	   	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, barrelRenderer);
	}
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityBarrel.class, EN2Data.MODID + ":tile_entity_barrel");
	}
	
	private static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	private static void registerRenderer(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(EN2Data.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
