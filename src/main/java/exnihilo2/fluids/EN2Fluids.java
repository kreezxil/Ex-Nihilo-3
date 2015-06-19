package exnihilo2.fluids;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class EN2Fluids {
	public static Fluid witchwater;
	public static Fluid azoth;
	
	public static void register()
	{
		witchwater = new Fluid("witchwater");
		FluidRegistry.registerFluid(witchwater);
		
		azoth = new Fluid("azoth");
		FluidRegistry.registerFluid(azoth);
	}
}
