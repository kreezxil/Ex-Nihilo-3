package exnihilo2.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class EN2FuelHandler implements IFuelHandler{

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.getItem() == EN2Items.bucket_porcelain_lava)
			return 20000;
		
		return 0;
	}

}
