package exnihilo2.registries.sifting;

import java.io.File;
import java.util.ArrayList;
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
	private static List<ItemStack> EMPTY_REWARDS_ARRAY = new ArrayList<ItemStack>(){};

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
		return getEntryForBlockState(state, EnumMetadataBehavior.SPECIFIC) != null || getEntryForBlockState(state, EnumMetadataBehavior.IGNORED) != null;
	}
	
	public static List<ItemStack> generateRewards(IBlockState state)
	{
		if (state == null || state.getBlock().equals(Blocks.air))
		{
			return EMPTY_REWARDS_ARRAY;
		}
		else
		{
			List<ItemStack> rewards = new ArrayList<ItemStack>();
			
			SieveRegistryEntry specific = getEntryForBlockState(state, EnumMetadataBehavior.SPECIFIC);
			SieveRegistryEntry generic = getEntryForBlockState(state, EnumMetadataBehavior.IGNORED);
			
			if (specific == null && generic == null)
				return EMPTY_REWARDS_ARRAY;
			
			if (specific != null)
			{
				for (SieveReward r : specific.getRewards())
				{
					ItemStack i = r.generateReward();
					
					if (i != null)
						rewards.add(i);
				}
			}
				
			if (generic != null)
			{
				for (SieveReward r : generic.getRewards())
				{
					ItemStack i = r.generateReward();
					
					if (i != null)
						rewards.add(i);
				}
			}
			
			return rewards;
		}
	}
	
	public static SieveRegistryEntry getEntryForBlockState(IBlockState state, EnumMetadataBehavior behavior)
	{
		if (behavior == EnumMetadataBehavior.SPECIFIC)
		{
			return entries.get(GameRegistry.findUniqueIdentifierFor(state.getBlock()) + ":" + state.getBlock().getMetaFromState(state));
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
		
		SieveRegistryEntry leaves = new SieveRegistryEntry(Blocks.leaves.getDefaultState(), EnumMetadataBehavior.IGNORED);
		leaves.addReward(new ItemStack(Blocks.sapling, 1, 0), 1);
		leaves.addReward(new ItemStack(Blocks.sapling, 1, 1), 1);
		leaves.addReward(new ItemStack(Blocks.sapling, 1, 2), 1);
		leaves.addReward(new ItemStack(Blocks.sapling, 1, 3), 1);
		leaves.addReward(new ItemStack(Blocks.sapling, 1, 4), 1);
		leaves.addReward(new ItemStack(Blocks.sapling, 1, 5), 1);
		add(leaves);
		
		SieveRegistryEntry leaves2 = new SieveRegistryEntry(Blocks.leaves2.getDefaultState(), EnumMetadataBehavior.IGNORED);
		leaves2.addReward(new ItemStack(Blocks.sapling, 1, 0), 1);
		leaves2.addReward(new ItemStack(Blocks.sapling, 1, 1), 1);
		leaves2.addReward(new ItemStack(Blocks.sapling, 1, 2), 1);
		leaves2.addReward(new ItemStack(Blocks.sapling, 1, 3), 1);
		leaves2.addReward(new ItemStack(Blocks.sapling, 1, 4), 1);
		leaves2.addReward(new ItemStack(Blocks.sapling, 1, 5), 1);
		add(leaves2);
		
		SieveRegistryEntry oak = new SieveRegistryEntry(Blocks.leaves.getStateFromMeta(0), EnumMetadataBehavior.SPECIFIC);
		oak.addReward(new ItemStack(Blocks.sapling, 1, 0), 30);
		add(oak);
		
		SieveRegistryEntry birch = new SieveRegistryEntry(Blocks.leaves.getStateFromMeta(1), EnumMetadataBehavior.SPECIFIC);
		birch.addReward(new ItemStack(Blocks.sapling, 1, 1), 30);
		add(birch);
		
		SieveRegistryEntry spruce = new SieveRegistryEntry(Blocks.leaves.getStateFromMeta(2), EnumMetadataBehavior.SPECIFIC);
		spruce.addReward(new ItemStack(Blocks.sapling, 1, 2), 30);
		add(spruce);
		
		SieveRegistryEntry jungle = new SieveRegistryEntry(Blocks.leaves.getStateFromMeta(3), EnumMetadataBehavior.SPECIFIC);
		jungle.addReward(new ItemStack(Blocks.sapling, 1, 3), 30);
		add(jungle);
		
		SieveRegistryEntry acacia = new SieveRegistryEntry(Blocks.leaves2.getStateFromMeta(0), EnumMetadataBehavior.SPECIFIC);
		acacia.addReward(new ItemStack(Blocks.sapling, 1, 4), 30);
		add(acacia);
		
		SieveRegistryEntry darkoak = new SieveRegistryEntry(Blocks.leaves2.getStateFromMeta(1), EnumMetadataBehavior.SPECIFIC);
		darkoak.addReward(new ItemStack(Blocks.sapling, 1, 5), 30);
		add(darkoak);
		
		SieveRegistryEntry mycelium = new SieveRegistryEntry(Blocks.mycelium.getDefaultState(), EnumMetadataBehavior.IGNORED);
		mycelium.addReward(new ItemStack(EN2Items.spores, 1), 33);
		add(mycelium);
	}
}
