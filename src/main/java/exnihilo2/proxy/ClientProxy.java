package exnihilo2.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import exnihilo2.EN2;
import exnihilo2.EN2Blocks;
import exnihilo2.EN2Items;

//Commands that only execute on the client.
public class ClientProxy extends Proxy {
	
	@Override
	public void registerRenderers()
	{
		EN2Items.registerItemRenderers();
		EN2Blocks.registerBlockRenderers();
	}
}
