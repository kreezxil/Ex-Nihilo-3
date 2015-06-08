package exnihilo2.client.models.furnaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.primitives.Ints;

import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.furnaces.BlockFurnaceDirt;
import exnihilo2.client.textures.files.TextureLocations;
import exnihilo2.util.Color;
import exnihilo2.util.Vertex;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ISmartBlockModel;

public class ModelFurnaceDirt implements IFlexibleBakedModel, ISmartBlockModel {
	public static final ModelResourceLocation model_normal = new ModelResourceLocation("exnihilo2:model_furnace_dirt", "normal");
	public static final ModelResourceLocation model_inv = new ModelResourceLocation("exnihilo2:furnace_dirt", "inventory");
	
	public static final ItemStack furnace = new ItemStack(Blocks.furnace);
	private EnumFacing facing;
	private boolean burning;
	
	public ModelFurnaceDirt()
	{
		this.facing = EnumFacing.NORTH;
		this.burning = false;
	}
	
	public ModelFurnaceDirt(EnumFacing facing, boolean burning)
	{
		this.facing = facing;
		this.burning = burning;
	}

	@Override
    public boolean isGui3d() { return true; }

    @Override
    public boolean isAmbientOcclusion() { return false; }

    @Override
    public boolean isBuiltInRenderer() { return false; }


	@Override
	public TextureAtlasSprite getTexture() {
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("exnihilo2:blocks/furnace_dirt_side");
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() 
	{
		return Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(furnace).getItemCameraTransforms();
	}

	@Override
	public List<BakedQuad> getFaceQuads(EnumFacing side) {
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<BakedQuad> getGeneralQuads() {
		TextureAtlasSprite sideTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("exnihilo2:blocks/furnace_dirt_side");
		TextureAtlasSprite frontTextureOn = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("exnihilo2:blocks/furnace_dirt_front_on");
		TextureAtlasSprite frontTextureOff = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("exnihilo2:blocks/furnace_dirt_front_off");

		List<BakedQuad> quads = new ArrayList<BakedQuad>();
		
		Vec3[] vectors = getCubeVectors(0.0d, 1.0d);
		
		for(EnumFacing f : EnumFacing.values())
        {
			if (this.facing == f)
			{
				if (this.burning)
				{
					quads.add(getQuad(vectors, frontTextureOn, f));
				}
				else
				{
					quads.add(getQuad(vectors, frontTextureOff, f));
				}
			}
			else
			{
				quads.add(getQuad(vectors, sideTexture, f));
			}
        }
		
		return quads;
	}
	
	private static Vec3[] getCubeVectors(double min, double max)
	{
		Vec3[] vectors = 
			{
				new Vec3(max, max, max),
				new Vec3(max, max, min),
				new Vec3(min, max, min),
				new Vec3(min, max, max),
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
	            new Vertex((float)v[0].xCoord, (float)v[0].yCoord, (float)v[0].zCoord, Color.WHITE, texture.getInterpolatedU(16f),texture.getInterpolatedV(16f)).toIntArray(),
	            new Vertex((float)v[1].xCoord, (float)v[1].yCoord, (float)v[1].zCoord, Color.WHITE, texture.getInterpolatedU(16f),texture.getInterpolatedV(0f)).toIntArray(),
	            new Vertex((float)v[2].xCoord, (float)v[2].yCoord, (float)v[2].zCoord, Color.WHITE, texture.getInterpolatedU(0f),texture.getInterpolatedV(0f)).toIntArray(),
	            new Vertex((float)v[3].xCoord, (float)v[3].yCoord, (float)v[3].zCoord, Color.WHITE, texture.getInterpolatedU(0f),texture.getInterpolatedV(16f)).toIntArray()
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

	@Override
	public VertexFormat getFormat() {
		return Vertex.getFormat();
	}

	@Override
	public IBakedModel handleBlockState(IBlockState state) {
		return new ModelFurnaceDirt((EnumFacing)state.getValue(BlockFurnaceDirt.FACING), ((Boolean)state.getValue(BlockFurnaceDirt.BURNING)).booleanValue());
	}
}
