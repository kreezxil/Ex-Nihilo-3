package exnihilo2.world.generation;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE;

import java.lang.reflect.Field;
import java.util.Random;

import exnihilo2.EN2;
import exnihilo2.util.helpers.PositionHelper;
import exnihilo2.world.EN2World;
import exnihilo2.world.generation.templates.pojos.Template;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderVoidHell extends ChunkProviderHell{
	private World world;
	MapGenNetherBridge fortresses;
	
	public ChunkProviderVoidHell(World world, boolean shouldGenNetherFortress, long seed)
	{
		super(world, true, seed);
		this.world = world;
		
		this.fortresses = (MapGenNetherBridge) TerrainGen.getModdedMapGen(new MapGenNetherBridge(), NETHER_BRIDGE);
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		ChunkPrimer primer = new ChunkPrimer();
		Chunk chunk = new Chunk(world, primer, x, z);
        
        if (EN2World.getNetherFortressesAllowed() && fortresses != null)
        {
            this.fortresses.generate(this, world, x, z, primer);
        }
        
        chunk.generateSkylightMap();
        
        return chunk;
    }
    
    @Override
    public void populate(IChunkProvider provider, int x, int z)
    {
    	if (PositionHelper.isChunkSpawn(world, world.getChunkFromChunkCoords(x, z)))
        {
        	Template template = EN2World.getNetherTemplate();
        	
        	if (template!= null)
        	{
        		double xSpawn = (double)world.getWorldInfo().getSpawnX() / world.provider.getMovementFactor();
        		double zSpawn = (double)world.getWorldInfo().getSpawnZ() / world.provider.getMovementFactor();
        		
        		template.generate(world, (int)xSpawn, (int)zSpawn);
        	}
        }
    	
    	if (EN2World.getNetherFortressesAllowed() && fortresses != null)
        {	
    		fortresses.generateStructure(world, world.rand, new ChunkCoordIntPair(x,z));
        }
    }
    
    
}
