package exnihilo2.barrels;

import java.util.HashMap;

import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.states.BarrelStateEmpty;
import exnihilo2.barrels.states.BarrelStateFluid;
import exnihilo2.barrels.states.BarrelStateFluidTrigger;

public class BarrelStateManager {
	public static HashMap<String, BaseBarrelState> states = new HashMap<String, BaseBarrelState>();
	
	public static void registerState(String key, BaseBarrelState state)
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
	
	public static BaseBarrelState getState(String key)
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
