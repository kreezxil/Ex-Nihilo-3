package exnihilo2.world.generation.maps;

import java.util.ArrayList;

import exnihilo2.EN2;
import exnihilo2.world.generation.maps.pojos.Map;
import exnihilo2.world.generation.maps.pojos.MapBlock;

public class MapDefault {
	public static Map generate()
	{
		//TODO add chest and stuff.
		Map map = new Map();
		
		ArrayList<MapBlock> blocks = map.getBlocks();
		//layer 0
		blocks.add(new MapBlock("minecraft:grass", 0, -1, 0, -1));
		blocks.add(new MapBlock("minecraft:grass", 0, -1, 0, 0));
		blocks.add(new MapBlock("minecraft:grass", 0, -1, 0, 1));
		
		blocks.add(new MapBlock("minecraft:grass", 0, 0, 0, -1));
		blocks.add(new MapBlock("minecraft:grass", 0, 0, 0, 0));
		blocks.add(new MapBlock("minecraft:grass", 0, 0, 0, 1));
		
		blocks.add(new MapBlock("minecraft:grass", 0, 1, 0, -1));
		blocks.add(new MapBlock("minecraft:grass", 0, 1, 0, 0));
		blocks.add(new MapBlock("minecraft:grass", 0, 1, 0, 1));
		
		//layer 1
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, -1, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, -1, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, -1, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, -1, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, -1, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, -1, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, -1, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, -1, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, -1, 1));
		
		//layer 2
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, -2, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, -2, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, -1, -2, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, -2, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, -2, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 0, -2, 1));
		
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, -2, -1));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, -2, 0));
		blocks.add(new MapBlock("minecraft:dirt", 0, 1, -2, 1));
		
		return map;
	}
}
