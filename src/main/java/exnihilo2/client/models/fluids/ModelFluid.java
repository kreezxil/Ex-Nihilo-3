package exnihilo2.client.models.fluids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.primitives.Ints;

import exnihilo2.blocks.fluids.base.BlockFluid;
import exnihilo2.util.Color;
import exnihilo2.util.Vertex;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModelFluid implements IFlexibleBakedModel, ISmartBlockModel{
	private final Map<List<Float>, ModelFluid> models = new HashMap<List<Float>, ModelFluid>();
	private final Fluid fluid;
	private static List EMPTY_LIST = new ArrayList();

	private float heightNW;
	private float heightSW;
	private float heightSE;
	private float heightNE;
	private float direction;

	public ModelFluid(Fluid fluid)
	{
		this.fluid = fluid;
		this.heightNW = 1.0f;
		this.heightSW = 1.0f;
		this.heightSE = 1.0f;
		this.heightNE = 1.0f;
		this.direction = -1000f;
	}

	public ModelFluid(Fluid fluid, List<Float> params)
	{
		this.fluid = fluid;
		this.heightNW = params.get(0);
		this.heightSW = params.get(1);
		this.heightSE = params.get(2);
		this.heightNE = params.get(3);
		this.direction = params.get(4);
	}

	@Override
	public IBakedModel handleBlockState(IBlockState state)
	{
		IExtendedBlockState extState = (IExtendedBlockState) state;
		float heightNW = extState.getValue(BlockFluid.HEIGHT_NW);
		float heightSW = extState.getValue(BlockFluid.HEIGHT_SW);
		float heightSE = extState.getValue(BlockFluid.HEIGHT_SE);
		float heightNE = extState.getValue(BlockFluid.HEIGHT_NE);
		float direction = extState.getValue(BlockFluid.DIRECTION);

		List<Float> params = Arrays.asList(heightSE, heightSW, heightNW, heightNE, direction);

		if (models.containsKey(params))
		{
			return models.get(params);
		}
		else
		{
			ModelFluid model = new ModelFluid(this.fluid, params);
			models.put(params, model);
			return model;
		}
	}
	@Override
	public List getFaceQuads(EnumFacing facing) {
		List<BakedQuad> quads = new ArrayList<BakedQuad>();

		Vec3[] vectors = getCubeVectors(0.0d, 1.0d, heightNW, heightSW, heightNE, heightSE);

		if (facing == EnumFacing.UP)
		{
			if(this.direction > -999f)
			{
				quads.add(getQuad(vectors, this.fluid.getFlowingIcon(), facing, this.direction));
			}
			else
			{
				quads.add(getQuad(vectors, this.fluid.getStillIcon(), facing));
			}
		}
		else
		{
			quads.add(getQuad(vectors, this.fluid.getFlowingIcon(), facing));
		}

		return quads;
	}
	@Override
	public List getGeneralQuads() {
		return EMPTY_LIST;
	}

	private static Vec3[] getCubeVectors(double min, double max, double heightNW, double heightSW, double heightNE, double heightSE)
	{
		Vec3[] vectors = 
			{
				new Vec3(max, heightSE, max),
				new Vec3(max, heightNE, min),
				new Vec3(min, heightNW, min),
				new Vec3(min, heightSW, max),
				new Vec3(min, min, max),
				new Vec3(min, min, min),
				new Vec3(max, min, min),
				new Vec3(max, min, max)
			};

		return vectors;
	}

	private static BakedQuad getQuad(Vec3[] vectors, TextureAtlasSprite texture, EnumFacing side)
	{
		Vec3[] v = getVectorsForSide(vectors, side);

		return new BakedQuad(Ints.concat(
				new Vertex((float)v[0].xCoord, (float)v[0].yCoord, (float)v[0].zCoord, getColorFromSide(side), texture.getInterpolatedU(16f),texture.getInterpolatedV(16f)).toIntArray(),
				new Vertex((float)v[1].xCoord, (float)v[1].yCoord, (float)v[1].zCoord, getColorFromSide(side), texture.getInterpolatedU(16f),texture.getInterpolatedV(0f)).toIntArray(),
				new Vertex((float)v[2].xCoord, (float)v[2].yCoord, (float)v[2].zCoord, getColorFromSide(side), texture.getInterpolatedU(0f),texture.getInterpolatedV(0f)).toIntArray(),
				new Vertex((float)v[3].xCoord, (float)v[3].yCoord, (float)v[3].zCoord, getColorFromSide(side), texture.getInterpolatedU(0f),texture.getInterpolatedV(16f)).toIntArray()
				), 0, side);
	}

	private static BakedQuad getQuad(Vec3[] vectors, TextureAtlasSprite texture, EnumFacing side, float direction)
	{
		Vec3[] v = getVectorsForSide(vectors, side);

		float sin = MathHelper.sin(direction) * 0.25F;
		float cos = MathHelper.cos(direction) * 0.25F;

		float f13 = texture.getInterpolatedU((double)(8.0F + (-cos -sin) * 16.0F));
		float f17 = texture.getInterpolatedV((double)(8.0F + (-cos +sin) * 16.0F));
		float f14 = texture.getInterpolatedU((double)(8.0F + (-cos +sin) * 16.0F));
		float f18 = texture.getInterpolatedV((double)(8.0F + (cos +sin) * 16.0F));
		float f15 = texture.getInterpolatedU((double)(8.0F + (cos +sin) * 16.0F));
		float f19 = texture.getInterpolatedV((double)(8.0F + (cos -sin) * 16.0F));
		float f16 = texture.getInterpolatedU((double)(8.0F + (cos -sin) * 16.0F));
		float f20 = texture.getInterpolatedV((double)(8.0F + (-cos -sin) * 16.0F));

		return new BakedQuad(Ints.concat(
				new Vertex((float)v[0].xCoord, (float)v[0].yCoord, (float)v[0].zCoord, Color.WHITE, f15, f19).toIntArray(),
				new Vertex((float)v[1].xCoord, (float)v[1].yCoord, (float)v[1].zCoord, Color.WHITE, f16, f20).toIntArray(),
				new Vertex((float)v[2].xCoord, (float)v[2].yCoord, (float)v[2].zCoord, Color.WHITE, f13, f17).toIntArray(),
				new Vertex((float)v[3].xCoord, (float)v[3].yCoord, (float)v[3].zCoord, Color.WHITE, f14, f18).toIntArray()
				), 0, side);
	}

	private static Vec3[] getVectorsForSide(Vec3[] vectors, EnumFacing side)
	{
		switch (side)
		{
		case DOWN:
			return new Vec3[] { vectors[4], vectors[5], vectors[6], vectors[7] };
		case EAST:
			return new Vec3[] { vectors[6], vectors[1], vectors[0], vectors[7] };
		case NORTH:
			return new Vec3[] { vectors[5], vectors[2], vectors[1], vectors[6] };
		case SOUTH:
			return new Vec3[] { vectors[7], vectors[0], vectors[3], vectors[4] };
		case UP:
			return new Vec3[] { vectors[0], vectors[1], vectors[2], vectors[3] };
		case WEST:
			return new Vec3[] { vectors[4], vectors[3], vectors[2], vectors[5] };
		}

		return null;
	}
	
	private static Color getColorFromSide(EnumFacing facing)	//Copied from FaceBakery (Only the end result, and not the process)
	{
		if(facing == EnumFacing.DOWN)
			return new Color(-8421505);
		if(facing == EnumFacing.SOUTH || facing == EnumFacing.NORTH)
			return new Color(-3355444);
		if(facing == EnumFacing.WEST || facing == EnumFacing.EAST)
			return new Color(-6710887);
		
		return new Color(-1);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return false;
	}
	@Override
	public boolean isGui3d() {
		return false;
	}
	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}
	@Override
	public TextureAtlasSprite getTexture() {
		if (this.fluid != null)
		{
			return fluid.getStillIcon();
		}
		else
		{
			return FluidRegistry.WATER.getStillIcon();
		}
	}
	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(new ModelResourceLocation("exnihilo2:dust", "inventory")).getItemCameraTransforms();
	}

	@Override
	public VertexFormat getFormat() {
		return Vertex.getFormat();
	}
}
