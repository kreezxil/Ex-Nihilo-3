package exnihilo2.fluids;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class EN2Fluids {
	public static Fluid witchwater;
	public static Fluid azoth;
	
	public static ResourceLocation witchwater_still = new ResourceLocation("exnihilo2:blocks/witchwater_still");
	public static ResourceLocation witchwater_flowing = new ResourceLocation("exnihilo2:blocks/witchwater_flowing");
	public static ResourceLocation azoth_still = new ResourceLocation("exnihilo2:blocks/azoth_still");
	public static ResourceLocation azoth_flowing = new ResourceLocation("exnihilo2:blocks/azoth_flowing");
	
	public static void register()
	{
		witchwater = new Fluid("witchwater", witchwater_still, witchwater_flowing);
		FluidRegistry.registerFluid(witchwater);
		
		azoth = new Fluid("azoth", azoth_still, azoth_flowing);
		FluidRegistry.registerFluid(azoth);
	}
}
