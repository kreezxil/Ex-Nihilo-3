package exnihilo2.blocks.fluids.base;

import net.minecraftforge.common.property.IUnlistedProperty;

public class PropertyFloat implements IUnlistedProperty<Float>{
	private String name;
	
	public PropertyFloat(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName() 
	{
		return name;
	}
	@Override
	public boolean isValid(Float value) 
	{
		return true;
	}
	@Override
	public Class<Float> getType() 
	{
		return Float.class;
	}
	@Override
	public String valueToString(Float value) 
	{
		return value.toString();
	}
}
