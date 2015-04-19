package exnihilo2.barrels;

import java.util.HashMap;

import exnihilo2.barrels.design.bases.BarrelState;
import exnihilo2.barrels.states.empty.BarrelStateEmpty;
import exnihilo2.barrels.states.empty.logic.FluidStateItemTrigger;
import exnihilo2.barrels.states.empty.logic.FluidStateWeatherTrigger;
import exnihilo2.barrels.states.fluid.BarrelStateFluid;

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
		registerTriggers();
	}
	
	private static void registerStates()
	{
		registerState("empty", new BarrelStateEmpty());
		registerState("fluid", new BarrelStateFluid());
	}
	
	private static void registerTriggers()
	{
		getState("empty").registerStateTrigger("empty->fluid(item)", new FluidStateItemTrigger());
		getState("empty").registerStateTrigger("empty->fluid(weather)", new FluidStateWeatherTrigger());
	}
}
