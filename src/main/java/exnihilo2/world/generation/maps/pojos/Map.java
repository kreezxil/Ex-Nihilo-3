package exnihilo2.world.generation.maps.pojos;

import java.util.ArrayList;

public class Map {
	private ArrayList<MapBlock>blocks = new ArrayList<MapBlock>();

	public ArrayList<MapBlock> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<MapBlock> blocks) {
		this.blocks = blocks;
	}
}
