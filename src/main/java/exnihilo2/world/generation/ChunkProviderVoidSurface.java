package exnihilo2.world.generation;

import java.lang.reflect.Method;
import java.util.ArrayList;

import exnihilo2.EN2;
import exnihilo2.world.EN2World;
import exnihilo2.world.generation.maps.pojos.Map;
import exnihilo2.world.generation.maps.pojos.MapBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
        
        chunk.generateSkylightMap();
        
        if (getChunkContainsPoint(chunk, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ()))
        {
        	buildMap(world, chunk, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ());
        }
        
        return chunk;
    }
    
    @Override
    public void populate(net.minecraft.world.chunk.IChunkProvider par1IChunkProvider, int par2, int par3)
    {
    	//Do nothing.
    }
    
    private void buildMap(World world, Chunk chunk, int xOffset, int zOffset)
    {
    	Map map = EN2World.getMap();
    	
    	if (map!= null)
    	{
    		ArrayList<MapBlock> blocks = map.getBlocks();
    		
    		for (MapBlock b : blocks)
    		{
    			String[] names = b.getId().split(":");
    			
    			//GameRegistry is currently broken. Using an ugly method I found on minecraftforgeforums instead. :(
    			//Block block = GameRegistry.findBlock(names[0], names[1]);
    			Block block = findBlock(names[0], names[1]);
    			
    			if (block != null)
    			{
    				setBlock(world, chunk, b.getX() + xOffset, b.getY() + map.getSpawnYLevel(), b.getZ() + zOffset, block, b.getMeta());
    			}
    			else
    			{
    				EN2.log.error("Unable to locate block (" + b.getId() + ").");
    			}
    		}
    	}
    	else
    	{
    		EN2.log.error("Failed to load map.");
    	}
    }
    
    public static boolean getChunkContainsPoint(Chunk chunk, int x, int z)
	{
		int chunkXMin = chunk.xPosition * 16;
    	int chunkXMax = chunk.xPosition * 16 + 16;
    	int chunkZMin = chunk.zPosition * 16;
    	int chunkZMax = chunk.zPosition * 16 + 16;
    	
    	return (x >= chunkXMin && x < chunkXMax && z >= chunkZMin && z < chunkZMax);
	}
	
	public static void setBlock(World world, Chunk chunk, int x, int y, int z, Block block, int meta)
	{
		BlockPos pos = new BlockPos(x, y, z);
		
		if (getChunkContainsPoint(chunk, pos.getX(), pos.getZ()))
		{
			chunk.setBlockState(pos, block.getStateFromMeta(meta));
		}
		else
		{
			world.getChunkFromBlockCoords(pos).setBlockState(pos, block.getStateFromMeta(meta));
		}
	}
	
	//Method found @ http://www.minecraftforge.net/forum/index.php?topic=29989.0
	/**
	* Tries to approach all legit ways to get a block from the game registry
	* but will go hackish when needed to to get the desired result.
	* @param modid String The modname
	* @param blockname String the block name
	* @return Blocks.air on not finding anything or the desired block.
	*/
	public static Block findBlock(String modid, String blockname) {
		Block find = GameRegistry.findBlock(modid,blockname);
		// legit way fails?
		if(find == null) {
			String searchkey = modid+":"+blockname;
			// Lets fire up the reflection...
			// You might want to put the map somewhere so you
			// don't have to reflect every time. saves a lot of cpu time.
			Class x = GameData.class;
				try {
					Method method = x.getDeclaredMethod("getMain");
					method.setAccessible(true);
					GameData gamedata = (GameData)method.invoke(null);
					// and I mean saving the below list b
					FMLControlledNamespacedRegistry<Block> b = gamedata.getBlockRegistry();
					// lets be gracious and give it a chance to find it this way
					if(b.containsKey(searchkey)) {
						find = b.getObject(searchkey);
					}
					else {
						// take a wild stab. returns air if nothing found
						find = b.getObject(searchkey);
						if(find != Blocks.air) {
							//WhoTookMyCookies.log.warn("Chest found: "+GameRegistry.findUniqueIdentifierFor(b.getObject("minecraft:chest")).name);
						}
						else {
							if(!searchkey.equals("minecraft:air")) {
								return null;
							}
						}
					}
				}
				catch(Exception ex) {
					// your error handling here
				}
			}
		
		return find;
	}
}
