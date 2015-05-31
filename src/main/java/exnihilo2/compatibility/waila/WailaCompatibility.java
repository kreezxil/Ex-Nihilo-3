package exnihilo2.compatibility.waila;

import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.barrels.BlockBarrel;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.furnaces.BlockFurnaceDirt;
import exnihilo2.blocks.sieves.BlockSieve;
import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.Waila;
import mcp.mobius.waila.api.ITaggedList.ITipList;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataAccessorServer;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaCompatibility implements IWailaDataProvider{
	private static ItemStack furnace_dirt_stack = new ItemStack(EN2Blocks.furnace_dirt);
	
	public static void initialize()
	{
		FMLInterModComms.sendMessage("Waila", "register", "exnihilo2.compatibility.waila.WailaCompatibility.register");
	}
	
	public static void register(IWailaRegistrar registrar)
	{
		WailaCompatibility instance = new WailaCompatibility();
		registrar.registerStackProvider(instance, BlockFurnaceDirt.class);
		registrar.registerBodyProvider(instance, BlockSieve.class);
		registrar.registerBodyProvider(instance, BlockBarrel.class);
	}
	
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		if (accessor.getBlock().equals(EN2Blocks.furnace_dirt))
		{
			return furnace_dirt_stack;
		}
		
		return null;
	}

	@Override
	public ITipList getWailaHead(ItemStack itemStack, ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		return null;
	}

	@Override
	public ITipList getWailaBody(ItemStack itemStack, ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		if (accessor.getBlock() instanceof BlockSieve) 
		{
			TileEntitySieve sieve = (TileEntitySieve) accessor.getTileEntity();
			addSieveBody(sieve, currenttip);
		}
		else if (accessor.getBlock() instanceof BlockBarrel) 
		{
			TileEntityBarrel barrel = (TileEntityBarrel) accessor.getTileEntity();
			addBarrelBody(barrel, currenttip);
		}
		
		return currenttip;
	}

	@Override
	public ITipList getWailaTail(ItemStack itemStack, ITipList currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		return null;
	}

	@Override
	public NBTTagCompound getNBTData(TileEntity te, NBTTagCompound tag, IWailaDataAccessorServer accessor) {
		
		return null;
	}
	
	public void addSieveBody(TileEntitySieve sieve, ITipList tip)
	{
		if (!sieve.hasMesh())
		{
			tip.add("No Mesh");
		}
		else
		{
			tip.add(sieve.getMesh().getDisplayName());
			
			if (!sieve.canWork())
			{
				tip.add("Empty");
			}
			else
			{
				tip.add("Processing " + sieve.getContents().getDisplayName() + ": " + format(sieve.getProgress() * 100) + "%");
			}
		}
	}
	
	public void addBarrelBody(TileEntityBarrel barrel, ITipList tip)
	{
		String[] body = barrel.getState().getWailaBody(barrel);
		
		if (body != null)
		{
			for (String s : body)
			{
				tip.add(s);
			}
		}
	}
	
	public String format(float input)
	{
		return String.format("%.0f", input);
	}
}
