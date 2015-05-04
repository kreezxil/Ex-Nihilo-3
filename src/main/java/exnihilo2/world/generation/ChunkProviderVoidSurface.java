package exnihilo2.world.generation;

import exnihilo2.EN2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class ChunkProviderVoidSurface extends ChunkProviderGenerate
{
	private World world;
	
    public ChunkProviderVoidSurface(World world)
    {
        super(world, world.getSeed(), false, "");
        
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
        Chunk chunk = new Chunk(world, new ChunkPrimer(), x, z);
        
        BiomeGenBase[] biomes = world.getWorldChunkManager().loadBlockGeneratorData(null, x * 16, z * 16, 16, 16);
        byte[] ids = chunk.getBiomeArray();

        for (int i = 0; i < ids.length; ++i)
        {
            ids[i] = (byte)biomes[i].biomeID;
        }

        chunk.generateSkylightMap();
        
        if (Map.getChunkContainsPoint(chunk, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ()))
        {
        	Map.generateSpawnPlatform(world, chunk, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ());
        }
        
        return chunk;
    }
    
    @Override
    public void populate(net.minecraft.world.chunk.IChunkProvider par1IChunkProvider, int par2, int par3)
    {
    	//Do nothing.
    }
}
