package exnihilo2.proxy;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import exnihilo2.EN2;

//Commands that only execute on the server.
public class ServerProxy implements Proxy {
	@Override
	public void Test()
	{
		EN2.log.info("Server test successful!");
	}

	@Override
	public void RegisterRenderers() 
	{
		//Do nothing because we're on the server side.
	}
}
