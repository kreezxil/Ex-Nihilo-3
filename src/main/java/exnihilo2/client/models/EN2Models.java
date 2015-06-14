package exnihilo2.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.client.models.fluids.ModelFluid;
import exnihilo2.client.models.furnaces.ModelFurnaceDirt;
import exnihilo2.client.models.furnaces.StateMapperFurnaceDirt;
import exnihilo2.fluids.EN2Fluids;

public class EN2Models {
	public static StateMapperBase furnace_dirt_states = new StateMapperFurnaceDirt();
	public static ModelFurnaceDirt furnace_dirt_model = new ModelFurnaceDirt();
	public static ModelResourceLocation furnace_dirt_model_normal = new ModelResourceLocation("exnihilo2:furnace_dirt", "normal");
	
	public static StateMapperBase witchwater_states = (new StateMap.Builder()).addPropertiesToIgnore(BlockFluidBase.LEVEL).build();
	public static ModelFluid witchwater_model = new ModelFluid(EN2Fluids.witchwater);
	
	public static void register()
	{
		ModelLoader.setCustomStateMapper(EN2Blocks.furnace_dirt, furnace_dirt_states);
		ModelLoader.setCustomStateMapper(EN2Blocks.witchwater, witchwater_states); 
	}
	
	public static void bake(ModelBakeEvent event)
	{
		event.modelRegistry.putObject(ModelFurnaceDirt.model_normal, furnace_dirt_model);
		event.modelRegistry.putObject(ModelFurnaceDirt.model_inv, furnace_dirt_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:witchwater", "normal"), witchwater_model);
		event.modelRegistry.putObject(new ModelResourceLocation("exnihilo2:witchwater", "inventory"), witchwater_model);
	}
}
