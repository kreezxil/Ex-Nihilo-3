package exnihilo2.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import exnihilo2.EN2;
import exnihilo2.EN2Blocks;
import exnihilo2.EN2Items;

//Commands that only execute on the client.
public class ClientProxy implements Proxy {
	@Override
	public void Test()
	{
		EN2.log.info("Client test successful!");
	}
	
	@Override
	public void RegisterRenderers()
	{
		EN2Items.RegisterItemRenderers();
		EN2Blocks.RegisterBlockRenderers();
	}
}
