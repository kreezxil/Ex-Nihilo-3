package exnihilo2.world.generation;

import java.lang.reflect.Method;
import java.util.ArrayList;

import exnihilo2.EN2;
import exnihilo2.util.helpers.GameRegistryHelper;
import exnihilo2.world.EN2World;
import exnihilo2.world.generation.templates.pojos.Template;
import exnihilo2.world.generation.templates.pojos.TemplateBlock;
import exnihilo2.world.generation.templates.pojos.TemplateItem;
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
    	if (isChunkSpawn(world, world.getChunkFromChunkCoords(x, z)))
        {
        	buildMap(world, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ());
        }
    }
    
    private void buildMap(World world, int xOffset, int zOffset)
    {
    	Template template = EN2World.getOverworldTemplate();
    	
    	if (template!= null)
    	{
    		template.generate(world, xOffset, zOffset);
    	}
    	else
    	{
    		EN2.log.error("Failed to load map.");
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
	
	
}
