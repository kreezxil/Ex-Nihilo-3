package exnihilo2.client.models;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.client.models.furnaces.ModelFurnaceDirt;
import exnihilo2.client.models.furnaces.StateMapperFurnaceDirt;
import exnihilo2.fluids.EN2Fluids;

public class EN2Models {
	public static StateMapperBase furnace_dirt_states = new StateMapperFurnaceDirt();
	public static ModelFurnaceDirt furnace_dirt_model = new ModelFurnaceDirt();
	public static ModelResourceLocation furnace_dirt_model_normal = new ModelResourceLocation("exnihilo2:furnace_dirt", "normal");

	public static ModelResourceLocation witchwater_model_location = new ModelResourceLocation("exnihilo2:witchwater", "fluid");

	public static ModelResourceLocation azoth_model_location = new ModelResourceLocation("exnihilo2:azoth", "fluid");

	public static void register()
	{
		ModelLoader.setCustomStateMapper(EN2Blocks.furnace_dirt, furnace_dirt_states);

		ModelBakery.addVariantName(Item.getItemFromBlock(EN2Blocks.witchwater));
		ModelLoader.setCustomStateMapper(EN2Blocks.witchwater, new StateMapperBase()
		{
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return witchwater_model_location;
			}
		});
		
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(EN2Blocks.witchwater), new ItemMeshDefinition()
		{
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return witchwater_model_location;
			}
		});

		ModelBakery.addVariantName(Item.getItemFromBlock(EN2Blocks.azoth));
		ModelLoader.setCustomStateMapper(EN2Blocks.azoth, new StateMapperBase()
		{
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return azoth_model_location;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(EN2Blocks.azoth), new ItemMeshDefinition()
		{
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return azoth_model_location;
			}
		});
	}

	public static void bake(ModelBakeEvent event)
	{
		event.modelRegistry.putObject(ModelFurnaceDirt.model_normal, furnace_dirt_model);
		event.modelRegistry.putObject(ModelFurnaceDirt.model_inv, furnace_dirt_model);
	}
}
