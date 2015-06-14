package exnihilo2.blocks.fluids.base;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluid extends BlockFluidClassic{
	public static final PropertyFloat HEIGHT_NW = new PropertyFloat("height_nw");
	public static final PropertyFloat HEIGHT_SW = new PropertyFloat("height_sw");
	public static final PropertyFloat HEIGHT_SE = new PropertyFloat("height_se");
	public static final PropertyFloat HEIGHT_NE = new PropertyFloat("height_ne");
	public static final PropertyFloat DIRECTION = new PropertyFloat("direction");

	public BlockFluid(Fluid fluid, Material material) {
		super(fluid, material);
	}

	@Override
	protected BlockState createBlockState()
	{
		return new ExtendedBlockState(this, 
				new IProperty[] {LEVEL}, 
				new IUnlistedProperty[] { HEIGHT_SW, HEIGHT_NW, HEIGHT_SE, HEIGHT_NE, DIRECTION});
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		state = super.getExtendedState(state, world, pos);
		float heightNW = 0f, heightSW = 0f, heightSE = 0f, heightNE = 0f;

		if (world.getBlockState(pos.up()).getBlock().getMaterial().isLiquid())
		{
			heightNW = heightSW = heightSE = heightNE = 1.0f;
		}
		else
		{
			heightSE = this.getFluidHeight(world, pos);
			heightSW = this.getFluidHeight(world, pos.south());
			heightNW = this.getFluidHeight(world, pos.east().south());
			heightNE = this.getFluidHeight(world, pos.east());
		}

		IExtendedBlockState extState = (IExtendedBlockState) state;
		extState = extState.withProperty(HEIGHT_NW, heightNW);
		extState = extState.withProperty(HEIGHT_SW, heightSW);
		extState = extState.withProperty(HEIGHT_SE, heightSE);
		extState = extState.withProperty(HEIGHT_NE, heightNE);
		extState = extState.withProperty(DIRECTION, (float) BlockFluidBase.getFlowDirection(world, pos));

		return extState;
	}

	private float getFluidHeight(IBlockAccess blockAccess, BlockPos blockPosIn)
    {
        int i = 0;
        float f = 0.0F;

        for (int j = 0; j < 4; ++j)
        {
            BlockPos blockpos1 = blockPosIn.add(-(j & 1), 0, -(j >> 1 & 1));

            if (blockAccess.getBlockState(blockpos1.up()).getBlock().getMaterial().isLiquid())
            {
                return 1.0F;
            }

            IBlockState iblockstate = blockAccess.getBlockState(blockpos1);
            Material material1 = iblockstate.getBlock().getMaterial();

            if (material1.isLiquid())
            {
                int k = ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue();

                if (k >= 8 || k == 0)
                {
                    f += BlockLiquid.getLiquidHeightPercent(k) * 10.0F;
                    i += 10;
                }

                f += BlockLiquid.getLiquidHeightPercent(k);
                ++i;
            }
            else if (!material1.isSolid())
            {
                ++f;
                ++i;
            }
        }

        return 1.0F - f / (float)i;
    }

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		if(side == EnumFacing.UP && world.getBlockState(pos.up()).getBlock() == this)
		{
			return false;
		}	
		else 
		{
			return super.shouldSideBeRendered(world, pos, side);
		}
	}
	
	@Override
	public int getRenderType()
	{
		return 3;
	}
}
