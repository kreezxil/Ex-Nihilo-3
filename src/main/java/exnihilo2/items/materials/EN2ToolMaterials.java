package exnihilo2.items.materials;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class EN2ToolMaterials{
	public static ToolMaterial Stick;
	public static ToolMaterial Bone;
	
	public static void initialize()
	{
		Stick = EnumHelper.addToolMaterial("Stick", 0, 59, 2.0F, 0.0F, 15);
		Stick.setRepairItem(new ItemStack(Items.stick, 0));
		
		Bone = EnumHelper.addToolMaterial("Bone", 1, 131, 4.0F, 1.0F, 5);
		Bone.setRepairItem(new ItemStack(Items.dye, 1, 15)); //bonemeal
	}
}
