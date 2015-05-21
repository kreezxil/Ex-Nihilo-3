package exnihilo2.registries.hammering;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.registries.composting.CompostRegistryEntry;
import exnihilo2.registries.composting.files.CompostRecipeLoader;
import exnihilo2.util.enums.EnumMetadataBehavior;

public class HammerRegistry {
	private static HashMap<String, HammerRegistryEntry> entries;

	public static void initialize()
	{
		entries = new HashMap<String, HammerRegistryEntry>();
		registerVanillaRecipes();
//		List<CompostRegistryEntry> loaded = CompostRecipeLoader.load(EN2.path + File.separator + "recipes" + File.separator + "compost" + File.separator);
//	
//		if (loaded != null && !loaded.isEmpty())
//		{
//			for (CompostRegistryEntry entry : loaded)
//			{
//				if (entry.getVolume() > 0)
//				{
//					add(entry);
//				}
//				else
//				{
//					remove(entry);
//				}
//			}
//		}
	}
	
	public static void add(HammerRegistryEntry entry)
	{
		if (entry != null)
		{
			entries.put(entry.getKey(), entry);
		}
	}
	
	public static void remove(HammerRegistryEntry entry)
	{
		entries.remove(entry.getKey());
	}
	
	public static boolean isHammerable(IBlockState state)
	{
		return getEntryForBlockState(state) != null;
	}
	
	public static HammerRegistryEntry getEntryForBlockState(IBlockState state)
	{
		HammerRegistryEntry entry = entries.get(GameRegistry.findUniqueIdentifierFor(state.getBlock()) + ":" + state.getBlock().getMetaFromState(state));
		
		if (entry != null)
		{
			return entry;
		}
		else
		{
			return entries.get(GameRegistry.findUniqueIdentifierFor(state.getBlock())  + ":*");
		}
	}
	
	public static void registerVanillaRecipes()
	{
		HammerRegistryEntry cobble = new HammerRegistryEntry(Blocks.cobblestone.getDefaultState(), EnumMetadataBehavior.SPECIFIC);
		cobble.addReward(new ItemStack(Blocks.gravel), 100, 0);
		add(cobble);
		
		HammerRegistryEntry gravel = new HammerRegistryEntry(Blocks.gravel.getDefaultState(), EnumMetadataBehavior.SPECIFIC);
		gravel.addReward(new ItemStack(Blocks.sand), 100, 0);
		add(gravel);
		
		HammerRegistryEntry sand = new HammerRegistryEntry(Blocks.sand.getDefaultState(), EnumMetadataBehavior.SPECIFIC);
		sand.addReward(new ItemStack(EN2Blocks.dust), 100, 0);
		add(sand);
	}
}
