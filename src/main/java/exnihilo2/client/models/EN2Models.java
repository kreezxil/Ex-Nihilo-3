package exnihilo2.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.client.models.furnaces.ModelFurnaceDirt;
import exnihilo2.client.models.furnaces.StateMapperFurnaceDirt;

public class EN2Models {
	public static StateMapperBase furnace_dirt_states = new StateMapperFurnaceDirt();
	public static ModelFurnaceDirt furnace_dirt_model = new ModelFurnaceDirt();
	public static ModelResourceLocation furnace_dirt_model_normal = new ModelResourceLocation("exnihilo2:furnace_dirt", "normal");
	
	public static void register()
	{
		ModelLoader.setCustomStateMapper(EN2Blocks.furnace_dirt, furnace_dirt_states);
	}
	
	public static void bake(ModelBakeEvent event)
	{
		event.modelRegistry.putObject(ModelFurnaceDirt.model_normal, furnace_dirt_model);
		event.modelRegistry.putObject(ModelFurnaceDirt.model_inv, furnace_dirt_model);
	}
}
