package exnihilo2.compatibility.waila;

import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.furnaces.BlockFurnaceDirt;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import mcp.mobius.waila.Waila;
import mcp.mobius.waila.api.ITaggedList.ITipList;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataAccessorServer;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaProvider implements IWailaDataProvider{
	public ItemStack furnace_dirt_stack = new ItemStack(EN2Blocks.furnace_dirt);
	
	public static void register(IWailaRegistrar registrar)
	{
		WailaProvider instance = new WailaProvider();
		registrar.registerStackProvider(instance, BlockFurnaceDirt.class);
	}
	
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		if (accessor.getBlock().equals(EN2Blocks.furnace_dirt))
		{
			return furnace_dirt_stack;
		}
		
		return null;
	}

	@Override
	public ITipList getWailaHead(ItemStack itemStack, ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		
		return null;
	}

	@Override
	public ITipList getWailaBody(ItemStack itemStack, ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		
		return null;
	}

	@Override
	public ITipList getWailaTail(ItemStack itemStack, ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		
		return null;
	}

	@Override
	public NBTTagCompound getNBTData(TileEntity te, NBTTagCompound tag, IWailaDataAccessorServer accessor) {
		
		return null;
	}
}
