package exnihilo2;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.client.models.EN2Models;
import exnihilo2.client.models.furnaces.ModelFurnaceDirt;
import exnihilo2.client.textures.EN2Textures;
import exnihilo2.compatibility.EN2Compatibility;
import exnihilo2.compatibility.veinminer.VeinMinerCompatibility;
import exnihilo2.compatibility.waila.WailaCompatibility;
import exnihilo2.crafting.EN2Crafting;
import exnihilo2.crafting.recipes.MobDrops;
import exnihilo2.entities.EN2Entities;
import exnihilo2.fluids.EN2Fluids;
import exnihilo2.items.EN2BucketHandler;
import exnihilo2.items.EN2FuelHandler;
import exnihilo2.items.EN2Items;
import exnihilo2.items.materials.EN2ToolMaterials;
import exnihilo2.proxy.Proxy;
import exnihilo2.registries.EN2Registries;
import exnihilo2.registries.composting.CompostRegistry;
import exnihilo2.registries.hammering.HammerRegistry;
import exnihilo2.registries.sifting.SieveRegistry;
import exnihilo2.world.EN2World;
import exnihilo2.world.manipulation.Moss;

@Mod(name = EN2.NAME, modid = EN2.MODID, version = EN2.VERSION) //, dependencies = "required-after:Forge@[11.14.2.1423,)"
public class EN2
{
	@Instance(EN2.MODID)
	public static EN2 instance;
	
	public static final String NAME = "Ex Nihilo 2";
	public static final String MODID = "exnihilo2";
	public static final String VERSION = "@EN2_VERSION@";

	@SidedProxy(serverSide = "exnihilo2.proxy.ServerProxy", clientSide = "exnihilo2.proxy.ClientProxy")
	public static Proxy proxy;

	public static Logger log = LogManager.getLogger(EN2.NAME);
	public static String path;
	public static Configuration config;

	@EventHandler
	public void preInitialize(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);

		path = event.getModConfigurationDirectory().getAbsolutePath() + File.separator + "ExNihilo2" + File.separator;
		config = new Configuration(new File(path + "ExNihilo2.cfg"));

		EN2Fluids.register();
		EN2ToolMaterials.configure();
		EN2Blocks.configure();
		EN2Items.configure();
		EN2Crafting.configure(config);
		BarrelStates.configure(config);
		EN2World.configure(config);

		EN2Blocks.registerTileEntities();
		EN2BucketHandler.registerBuckets();
		EN2Crafting.registerRecipes();
		EN2Entities.configure();
		EN2Registries.configure(config);
		EN2Compatibility.configure(config);
		
		proxy.registerModels();
		
		if(config.hasChanged())
			config.save();
	}

	@EventHandler
	public void doInitialize(FMLInitializationEvent event)
	{
		proxy.registerRenderers();

		EN2World.registerWorldProviders();
		EN2Compatibility.initialize();
		
		GameRegistry.registerFuelHandler(new EN2FuelHandler());
	}

	@EventHandler
	public void postInitialize(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EN2BucketHandler());
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onTextureStitchEvent(TextureStitchEvent.Pre e)
	{
		EN2Textures.registerCustomTextures(e.map);
		EN2Textures.setMeshTextures();
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
    public void onModelBakeEvent(ModelBakeEvent event)
    {
		EN2Models.bake(event);
    }

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e)
	{
		if (!e.world.isRemote && e.world instanceof WorldServer)
		{
			EN2World.load(e.world);
		}
	}

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent e)
	{
		if (e.side == Side.SERVER && e.phase == TickEvent.Phase.START)
		{
			EN2World.tick(e.world);
		}
	}
	
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) 
	{
		MobDrops.onMobDeath(event);
	}
}
