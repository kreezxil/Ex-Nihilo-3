package exnihilo2.barrels;

import java.util.HashMap;

import exnihilo2.barrels.bases.BaseBarrelState;
import exnihilo2.barrels.states.BarrelStateEmpty;
import exnihilo2.barrels.states.BarrelStateFluid;
import exnihilo2.barrels.states.BarrelStateFluidTrigger;

public class Barrels {
	public static HashMap<String, BaseBarrelState> states = new HashMap<String, BaseBarrelState>();
	
	public static void registerState(BaseBarrelState state)
	{
		if (state != null)
		{
			String key = state.getBarrelStateKey();
			
			if (key != null && !key.isEmpty() && !key.trim().isEmpty() && !states.containsKey(key))
			{
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
		registerNodes();
		registerTriggers();
	}
	
	private static void registerNodes()
	{
		registerState(new BarrelStateEmpty());
		registerState(new BarrelStateFluid());
	}
	
	private static void registerTriggers()
	{
		getState("empty").registerStateTrigger(new BarrelStateFluidTrigger());
	}
}
