package exnihilo2;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
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
import exnihilo2.blocks.barrels.BarrelStates;
import exnihilo2.items.materials.EN2ToolMaterials;
import exnihilo2.proxy.Proxy;
import exnihilo2.registries.CompostRegistry;
import exnihilo2.world.generation.WorldTypeSkyblock;
import exnihilo2.world.manipulation.Moss;

@Mod(name = EN2Data.NAME, modid = EN2Data.MODID, version = EN2Data.VERSION)
public class EN2
{
	private WorldType worldType;
	
	 @SidedProxy(serverSide = "exnihilo2.proxy.ServerProxy", clientSide = "exnihilo2.proxy.ClientProxy")
	 public static Proxy proxy;
	 
	 public static Logger log = LogManager.getLogger(EN2Data.NAME);
	
    @EventHandler
	public void preInitialize(FMLPreInitializationEvent event)
	{
    	MinecraftForge.EVENT_BUS.register(this);
    	FMLCommonHandler.instance().bus().register(this);
    	
    	EN2ToolMaterials.initialize();
    	
    	EN2Items.initialize();
    	EN2Blocks.initialize();
    	BarrelStates.initialize();
    	
    	EN2Items.registerItems();
    	EN2Blocks.registerBlocks();
    	EN2Blocks.registerTileEntities();
    	
    	CompostRegistry.addVanillaRecipes();
	}
    
    @EventHandler
    public void doInitialize(FMLInitializationEvent event)
    {
    	proxy.registerRenderers();
    	
    	worldType = new WorldTypeSkyblock();
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
    public void onWorldTick(TickEvent.WorldTickEvent e)
    {
    	if (e.side == Side.SERVER && e.phase == TickEvent.Phase.START)
    	{
    		ChunkProviderServer provider = (ChunkProviderServer)e.world.getChunkProvider();
            for (Object o : provider.func_152380_a().toArray())
            {
            	Moss.grow(e.world, (Chunk)o);
            }
    	}
    }
}
