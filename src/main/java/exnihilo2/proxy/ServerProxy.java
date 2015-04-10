package exnihilo2.proxy;

import exnihilo2.EN2;

//Commands that only execute on the server.
public class ServerProxy implements Proxy {
	@Override
	public void Test()
	{
		EN2.log.info("Server test successful!");
	}

	@Override
	public void RegisterItemRenderers() 
	{
		//Do nothing because we're on the server side.
	}
}
