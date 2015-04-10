package exnihilo2.proxy;

import exnihilo2.EN2;
import exnihilo2.EN2Items;

//Commands that only execute on the client.
public class ClientProxy implements Proxy {
	@Override
	public void Test()
	{
		EN2.log.info("Client test successful!");
	}
	
	@Override
	public void RegisterItemRenderers()
	{
		EN2Items.RegisterItemRenderers();
		EN2.log.info("!!!!! Item renderers registered successfully!");
	}
}
