package exnihilo2.blocks.barrels;

import java.util.HashMap;

import exnihilo2.blocks.barrels.architecture.BarrelState;
import exnihilo2.blocks.barrels.states.compost.BarrelStateCompost;
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

public class BarrelStateManager {
	public static HashMap<String, BarrelState> states = new HashMap<String, BarrelState>();
	
	public static void registerState(String key, BarrelState state)
	{
		if (state != null)
		{
			if (key != null && !key.isEmpty() && !key.trim().isEmpty() && !states.containsKey(key))
			{
				state.setKey(key);
				states.put(key, state);
			}
		}
	}
	
	public static void unregisterState(String key)
	{
		if (states.containsKey(key))
		{
			states.remove(key);
		}
	}
	
	public static BarrelState getState(String key)
	{
		return states.get(key);
	}
	
	public static void buildBehaviorTree()
	{
		registerStates();
		registerLogic();
	}
	
	private static void registerStates()
	{
		registerState("empty", new BarrelStateEmpty());
		registerState("fluid", new BarrelStateFluid());
		registerState("simple", new BarrelStateSimple());
		registerState("compost", new BarrelStateCompost());
	}
	
	private static void registerLogic()
	{
		getState("empty").registerStateLogic("empty to compost (item)", new CompostStateTrigger());
		getState("empty").registerStateLogic("empty to fluid (item)", new FluidStateTriggerItem());
		getState("empty").registerStateLogic("empty to fluid (weather)", new FluidStateTriggerWeather());
		
		getState("fluid").registerStateLogic("fluid logic (gas)", new FluidStateLogicHot());
		getState("fluid").registerStateLogic("fluid logic (hot)", new FluidStateLogicGas());
		getState("fluid").registerStateLogic("fluid logic (rain)", new FluidStateLogicRain());
		getState("fluid").registerStateLogic("fluid logic (luminosity)", new FluidStateLogicLuminosity());
		getState("fluid").registerStateLogic("fluid logic (items)", new FluidStateLogicItems());
		getState("fluid").registerStateLogic("barrel crafting (netherrack)", new FluidCraftNetherrackTrigger());
	}
}
