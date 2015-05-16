package exnihilo2.registries.composting.pojos;

import exnihilo2.util.enums.MetadataBehavior;

public class CompostRecipe {
	private String id;
	private int meta;
	private MetadataBehavior behavior = MetadataBehavior.IGNORED;
	private int value;
	private String color;
	
	public CompostRecipe() {}
	
	public CompostRecipe(String id, int meta, MetadataBehavior behavior, int value, String color)
	{
		this.id = id;
		this.meta = meta;
		this.behavior = behavior;
		this.value = value;
		this.color = color;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMeta() {
		return meta;
	}
	public void setMeta(int meta) {
		this.meta = meta;
	}
	public MetadataBehavior getBehavior() {
		return behavior;
	}
	public void setBehavior(MetadataBehavior behavior) {
		this.behavior = behavior;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
