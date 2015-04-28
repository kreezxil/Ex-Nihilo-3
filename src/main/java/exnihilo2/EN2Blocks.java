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
	public static Block stained_glass_barrel_white;
	public static Block stained_glass_barrel_orange;
	public static Block stained_glass_barrel_magenta;
	public static Block stained_glass_barrel_lightblue;
	public static Block stained_glass_barrel_yellow;
	public static Block stained_glass_barrel_lime;
	public static Block stained_glass_barrel_pink;
	public static Block stained_glass_barrel_gray;
	public static Block stained_glass_barrel_lightgray;
	public static Block stained_glass_barrel_cyan;
	public static Block stained_glass_barrel_purple;
	public static Block stained_glass_barrel_blue;
	public static Block stained_glass_barrel_brown;
	public static Block stained_glass_barrel_green;
	public static Block stained_glass_barrel_red;
	public static Block stained_glass_barrel_black;

	public static void initialize()
	{
		oak_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("oak_barrel");
		spruce_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("spruce_barrel");
		dark_oak_barrel = new BlockBarrel(Material.wood).setUnlocalizedName("dark_oak_barrel");
		glass_barrel = new BlockBarrel(Material.glass).setUnlocalizedName("glass_barrel");
		
		stained_glass_barrel_white = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_white");
		stained_glass_barrel_orange = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_orange");
		stained_glass_barrel_magenta = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_magenta");
		stained_glass_barrel_lightblue = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_lightblue");
		stained_glass_barrel_yellow = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_yellow");
		stained_glass_barrel_lime = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_lime");
		stained_glass_barrel_pink = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_pink");
		stained_glass_barrel_gray = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_gray");
		stained_glass_barrel_lightgray = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_lightgray");
		stained_glass_barrel_cyan = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_cyan");
		stained_glass_barrel_purple = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_purple");
		stained_glass_barrel_blue = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_blue");
		stained_glass_barrel_brown = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_brown");
		stained_glass_barrel_green = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_green");
		stained_glass_barrel_red = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_red");
		stained_glass_barrel_black = new BlockBarrel(Material.glass).setUnlocalizedName("stained_glass_barrel_black");
	}
	
	public static void registerBlocks()
	{
		registerBlock(oak_barrel);
		registerBlock(spruce_barrel);
		registerBlock(dark_oak_barrel);
		registerBlock(glass_barrel);
		
		registerBlock(stained_glass_barrel_white);
		registerBlock(stained_glass_barrel_orange);
		registerBlock(stained_glass_barrel_magenta);
		registerBlock(stained_glass_barrel_lightblue);
		registerBlock(stained_glass_barrel_yellow);
		registerBlock(stained_glass_barrel_lime);
		registerBlock(stained_glass_barrel_pink);
		registerBlock(stained_glass_barrel_gray);
		registerBlock(stained_glass_barrel_lightgray);
		registerBlock(stained_glass_barrel_cyan);
		registerBlock(stained_glass_barrel_purple);
		registerBlock(stained_glass_barrel_blue);
		registerBlock(stained_glass_barrel_brown);
		registerBlock(stained_glass_barrel_green);
		registerBlock(stained_glass_barrel_red);
		registerBlock(stained_glass_barrel_black);
	}
	
	public static void registerBlockRenderers()
	{
		registerRenderer(oak_barrel);
		registerRenderer(spruce_barrel);
		registerRenderer(dark_oak_barrel);
		registerRenderer(glass_barrel);
		
		registerRenderer(stained_glass_barrel_white);
		registerRenderer(stained_glass_barrel_orange);
		registerRenderer(stained_glass_barrel_magenta);
		registerRenderer(stained_glass_barrel_lightblue);
		registerRenderer(stained_glass_barrel_yellow);
		registerRenderer(stained_glass_barrel_lime);
		registerRenderer(stained_glass_barrel_pink);
		registerRenderer(stained_glass_barrel_gray);
		registerRenderer(stained_glass_barrel_lightgray);
		registerRenderer(stained_glass_barrel_cyan);
		registerRenderer(stained_glass_barrel_purple);
		registerRenderer(stained_glass_barrel_blue);
		registerRenderer(stained_glass_barrel_brown);
		registerRenderer(stained_glass_barrel_green);
		registerRenderer(stained_glass_barrel_red);
		registerRenderer(stained_glass_barrel_black);

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
