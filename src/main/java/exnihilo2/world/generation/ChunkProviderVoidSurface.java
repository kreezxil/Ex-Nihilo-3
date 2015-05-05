package exnihilo2.world.generation;

import java.lang.reflect.Method;
import java.util.ArrayList;

import exnihilo2.EN2;
import exnihilo2.world.EN2World;
import exnihilo2.world.generation.maps.pojos.Map;
import exnihilo2.world.generation.maps.pojos.MapBlock;
import exnihilo2.world.generation.maps.pojos.MapItem;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
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
    public Chunk provideChunk(int x, int z)
    {
        Chunk chunk = new Chunk(world, new ChunkPrimer(), x, z);

        chunk.generateSkylightMap();
        
        return chunk;
    }
    
    @Override
    public void populate(IChunkProvider provider, int x, int z)
    {
    	Chunk chunk = world.getChunkFromChunkCoords(x, z);
    	
    	if (isChunkSpawn(world, chunk))
        {
        	buildMap(world, chunk, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ());
        }
    }
    
    private void buildMap(World world, Chunk chunk, int xOffset, int zOffset)
    {
    	Map map = EN2World.getMap();
    	
    	if (map!= null)
    	{
    		ArrayList<MapBlock> blocks = map.getBlocks();
    		
    		for (MapBlock b : blocks)
    		{
    			//GameRegistry is currently broken. Using an ugly method I found on minecraftforgeforums instead. :(
    			//Block block = GameRegistry.findBlock(names[0], names[1]);
    			Block block = findBlock(b.getId());
    			
    			if (block != null)
    			{
    				int x = b.getX() + xOffset;
    				int y = b.getY() + map.getSpawnYLevel();
    				int z = b.getZ() + zOffset;
    				
    				setBlock(world, chunk, x, y, z, block, b.getMeta());
    				
    				TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
    				if (b.getContents() != null && te != null && te instanceof IInventory)
    				{
    					IInventory inv = (IInventory) te;
    					
    					if (inv != null)
    					{
    						int i = 0;
    						int max = inv.getSizeInventory();
    						
    						for (MapItem contentItem : b.getContents())
    						{
    							if (i < max && contentItem.getCount() > 0)
    							{
    								Item item = findItem(contentItem.getId());
    								
    								if (item != null)
    									inv.setInventorySlotContents(i, new ItemStack(item, contentItem.getCount(), contentItem.getMeta()));
    							}
    								
    							i++;
    						}
    					}
    				}
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
    
	public static void setBlock(World world, Chunk chunk, int x, int y, int z, Block block, int meta)
	{
		BlockPos pos = new BlockPos(x, y, z);
		
		if (getChunkContainsPoint(chunk, x, z))
		{
			chunk.setBlockState(pos, block.getStateFromMeta(meta));
		}
		else
		{
			world.getChunkFromBlockCoords(pos).setBlockState(pos, block.getStateFromMeta(meta));
		}
	}
	
	public static boolean isChunkSpawn(World world, Chunk chunk)
	{
		return getChunkContainsPoint(chunk, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ());
	}
	
	public static boolean getChunkContainsPoint(Chunk chunk, int x, int z)
	{
    	return (x >> 4 == chunk.xPosition && z >> 4 == chunk.zPosition);
	}
	
	public static Block findBlock(String id)
	{
		String[] names = id.split(":");
		
		//GameRegistry is currently broken. Using an ugly method I found on minecraftforgeforums instead. :(
		//TODO: Uncomment this when someone fixes it.
		//Block block = GameRegistry.findBlock(names[0], names[1]);
		Block block = findBlock(names[0], names[1]);
		
		return block;
	}
	
	public static Item findItem(String id)
	{
		String[] names = id.split(":");
		
		Item item = GameRegistry.findItem(names[0], names[1]);
		
		return item;
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
