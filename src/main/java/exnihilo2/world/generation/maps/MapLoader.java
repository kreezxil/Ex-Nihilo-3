package exnihilo2.world.generation.maps;

import exnihilo2.EN2;
import exnihilo2.world.generation.maps.pojos.Map;

public class MapLoader {
	public static Map load(String json)
	{
		EN2.log.error("Loading map!");
		
		if (json == null || json.trim().length() == 0)
		{
			return MapDefault.generate();
		}
		
		//TODO Parse the json, build the map, return the map.
		return new Map();
	}
}
