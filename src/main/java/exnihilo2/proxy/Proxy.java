package exnihilo2.proxy;

import exnihilo2.EN2Items;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

//Commands that execute on both the client and the server.
public interface Proxy {
	public void Test();
	public void RegisterItemRenderers();
}
