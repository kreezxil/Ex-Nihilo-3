package exnihilo2.util.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumOre implements IStringSerializable{
	IRON(0, "iron"),
	GOLD(1, "gold"),
	TIN(2, "tin"),
	COPPER(3, "copper"),
	LEAD(4, "lead"),
	SILVER(5, "silver"),
	NICKEL(6, "nickel"),
	PLATINUM(7, "platinum");

	private final int meta;
	private final String name;

	private static final EnumOre[] META_LOOKUP = new EnumOre[values().length];

	private EnumOre(int meta, String name)
	{
		this.meta = meta;
		this.name = name;
	}

	public int getMetadata()
	{
		return this.meta;
	}

	public static EnumOre fromMetadata(int meta)
	{
		if (meta < 0 || meta >= META_LOOKUP.length)
		{
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	static
	{
		EnumOre[] ores = values();

		for (int x = 0; x < ores.length; ++x)
		{
			EnumOre ore = ores[x];
			META_LOOKUP[ore.getMetadata()] = ore;
		}
	}

	@Override
	public String getName() {
		return this.name;
	}
}
