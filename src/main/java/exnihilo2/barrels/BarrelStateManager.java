package exnihilo2.barrels;

import java.util.HashMap;

import exnihilo2.barrels.states.BarrelStateBase;
import exnihilo2.barrels.states.empty.BarrelStateEmpty;
import exnihilo2.barrels.states.empty.logic.BarrelStateFluidTrigger;
import exnihilo2.barrels.states.fluid.BarrelStateFluid;

public class BarrelStateManager {
	public static HashMap<String, BarrelStateBase> states = new HashMap<String, BarrelStateBase>();
	
	public static void registerState(String key, BarrelStateBase state)
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
	
	public static BarrelStateBase getState(String key)
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
		getState("empty").registerStateTrigger("empty->fluid", new BarrelStateFluidTrigger());
	}
}
