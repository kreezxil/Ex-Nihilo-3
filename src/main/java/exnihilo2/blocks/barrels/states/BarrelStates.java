package exnihilo2.blocks.barrels.states;

import java.util.HashMap;

import net.minecraftforge.common.config.Configuration;
import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.states.compost.BarrelStateCoarseDirt;
import exnihilo2.blocks.barrels.states.compost.BarrelStateCompost;
import exnihilo2.blocks.barrels.states.compost.BarrelStateGrass;
import exnihilo2.blocks.barrels.states.compost.BarrelStateMycelium;
import exnihilo2.blocks.barrels.states.compost.BarrelStatePodzol;
import exnihilo2.blocks.barrels.states.compost.logic.CoarseDirtStateLogicComplete;
import exnihilo2.blocks.barrels.states.compost.logic.CoarseDirtStateTrigger;
import exnihilo2.blocks.barrels.states.compost.logic.CompostStateLogicComplete;
import exnihilo2.blocks.barrels.states.compost.logic.CompostStateLogicItems;
import exnihilo2.blocks.barrels.states.compost.logic.GrassStateLogicComplete;
import exnihilo2.blocks.barrels.states.compost.logic.GrassStateTrigger;
import exnihilo2.blocks.barrels.states.compost.logic.MyceliumStateLogicComplete;
import exnihilo2.blocks.barrels.states.compost.logic.MyceliumStateTrigger;
import exnihilo2.blocks.barrels.states.compost.logic.PodzolStateLogicComplete;
import exnihilo2.blocks.barrels.states.compost.logic.PodzolStateTrigger;
import exnihilo2.blocks.barrels.states.empty.BarrelStateEmpty;
import exnihilo2.blocks.barrels.states.empty.logic.CompostStateTrigger;
import exnihilo2.blocks.barrels.states.empty.logic.EmptyStateLogic;
import exnihilo2.blocks.barrels.states.empty.logic.FluidStateTriggerItem;
import exnihilo2.blocks.barrels.states.empty.logic.FluidStateTriggerWeather;
import exnihilo2.blocks.barrels.states.fluid.BarrelStateFluid;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidCraftClayTrigger;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidCraftEndstoneTrigger;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidCraftNetherrackTrigger;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidCraftObsidianTrigger;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidCraftStoneTrigger;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicGas;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicHot;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicItems;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicRain;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidSummonSlimeTrigger;
import exnihilo2.blocks.barrels.states.output.BarrelStateOutput;
import exnihilo2.blocks.barrels.states.output.logic.OutputStateLogicGrowingGrass;
import exnihilo2.blocks.barrels.states.output.logic.OutputStateLogicGrowingMycelium;
import exnihilo2.blocks.barrels.states.slime.BarrelStateSlime;
import exnihilo2.blocks.barrels.states.slime.logic.SlimeStateLogic;

public class BarrelStates {
	public static HashMap<String, BarrelState> states = new HashMap<String, BarrelState>();
	
	//States
	public static BarrelState empty;
	public static BarrelState output;
	public static BarrelState fluid;
	public static BarrelState compost;
	public static BarrelState podzol;
	public static BarrelState mycelium;
	public static BarrelState grass;
	public static BarrelState coarse_dirt;
	public static BarrelState slime_green;
	
	//Logic
	//-empty
	public static BarrelLogic empty_state_logic;
	public static BarrelLogic empty_state_trigger_compost_item;
	public static BarrelLogic empty_state_trigger_fluid_item;
	public static BarrelLogic empty_state_trigger_fluid_weather;
	
	//-output
	public static BarrelLogic output_state_logic_growing_grass;
	public static BarrelLogic output_state_logic_growing_mycelium;
	
	//-fluid
	public static BarrelLogic fluid_state_logic_hot;
	public static BarrelLogic fluid_state_logic_weather;
	public static BarrelLogic fluid_state_logic_gas;
	public static BarrelLogic fluid_state_logic_items;
	public static BarrelLogic fluid_state_trigger_crafting_netherrack;
	public static BarrelLogic fluid_state_trigger_crafting_end_stone;
	public static BarrelLogic fluid_state_trigger_crafting_clay;
	public static BarrelLogic fluid_state_trigger_crafting_obsidian;
	public static BarrelLogic fluid_state_trigger_crafting_stone;
	public static BarrelLogic fluid_state_trigger_summon_slime;
	
	//-compost
	public static BarrelLogic compost_state_logic_items;
	public static BarrelLogic compost_state_logic_update;
	public static BarrelLogic compost_state_trigger_complete;
	public static BarrelLogic compost_state_trigger_podzol;
	public static BarrelLogic compost_state_trigger_mycelium;
	public static BarrelLogic compost_state_trigger_grass;
	public static BarrelLogic compost_state_trigger_coarse_dirt;
	public static BarrelLogic podzol_state_trigger_complete;
	public static BarrelLogic mycelium_state_trigger_complete;
	public static BarrelLogic grass_state_trigger_complete;
	public static BarrelLogic coarse_dirt_state_trigger_complete;
	
	//slime
	public static BarrelLogic slime_state_logic;
	
	private static final String CATEGORY_BARREL_OPTIONS = "barrel options";
	public static boolean allow_compost;
	public static boolean allow_rain_filling;
	public static boolean allow_crafting_netherrack;
	public static boolean allow_crafting_end_stone;
	public static boolean allow_crafting_clay;
	public static boolean allow_crafting_obsidian;
	public static boolean allow_crafting_stone;
	public static boolean allow_slime_summoning;
	
	public static void configure(Configuration config)
	{
		loadSettings(config);
		
		initializeLogic();
		initializeStates();
		
		buildBehaviorTree();
	}
	
	private static void loadSettings(Configuration config)
	{
		allow_compost = config.get(CATEGORY_BARREL_OPTIONS, "allow composting", true).getBoolean(true);
		allow_rain_filling = config.get(CATEGORY_BARREL_OPTIONS, "allow rain to fill barrels", true).getBoolean(true);
		allow_crafting_netherrack = config.get(CATEGORY_BARREL_OPTIONS, "allow creating netherrack", true).getBoolean(true);
		allow_crafting_end_stone = config.get(CATEGORY_BARREL_OPTIONS, "allow creating end stone", true).getBoolean(true);
		allow_crafting_clay = config.get(CATEGORY_BARREL_OPTIONS, "allow creating clay", true).getBoolean(true);
		allow_crafting_obsidian = config.get(CATEGORY_BARREL_OPTIONS, "allow creating obsidian", true).getBoolean(true);
		allow_crafting_stone = config.get(CATEGORY_BARREL_OPTIONS, "allow creating stone and cobblestone", true).getBoolean(true);
		allow_slime_summoning = config.get(CATEGORY_BARREL_OPTIONS, "allow creating slimes", true).getBoolean(true);
	}
	
	private static void initializeLogic()
	{
		empty_state_logic = new EmptyStateLogic();
		empty_state_trigger_compost_item = new CompostStateTrigger();
		empty_state_trigger_fluid_item = new FluidStateTriggerItem();
		empty_state_trigger_fluid_weather = new FluidStateTriggerWeather();

		output_state_logic_growing_grass = new OutputStateLogicGrowingGrass();
		output_state_logic_growing_mycelium = new OutputStateLogicGrowingMycelium();

		fluid_state_logic_hot = new FluidStateLogicHot();
		fluid_state_logic_weather = new FluidStateLogicRain();
		fluid_state_logic_gas = new FluidStateLogicGas();
		fluid_state_logic_items = new FluidStateLogicItems();
		fluid_state_trigger_crafting_netherrack = new FluidCraftNetherrackTrigger();
		fluid_state_trigger_crafting_end_stone = new FluidCraftEndstoneTrigger();
		fluid_state_trigger_crafting_clay = new FluidCraftClayTrigger();
		fluid_state_trigger_crafting_obsidian = new FluidCraftObsidianTrigger();
		fluid_state_trigger_crafting_stone = new FluidCraftStoneTrigger();
		fluid_state_trigger_summon_slime = new FluidSummonSlimeTrigger();

		compost_state_logic_items = new CompostStateLogicItems();
		compost_state_trigger_complete = new CompostStateLogicComplete();
		compost_state_trigger_podzol = new PodzolStateTrigger();
		compost_state_trigger_mycelium = new MyceliumStateTrigger();
		compost_state_trigger_grass = new GrassStateTrigger();
		compost_state_trigger_coarse_dirt = new CoarseDirtStateTrigger();
		podzol_state_trigger_complete = new PodzolStateLogicComplete();
		mycelium_state_trigger_complete = new MyceliumStateLogicComplete();
		grass_state_trigger_complete = new GrassStateLogicComplete();
		coarse_dirt_state_trigger_complete = new CoarseDirtStateLogicComplete();
		
		slime_state_logic = new SlimeStateLogic();
	}
	
	private static void initializeStates()
	{
		empty = new BarrelStateEmpty();
		output = new BarrelStateOutput();
		fluid = new BarrelStateFluid();
		compost = new BarrelStateCompost();
		podzol = new BarrelStatePodzol();
		mycelium = new BarrelStateMycelium();
		grass = new BarrelStateGrass();
		coarse_dirt = new BarrelStateCoarseDirt();
		slime_green = new BarrelStateSlime();
		
		BarrelStates.registerState(empty);
		BarrelStates.registerState(output);
		BarrelStates.registerState(fluid);
		BarrelStates.registerState(compost);
		BarrelStates.registerState(podzol);
		BarrelStates.registerState(mycelium);
		BarrelStates.registerState(grass);
		BarrelStates.registerState(coarse_dirt);
		BarrelStates.registerState(slime_green);
	}
	
	private static void buildBehaviorTree()
	{
		BarrelStates.empty.addLogic(empty_state_logic);
		
		if (allow_compost)
			BarrelStates.empty.addLogic(empty_state_trigger_compost_item);
		BarrelStates.empty.addLogic(empty_state_trigger_fluid_item);
		if (allow_rain_filling)
			BarrelStates.empty.addLogic(empty_state_trigger_fluid_weather);

		BarrelStates.output.addLogic(output_state_logic_growing_grass);
		BarrelStates.output.addLogic(output_state_logic_growing_mycelium);

		BarrelStates.fluid.addLogic(fluid_state_logic_hot);
		if (allow_rain_filling)
			BarrelStates.fluid.addLogic(fluid_state_logic_weather);
		BarrelStates.fluid.addLogic(fluid_state_logic_gas);
		BarrelStates.fluid.addLogic(fluid_state_logic_items);
		if (allow_crafting_netherrack)
			BarrelStates.fluid.addLogic(fluid_state_trigger_crafting_netherrack);
		if (allow_crafting_end_stone)
			BarrelStates.fluid.addLogic(fluid_state_trigger_crafting_end_stone);
		if (allow_crafting_clay)
			BarrelStates.fluid.addLogic(fluid_state_trigger_crafting_clay);
		if (allow_crafting_obsidian)
			BarrelStates.fluid.addLogic(fluid_state_trigger_crafting_obsidian);
		if (allow_crafting_stone)
			BarrelStates.fluid.addLogic(fluid_state_trigger_crafting_stone);
		if (allow_slime_summoning)
			BarrelStates.fluid.addLogic(fluid_state_trigger_summon_slime);

		if (allow_compost)
		{
			BarrelStates.compost.addLogic(compost_state_logic_items);
			BarrelStates.compost.addLogic(compost_state_trigger_complete);
			BarrelStates.compost.addLogic(compost_state_trigger_podzol);
			BarrelStates.compost.addLogic(compost_state_trigger_mycelium);
			BarrelStates.compost.addLogic(compost_state_trigger_grass);
			BarrelStates.compost.addLogic(compost_state_trigger_coarse_dirt);

			BarrelStates.podzol.addLogic(compost_state_logic_items);
			BarrelStates.podzol.addLogic(compost_state_trigger_coarse_dirt);
			BarrelStates.podzol.addLogic(compost_state_trigger_mycelium);
			BarrelStates.podzol.addLogic(podzol_state_trigger_complete);

			BarrelStates.mycelium.addLogic(compost_state_logic_items);
			BarrelStates.mycelium.addLogic(compost_state_trigger_grass);
			BarrelStates.mycelium.addLogic(compost_state_trigger_coarse_dirt);
			BarrelStates.mycelium.addLogic(mycelium_state_trigger_complete);

			BarrelStates.grass.addLogic(compost_state_logic_items);
			BarrelStates.grass.addLogic(compost_state_trigger_coarse_dirt);
			BarrelStates.grass.addLogic(grass_state_trigger_complete);

			BarrelStates.coarse_dirt.addLogic(compost_state_logic_items);
			BarrelStates.coarse_dirt.addLogic(coarse_dirt_state_trigger_complete);
		}
		
		BarrelStates.slime_green.addLogic(slime_state_logic);
	}
	
	public static void registerState(BarrelState state)
	{
		if (state != null)
		{
			String key = state.getUniqueIdentifier();
			
			if (key != null && !key.isEmpty() && !key.trim().isEmpty() && !states.containsKey(key))
			{
				states.put(key, state);
			}
		}
	}
	
	public static void unregisterState(BarrelState state)
	{
		states.remove(state.getUniqueIdentifier());
	}
	
	public static BarrelState getState(String key)
	{
		return states.get(key);
	}
}
