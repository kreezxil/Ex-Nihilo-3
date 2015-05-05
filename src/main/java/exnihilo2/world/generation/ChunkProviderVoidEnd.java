package exnihilo2.world.generation;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkProviderEnd;

public class ChunkProviderVoidEnd extends ChunkProviderEnd{
	private World world;
	
	public ChunkProviderVoidEnd(World world, long par2) {
		super(world, par2);
		
		this.world = world;
	}

    @Override public Chunk provideChunk(BlockPos pos)
    { 
    	return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4); 
    }
    
    @Override
    public Chunk provideChunk(int x, int z)
    {
        Chunk chunk = new Chunk(world, new ChunkPrimer(), x, z);
        
        chunk.generateSkylightMap();
        
        return chunk;
    }
}
