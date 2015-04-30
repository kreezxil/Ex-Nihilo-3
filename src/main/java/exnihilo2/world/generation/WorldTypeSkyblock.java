package exnihilo2.world.generation;

import net.minecraft.world.WorldType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldTypeSkyblock extends WorldType{
	
	public WorldTypeSkyblock() {
		super("skyblock");
	}
	
	@SideOnly(Side.CLIENT)
    public String getTranslateName()
    {
        return "Skyblock";
    }
	
	@Override
	public boolean isCustomizable()
    {
        return true;
    }

	@Override
	@SideOnly(Side.CLIENT)
    public boolean getCanBeCreated()
    {
        return true;
    }
}
