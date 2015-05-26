package exnihilo2.proxy;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.barrels.renderer.BarrelRenderer;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.sieves.renderer.SieveRenderer;
import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import exnihilo2.items.EN2Items;

//Commands that only execute on the client.
public class ClientProxy extends Proxy {
	
	@Override
	public void registerRenderers()
	{
		registerItemRenderers();
		registerBlockRenderers();
	}
	
	private void registerItemRenderers()
	{
		registerRenderer(EN2Items.crook_wood);
		registerRenderer(EN2Items.crook_bone);
		registerRenderer(EN2Items.hammer_wood);
		registerRenderer(EN2Items.hammer_stone);
		registerRenderer(EN2Items.hammer_iron);
		registerRenderer(EN2Items.hammer_gold);
		registerRenderer(EN2Items.hammer_diamond);
		registerRenderer(EN2Items.mesh_silk_white);
		registerRenderer(EN2Items.mesh_wood);
	}
	
	private void registerBlockRenderers()
	{
		registerRenderer(EN2Blocks.barrel_wood);
		registerRenderer(EN2Blocks.barrel_stone);
		registerRenderer(EN2Blocks.barrel_glass);
		registerRenderer(EN2Blocks.barrel_glass_colored);
		registerRenderer(EN2Blocks.dust);
		registerRenderer(EN2Blocks.sieve_wood);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new BarrelRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySieve.class, new SieveRenderer());
	}
	
	private static void registerRenderer(Block block)
	{
		Item item = Item.getItemFromBlock(block);

		if (item.getHasSubtypes())
		{
			ArrayList<ItemStack> list = new ArrayList<ItemStack>();
			
			block.getSubBlocks(item, null, list);
			
			for (ItemStack i : list)
			{
				ModelBakery.addVariantName(i.getItem(), EN2.MODID + ":" + i.getUnlocalizedName().substring(5));
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i.getItemDamage(), new ModelResourceLocation(EN2.MODID + ":" + i.getUnlocalizedName().substring(5), "inventory"));
			}
		}
		else
		{
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(EN2.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}
	}
	
	private static void registerRenderer(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(EN2.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
