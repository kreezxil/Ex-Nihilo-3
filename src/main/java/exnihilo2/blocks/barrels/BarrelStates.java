package exnihilo2.blocks.barrels;

import java.util.HashMap;

import exnihilo2.blocks.barrels.architecture.BarrelLogic;
import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.states.compost.BarrelStateCompost;
import exnihilo2.blocks.barrels.states.compost.BarrelStatePodzol;
import exnihilo2.blocks.barrels.states.compost.logic.CompostStateLogicComplete;
import exnihilo2.blocks.barrels.states.compost.logic.CompostStateLogicItems;
import exnihilo2.blocks.barrels.states.compost.logic.PodzolStateLogicComplete;
import exnihilo2.blocks.barrels.states.compost.logic.PodzolStateTrigger;
import exnihilo2.blocks.barrels.states.empty.BarrelStateEmpty;
import exnihilo2.blocks.barrels.states.empty.logic.CompostStateTrigger;
import exnihilo2.blocks.barrels.states.empty.logic.FluidStateTriggerItem;
import exnihilo2.blocks.barrels.states.empty.logic.FluidStateTriggerWeather;
import exnihilo2.blocks.barrels.states.fluid.BarrelStateFluid;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidCraftNetherrackTrigger;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicGas;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicHot;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicItems;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicLuminosity;
import exnihilo2.blocks.barrels.states.fluid.logic.FluidStateLogicRain;
import exnihilo2.blocks.barrels.states.simple.BarrelStateSimple;

public class BarrelStates {
	public static HashMap<String, BarrelState> states = new HashMap<String, BarrelState>();
	
	//States
	public static BarrelState empty;
	public static BarrelState output;
	public static BarrelState fluid;
	public static BarrelState compost;
	public static BarrelState podzol;
	
	//Logic
	//-empty
	public static BarrelLogic empty_state_trigger_compost_item;
	public static BarrelLogic empty_state_trigger_fluid_item;
	public static BarrelLogic empty_state_trigger_fluid_weather;
	
	//-fluid
	public static BarrelLogic fluid_state_logic_hot;
	public static BarrelLogic fluid_state_logic_weather;
	public static BarrelLogic fluid_state_logic_gas;
	public static BarrelLogic fluid_state_logic_light;
	public static BarrelLogic fluid_state_logic_items;
	public static BarrelLogic fluid_state_trigger_crafting_netherrack;
	
	//-compost
	public static BarrelLogic compost_state_logic_items;
	public static BarrelLogic compost_state_trigger_complete;
	public static BarrelLogic compost_state_trigger_podzol;
	public static BarrelLogic podzol_state_trigger_complete;
	
	public static void initialize()
	{
		initializeLogic();
		initializeStates();
		registerLogic();
	}
	
	private static void initializeLogic()
	{
		empty_state_trigger_compost_item = new CompostStateTrigger();
		empty_state_trigger_fluid_item = new FluidStateTriggerItem();
		empty_state_trigger_fluid_weather = new FluidStateTriggerWeather();
		
		fluid_state_logic_hot = new FluidStateLogicHot();
		fluid_state_logic_weather = new FluidStateLogicRain();
		fluid_state_logic_gas = new FluidStateLogicGas();
		fluid_state_logic_light = new FluidStateLogicLuminosity();
		fluid_state_logic_items = new FluidStateLogicItems();
		fluid_state_trigger_crafting_netherrack = new FluidCraftNetherrackTrigger();
		
		compost_state_logic_items = new CompostStateLogicItems();
		compost_state_trigger_complete = new CompostStateLogicComplete();
		compost_state_trigger_podzol = new PodzolStateTrigger();
		podzol_state_trigger_complete = new PodzolStateLogicComplete();
	}
	
	private static void initializeStates()
	{
		empty = new BarrelStateEmpty();
		output = new BarrelStateSimple();
		fluid = new BarrelStateFluid();
		compost = new BarrelStateCompost();
		podzol = new BarrelStatePodzol();
	}
	
	private static void registerLogic()
	{
		BarrelStates.empty.addLogic(empty_state_trigger_compost_item);
		BarrelStates.empty.addLogic(empty_state_trigger_fluid_item);
		BarrelStates.empty.addLogic(empty_state_trigger_fluid_weather);
		
		BarrelStates.fluid.addLogic(fluid_state_logic_hot);
		BarrelStates.fluid.addLogic(fluid_state_logic_weather);
		BarrelStates.fluid.addLogic(fluid_state_logic_gas);
		BarrelStates.fluid.addLogic(fluid_state_logic_light);
		BarrelStates.fluid.addLogic(fluid_state_logic_items);
		BarrelStates.fluid.addLogic(fluid_state_trigger_crafting_netherrack);
		
		BarrelStates.compost.addLogic(compost_state_logic_items);
		BarrelStates.compost.addLogic(compost_state_trigger_complete);
		BarrelStates.compost.addLogic(compost_state_trigger_podzol);
		
		BarrelStates.podzol.addLogic(compost_state_logic_items);
		BarrelStates.podzol.addLogic(podzol_state_trigger_complete);
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
	
	public static void unregisterState(String key)
	{
		states.remove(key);
	}
	
	public static BarrelState getState(String key)
	{
		return states.get(key);
	}
	
	public static void buildBehaviorTree()
	{
		registerLogic();
	}
}
