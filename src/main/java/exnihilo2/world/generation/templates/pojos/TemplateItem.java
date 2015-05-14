package exnihilo2.world.generation.templates.pojos;

public class TemplateItem {
	private String id;
	private int meta;
	private int count;
	
	public TemplateItem(){};
	public TemplateItem(String idIn, int countIn, int metaIn)
	{
		this.id = idIn;
		this.count = countIn;
		this.meta = metaIn;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
