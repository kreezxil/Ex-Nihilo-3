package exnihilo2.world.generation;

import exnihilo2.EN2;
import exnihilo2.util.helpers.PositionHelper;
import exnihilo2.world.EN2World;
import exnihilo2.world.generation.templates.pojos.Template;
import net.minecraft.block.Block;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;

public class ChunkProviderVoidEnd extends ChunkProviderEnd{
	private World world;
	
	public ChunkProviderVoidEnd(World world, long par2) {
		super(world, par2);
		
		this.world = world;
	}

    @Override
    public Chunk provideChunk(int x, int z)
    {
        Chunk chunk = new Chunk(world, new ChunkPrimer(), x, z);
        
        chunk.generateSkylightMap();
        
        return chunk;
    }
    
    @Override
    public void populate(IChunkProvider par1IChunkProvider, int x, int z)
    {
    	if (x == 0 && z == 0)
        {
        	Template template = EN2World.getEndTemplate();
        	
        	if (template!= null)
        	{
        		template.generate(world, 8, 8); //Begin generation in the center of chunk 0,0.
        		
        		EntityDragon dragon = new EntityDragon(world);
                dragon.setLocationAndAngles(8.0D, 128.0D, 8.0D, world.rand.nextFloat() * 360.0F, 0.0F);
                world.spawnEntityInWorld(dragon);
        	}
        }
    }
}
