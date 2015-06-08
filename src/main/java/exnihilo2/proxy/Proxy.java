package exnihilo2.proxy;

import exnihilo2.items.EN2Items;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

//Commands that execute on both the client and the server.
public abstract class Proxy {
	public void registerModels() {}
	public void registerRenderers() {}
}
