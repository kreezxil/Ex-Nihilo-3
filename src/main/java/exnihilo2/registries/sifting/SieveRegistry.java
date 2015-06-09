package exnihilo2.registries.sifting;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import exnihilo2.EN2;
import exnihilo2.blocks.EN2Blocks;
import exnihilo2.items.EN2Items;
import exnihilo2.registries.EN2Registries;
import exnihilo2.registries.hammering.HammerRegistryEntry;
import exnihilo2.registries.hammering.files.HammerRecipeLoader;
import exnihilo2.registries.sifting.files.SieveRecipeLoader;
import exnihilo2.util.enums.EnumMetadataBehavior;

public class SieveRegistry {
	private static HashMap<String, SieveRegistryEntry> entries;

	public static void initialize()
	{
		entries = new HashMap<String, SieveRegistryEntry>();
		
		if (EN2Registries.loadSieveDefaults)
			registerVanillaRecipes();
		
		List<SieveRegistryEntry> loaded = SieveRecipeLoader.load(EN2.path + File.separator + "recipes" + File.separator + "sieve" + File.separator);
	
		if (loaded != null && !loaded.isEmpty())
		{
			for (SieveRegistryEntry entry : loaded)
			{
				if (entry.getRewards().size() > 0)
				{
					add(entry);
				}
				else
				{
					remove(entry);
				}
			}
		}
	}
	
	public static HashMap<String, SieveRegistryEntry> getEntryMap()
	{
		return entries;
	}
	
	public static void add(SieveRegistryEntry entry)
	{
		if (entry != null)
		{
			entries.put(entry.getKey(), entry);
		}
	}
	
	public static void remove(SieveRegistryEntry entry)
	{
		entries.remove(entry.getKey());
	}
	
	public static boolean isSiftable(IBlockState state)
	{
		return getEntryForBlockState(state) != null;
	}
	
	public static SieveRegistryEntry getEntryForBlockState(IBlockState state)
	{
		SieveRegistryEntry entry = entries.get(GameRegistry.findUniqueIdentifierFor(state.getBlock()) + ":" + state.getBlock().getMetaFromState(state));
		
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
		SieveRegistryEntry dirt = new SieveRegistryEntry(Blocks.dirt.getDefaultState(), EnumMetadataBehavior.IGNORED);
		dirt.addReward(new ItemStack(EN2Items.stone, 1), 100);
		dirt.addReward(new ItemStack(EN2Items.stone, 1), 75);
		dirt.addReward(new ItemStack(EN2Items.stone, 1), 50);
		add(dirt);
		
		SieveRegistryEntry gravel = new SieveRegistryEntry(Blocks.gravel.getDefaultState(), EnumMetadataBehavior.IGNORED);
		gravel.addReward(new ItemStack(Items.flint, 1), 100);
		gravel.addReward(new ItemStack(EN2Items.rust, 1), 15);
		add(gravel);
		
		SieveRegistryEntry soulsand = new SieveRegistryEntry(Blocks.soul_sand.getDefaultState(), EnumMetadataBehavior.IGNORED);
		soulsand.addReward(new ItemStack(Items.quartz, 1), 100);
		add(soulsand);
	}
}
