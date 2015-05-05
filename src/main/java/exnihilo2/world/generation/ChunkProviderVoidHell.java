package exnihilo2.world.generation;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;

public class ChunkProviderVoidHell extends ChunkProviderHell{
	private World world;
	
	public ChunkProviderVoidHell(World world, boolean shouldGenNetherFortress, long seed)
	{
		super(world, false, seed);
		this.world = world;
	}

	@Override 
	public Chunk provideChunk(BlockPos pos)
	{ 
		return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4); 
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		ChunkPrimer primer = new ChunkPrimer();
		Chunk chunk = new Chunk(world, primer, x, z);

        chunk.generateSkylightMap();
        
        return chunk;
    }
    
    @Override
    public void populate(IChunkProvider par1IChunkProvider, int x, int z)
    {
//    	if (WorldData.allowNetherFortresses)
//    	{
//    		super.populate(par1IChunkProvider, par2, par3);
//    	}
    }
}
