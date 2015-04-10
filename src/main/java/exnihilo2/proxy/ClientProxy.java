package exnihilo2.proxy;

import exnihilo2.EN2;

//Commands that only execute on the client.
public class ClientProxy extends Proxy {
	@Override
	public void Test()
	{
		EN2.log.debug("Test successful!");
	}
}
