package exnihilo2;

import java.util.ArrayList;

import exnihilo2.blocks.BlockBarrel;
import exnihilo2.blocks.BlockBarrelGlassColored;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.items.ItemBarrelGlassColored;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EN2Blocks {
	
	public static Block barrel_oak;
	public static Block barrel_spruce;
	public static Block barrel_dark_oak;
	public static Block barrel_glass;
	public static Block barrel_glass_colored;

	public static void initialize()
	{
		barrel_oak = new BlockBarrel(Material.wood).setUnlocalizedName("barrel_oak");
		barrel_spruce = new BlockBarrel(Material.wood).setUnlocalizedName("barrel_spruce");
		barrel_dark_oak = new BlockBarrel(Material.wood).setUnlocalizedName("barrel_dark_oak");
		barrel_glass = new BlockBarrel(Material.glass).setUnlocalizedName("barrel_glass");
		barrel_glass_colored = new BlockBarrelGlassColored(Material.glass).setUnlocalizedName("barrel_glass_colored");
	}
	
	public static void registerBlocks()
	{
		registerBlock(barrel_oak);
		registerBlock(barrel_spruce);
		registerBlock(barrel_dark_oak);
		registerBlock(barrel_glass);
		registerBlock(barrel_glass_colored, ItemBarrelGlassColored.class);
		registerBlockVariantsFromMeta(barrel_glass_colored);
	}
	
	public static void registerBlockRenderers()
	{
		registerRenderer(barrel_oak);
		registerRenderer(barrel_spruce);
		registerRenderer(barrel_dark_oak);
		registerRenderer(barrel_glass);
		registerRenderer(barrel_glass_colored);

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
	
	private static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock)
	{
		GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName().substring(5));
	}
	
	@SideOnly(Side.CLIENT)
	private static void registerBlockVariantsFromMeta(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		
		if (item != null)
		{
			ArrayList<ItemStack> list = new ArrayList<ItemStack>();
			
			block.getSubBlocks(item, null, list);
			
			for (ItemStack i : list)
			{
				ModelBakery.addVariantName(item, EN2Data.MODID + ":" + i.getUnlocalizedName().substring(5));
			}
		}
	}

	private static void registerRenderer(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		
		if (item.getHasSubtypes())
		{
			ArrayList<ItemStack> list = new ArrayList<ItemStack>();
			
			block.getSubBlocks(item, null, list);
			
			for (ItemStack i : list)
			{
				mesher.register(item, i.getItemDamage(), new ModelResourceLocation(EN2Data.MODID + ":" + i.getUnlocalizedName().substring(5), "inventory"));
			}
		}
		else
		{
			mesher.register(item, 0, new ModelResourceLocation(EN2Data.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}
	}
}
