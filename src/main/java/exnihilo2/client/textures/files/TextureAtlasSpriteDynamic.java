package exnihilo2.client.textures.files;

import java.awt.image.BufferedImage;
import java.io.IOException;

import exnihilo2.EN2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class TextureAtlasSpriteDynamic extends TextureAtlasSprite{

	public TextureAtlasSpriteDynamic(String spriteName) {
		super(spriteName);
	}
	
	public void loadImage(BufferedImage image)
	{
		BufferedImage[] images = new BufferedImage[Minecraft.getMinecraft().gameSettings.mipmapLevels + 1];
		images[0] = image;
		
		try
    {
      this.loadSprite(images, null);
    }
    catch (IOException e){}
	}
	
	public static TextureAtlasSpriteDynamic fromImage(ResourceLocation loc, BufferedImage image)
	{
		TextureAtlasSpriteDynamic sprite = new TextureAtlasSpriteDynamic(loc.toString());
		sprite.loadImage(image);
		
		return sprite;
	}

	@Override
	public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
		return true;
	}

	@Override
	public boolean load(IResourceManager manager, ResourceLocation location) {
		return false;
	}
}
