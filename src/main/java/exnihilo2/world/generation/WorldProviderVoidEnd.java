package exnihilo2.world.generation;

import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderVoidEnd extends WorldProviderEnd{
	
	@Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderVoidEnd(worldObj, worldObj.getSeed());
    }
}
