package exnihilo2.world.generation.maps.pojos;

import java.util.ArrayList;

public class Map {
	private int spawnYLevel = 64;
	private ArrayList<MapBlock>blocks = new ArrayList<MapBlock>();

	public ArrayList<MapBlock> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<MapBlock> blocks) {
		this.blocks = blocks;
	}

	public int getSpawnYLevel() {
		return spawnYLevel;
	}

	public void setSpawnYLevel(int spawnYLevel) {
		this.spawnYLevel = spawnYLevel;
	}
}
