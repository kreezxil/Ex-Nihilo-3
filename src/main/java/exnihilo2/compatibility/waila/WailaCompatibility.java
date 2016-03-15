package exnihilo2.compatibility.waila;

import java.util.List;

import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.blocks.barrels.BlockBarrel;
import exnihilo2.blocks.barrels.tileentity.TileEntityBarrel;
import exnihilo2.blocks.crucibles.BlockCrucible;
import exnihilo2.blocks.crucibles.tileentity.TileEntityCrucible;
import exnihilo2.blocks.furnaces.BlockFurnaceDirt;
import exnihilo2.blocks.sieves.BlockSieve;
import exnihilo2.blocks.sieves.tileentity.TileEntitySieve;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.Waila;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
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
		registrar.registerBodyProvider(instance, BlockCrucible.class);
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
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
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
		else if(accessor.getBlock() instanceof BlockCrucible)
		{
			TileEntityCrucible crucible = (TileEntityCrucible) accessor.getTileEntity();
			addCrucibleBody(crucible, currenttip);
		}
		
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		return null;
	}
	
	public void addSieveBody(TileEntitySieve sieve, List<String> tip)
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
	
	public void addBarrelBody(TileEntityBarrel barrel, List<String> tip)
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
	
	public void addCrucibleBody(TileEntityCrucible crucible, List<String> tip)
	{
		FluidStack fluid = crucible.getCurrentFluid();
		
		if (fluid != null)
		{
			tip.add(fluid.getLocalizedName() + " " + fluid.amount + " mb");
		}
		
		tip.add("Speed " + crucible.getMeltingSpeed());
	}
	
	public String format(float input)
	{
		return String.format("%.0f", input);
	}

  @Override
  public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag,
                                   World world, BlockPos pos)
  {
    // TODO Auto-generated method stub
    return null;
  }
}
