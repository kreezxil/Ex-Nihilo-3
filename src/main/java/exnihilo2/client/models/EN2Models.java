package exnihilo2.client.models;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import exnihilo2.client.models.furnaces.ModelFurnaceDirt;

public class EN2Models {
	public static void register(ModelBakeEvent event)
	{
		ModelFurnaceDirt dirt_furnace_model = new ModelFurnaceDirt();
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "normal"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "inventory"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "burning=false,facing=north"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "burning=false,facing=east"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "burning=false,facing=south"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "burning=false,facing=west"), dirt_furnace_model);
		//event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "normal"), dirt_furnace_model);
		//event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "inventory"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "burning=true,facing=north"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "burning=true,facing=east"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "burning=true,facing=south"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "burning=true,facing=west"), dirt_furnace_model);
	}
}
