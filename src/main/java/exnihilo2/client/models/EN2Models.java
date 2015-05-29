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
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "facing=north"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "facing=east"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "facing=south"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt", "facing=west"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt_lit", "normal"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt_lit", "inventory"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt_lit", "facing=north"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt_lit", "facing=east"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt_lit", "facing=south"), dirt_furnace_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:furnace_dirt_lit", "facing=west"), dirt_furnace_model);
	}
}
