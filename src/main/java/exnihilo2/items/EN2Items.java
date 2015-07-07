package exnihilo2.items;

import exnihilo2.blocks.EN2Blocks;
import exnihilo2.items.buckets.ItemBucketAzoth;
import exnihilo2.items.buckets.ItemBucketPorcelain;
import exnihilo2.items.buckets.ItemBucketPorcelainMilk;
import exnihilo2.items.buckets.ItemBucketPorcelainRaw;
import exnihilo2.items.crooks.ItemCrook;
import exnihilo2.items.hammers.ItemHammer;
import exnihilo2.items.materials.EN2ToolMaterials;
import exnihilo2.items.meshs.ItemMesh;
import exnihilo2.items.misc.ItemAsh;
import exnihilo2.items.misc.ItemAstrolabe;
import exnihilo2.items.misc.ItemEmeraldBlack;
import exnihilo2.items.misc.ItemMetalSalts;
import exnihilo2.items.misc.ItemPorcelain;
import exnihilo2.items.misc.ItemRust;
import exnihilo2.items.misc.ItemSlimeballBlack;
import exnihilo2.items.misc.ItemSpores;
import exnihilo2.items.misc.ItemStone;
import exnihilo2.items.ores.ItemOreSalts;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EN2Items {
	public static Item astrolabe_jade;
	
	public static Item bucket_porcelain_azoth;
	public static Item bucket_porcelain_raw;
	public static Item bucket_porcelain_empty;
	public static Item bucket_porcelain_water;
	public static Item bucket_porcelain_lava;
	public static Item bucket_porcelain_milk;
	public static Item bucket_porcelain_witchwater;
	
	public static Item bucket_azoth;
	public static Item bucket_witchwater;
	
	public static Item crook_wood;
	public static Item crook_bone;
	
	public static Item hammer_wood;
	public static Item hammer_stone;
	public static Item hammer_iron;
	public static Item hammer_gold;
	public static Item hammer_diamond;
	
	public static Item mesh_silk_white;
	public static Item mesh_wood;
	
	public static Item rust;
	public static Item metal_salts;
	public static Item metal_crystals;
	
	public static Item ash;
	public static Item porcelain;
	public static Item stone;
	public static Item emerald_black;
	public static Item slimeball_black;
	public static Item spores;
	
	public static void configure()
	{
		astrolabe_jade = new ItemAstrolabe().setUnlocalizedName("astrolabe_jade");
		
		//Buckets (Porcelain)
		bucket_porcelain_empty = new ItemBucketPorcelain(Blocks.air).setUnlocalizedName("bucket_porcelain_empty").setMaxStackSize(16);
		bucket_porcelain_azoth = new ItemBucketAzoth(EN2Blocks.azoth).setUnlocalizedName("bucket_porcelain_azoth").setContainerItem(bucket_porcelain_empty);
		bucket_porcelain_raw = new ItemBucketPorcelainRaw().setUnlocalizedName("bucket_porcelain_raw");
		bucket_porcelain_water = new ItemBucketPorcelain(Blocks.flowing_water).setUnlocalizedName("bucket_porcelain_water").setContainerItem(bucket_porcelain_empty);
		bucket_porcelain_lava = new ItemBucketPorcelain(Blocks.flowing_lava).setUnlocalizedName("bucket_porcelain_lava").setContainerItem(bucket_porcelain_empty);
		bucket_porcelain_milk = new ItemBucketPorcelainMilk().setUnlocalizedName("bucket_porcelain_milk");
		bucket_porcelain_witchwater = new ItemBucketPorcelain(EN2Blocks.witchwater).setUnlocalizedName("bucket_porcelain_witchwater").setContainerItem(bucket_porcelain_empty);
		
		//Buckets (Vanilla)
		bucket_azoth = new ItemBucketAzoth(EN2Blocks.azoth).setUnlocalizedName("bucket_azoth").setContainerItem(Items.bucket);
		bucket_witchwater = new ItemBucket(EN2Blocks.witchwater).setUnlocalizedName("bucket_witchwater").setContainerItem(Items.bucket);
		
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
		mesh_silk_white = new ItemMesh().setUnlocalizedName("mesh_silk_white").setMaxDamage(64);
		mesh_wood = new ItemMesh().setUnlocalizedName("mesh_wood").setMaxDamage(10);
		
		rust = new ItemRust().setUnlocalizedName("rust");
		metal_salts = new ItemMetalSalts().setUnlocalizedName("metal_salts");
		metal_crystals = new ItemOreSalts().setUnlocalizedName("ore_salts");
		
		//Misc
		ash = new ItemAsh().setUnlocalizedName("ash");
		porcelain = new ItemPorcelain().setUnlocalizedName("porcelain");
		stone = new ItemStone().setUnlocalizedName("stone");
		emerald_black = new ItemEmeraldBlack().setUnlocalizedName("emerald_black");
		slimeball_black = new ItemSlimeballBlack().setUnlocalizedName("slimeball_black");
		spores = new ItemSpores().setUnlocalizedName("spores");
		
		registerItems();
	}
	
	private static void registerItems()
	{
		registerItem(astrolabe_jade);
		
		registerItem(bucket_porcelain_azoth);
		registerItem(bucket_porcelain_raw);
		registerItem(bucket_porcelain_empty);
		registerItem(bucket_porcelain_water);
		registerItem(bucket_porcelain_lava);
		registerItem(bucket_porcelain_milk);
		registerItem(bucket_porcelain_witchwater);
		
		registerItem(bucket_azoth);
		registerItem(bucket_witchwater);
		
		registerItem(crook_wood);
		registerItem(crook_bone);
		
		registerItem(hammer_wood);
		registerItem(hammer_stone);
		registerItem(hammer_iron);
		registerItem(hammer_gold);
		registerItem(hammer_diamond);
		
		registerItem(mesh_silk_white);
		registerItem(mesh_wood);
		
		registerItem(rust);
		registerItem(metal_salts);
		registerItem(metal_crystals);
		
		registerItem(ash);
		registerItem(porcelain);
		registerItem(stone);
		registerItem(emerald_black);
		registerItem(slimeball_black);
		registerItem(spores);
	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
