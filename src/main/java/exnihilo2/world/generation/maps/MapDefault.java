package exnihilo2.world.generation.maps;

import java.util.ArrayList;

import exnihilo2.EN2;
import exnihilo2.world.generation.maps.pojos.Map;
import exnihilo2.world.generation.maps.pojos.MapBlock;

public class MapDefault {
	public static Map generate()
	{
		EN2.log.error("Generating default map!");
		
		Map map = new Map();
		
		ArrayList<MapBlock> blocks = map.getBlocks();
		//layer 0
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 63, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 63, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 63, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 63, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 63, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 63, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 63, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 63, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 63, 1));
		
		//layer 1
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 62, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 62, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 62, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 62, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 62, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 62, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 62, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 62, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 62, 1));
		
		//layer 2
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 61, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 61, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, 61, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 61, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 61, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, 61, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 61, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 61, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, 61, 1));
		
		return map;
	}
}
