package exnihilo2.blocks.misc;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

public class BlockDust extends BlockFalling{

	public BlockDust()
	{
		super(Material.sand);
		
		this.setHardness(0.6f);
		this.setStepSound(soundTypeSnow);
		this.setHarvestLevel("shovel", 0);
	}
}
