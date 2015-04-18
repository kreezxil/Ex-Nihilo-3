package exnihilo2.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
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
