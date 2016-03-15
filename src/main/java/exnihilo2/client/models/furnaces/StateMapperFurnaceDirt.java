package exnihilo2.client.models.furnaces;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class StateMapperFurnaceDirt extends StateMapperBase{
	
	@Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState iBlockState) {
      return ModelFurnaceDirt.model_normal;
    }
}
