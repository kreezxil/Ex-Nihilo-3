package exnihilo2;

import java.io.File;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.barrels.states.BarrelStates;
import exnihilo2.items.EN2Items;
import exnihilo2.items.materials.EN2ToolMaterials;
import exnihilo2.proxy.Proxy;
import exnihilo2.registries.composting.CompostRegistry;
import exnihilo2.world.EN2World;
import exnihilo2.world.manipulation.Moss;

@Mod(name = EN2.NAME, modid = EN2.MODID, version = EN2.VERSION)
public class EN2
{
	public static final String NAME = "Ex Nihilo 2";
	public static final String MODID = "exnihilo2";
	public static final String VERSION = "1.0";

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

		EN2ToolMaterials.initialize();

		EN2Items.initialize();
		EN2Blocks.initialize();
		BarrelStates.initialize(config);

		EN2Items.registerItems();
		EN2Blocks.registerBlocks();
		EN2Blocks.registerTileEntities();

		CompostRegistry.initialize();
		CompostRegistry.addVanillaRecipes();

		EN2World.initialize(config);

		if(config.hasChanged())
			config.save();
	}

	@EventHandler
	public void doInitialize(FMLInitializationEvent event)
	{
		proxy.registerRenderers();

		EN2World.registerWorldProviders();
	}

	@EventHandler
	public void postInitialize(FMLPostInitializationEvent event)
	{

	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onTextureStitchEvent(TextureStitchEvent.Pre e) {
		e.map.registerSprite(new ResourceLocation("exnihilo2:blocks/compost"));
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
}
