package exnihilo2.items.meshs;

import java.util.Collections;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMesh extends Item{
	private String texture_location;
	
	public ItemMesh(String texture_location)
	{
		super();
		
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setMeshTexture(texture_location);
		this.setMaxStackSize(1);
		
		EnchantmentHelper.mapEnchantmentData(this.getItemEnchantability(), new ItemStack(this));
	}
	
	@SideOnly(Side.CLIENT)
	public void setMeshTexture(String texture_location)
	{
		this.texture_location = texture_location;
	}
	
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getMeshTexture()
	{
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture_location);
	}

	@Override
	public int getItemEnchantability() {
		return 30;
	}
}
