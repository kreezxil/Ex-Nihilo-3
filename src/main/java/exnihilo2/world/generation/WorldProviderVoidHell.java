package exnihilo2.world.generation;

import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderVoidHell extends WorldProviderHell{

	@Override
    public IChunkProvider createChunkGenerator()
    {
		ChunkProviderVoidHell provider = new ChunkProviderVoidHell(this.worldObj, false, worldObj.getSeed());
		
        return provider;
    }
}
