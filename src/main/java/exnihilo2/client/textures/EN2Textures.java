package exnihilo2.client.textures;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.Map;

import exnihilo2.EN2;
import exnihilo2.client.textures.files.TextureAtlasSpriteDynamic;
import exnihilo2.client.textures.files.TextureLoader;
import exnihilo2.client.textures.files.TextureLocations;
import exnihilo2.client.textures.manipulation.TextureCompositing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class EN2Textures {
	public static void registerCustomTextures(TextureMap map)
	{
		map.registerSprite(new ResourceLocation("exnihilo2:blocks/compost"));
		map.registerSprite(new ResourceLocation("exnihilo2:blocks/sieve_mesh_silk_white"));
		map.registerSprite(new ResourceLocation("exnihilo2:blocks/sieve_mesh_wood"));
		
		registerDirtFurnaceTextures(map);
	}
	
	private static void registerDirtFurnaceTextures(TextureMap map)
	{
		try
		{
			BufferedImage dirt = TextureLoader.load(TextureLocations.getBlockLocation("minecraft", "dirt"));
			BufferedImage side_mask = TextureLoader.load(TextureLocations.getBlockLocation("exnihilo2", "furnace_mask_side"));
			BufferedImage front_mask_off = TextureLoader.load(TextureLocations.getBlockLocation("exnihilo2", "furnace_mask_front_off"));
			BufferedImage front_mask_on = TextureLoader.load(TextureLocations.getBlockLocation("exnihilo2", "furnace_mask_front_on"));
			
			if (dirt != null && side_mask != null && front_mask_off != null && front_mask_on != null)
			{
				BufferedImage side = TextureCompositing.composite(dirt, side_mask);
				TextureAtlasSpriteDynamic sideSprite = TextureAtlasSpriteDynamic.fromImage(new ResourceLocation("exnihilo2:blocks/furnace_dirt_side"), side);
				forceTextureRegistration(map, sideSprite);
				
				BufferedImage front_off = TextureCompositing.composite(dirt, front_mask_off);
				TextureAtlasSpriteDynamic frontSpriteOff = TextureAtlasSpriteDynamic.fromImage(new ResourceLocation("exnihilo2:blocks/furnace_dirt_front_off"), front_off);
				forceTextureRegistration(map, frontSpriteOff);
				
				BufferedImage front_on = TextureCompositing.composite(dirt, front_mask_on);
				TextureAtlasSpriteDynamic frontSpriteOn = TextureAtlasSpriteDynamic.fromImage(new ResourceLocation("exnihilo2:blocks/furnace_dirt_front_on"), front_on);
				forceTextureRegistration(map, frontSpriteOn);
			}
			else
			{
				EN2.log.error("Failed to load one or more images required to render the dirt furnace.");
			}
		}
		catch (Exception e)
		{
			EN2.log.error("Critical error occured while generating furnace textures.");
		}
		
	}
	
	private static void forceTextureRegistration(TextureMap map, TextureAtlasSprite sprite)
	{
		try 
		{
			Field f;
			f = map.getClass().getDeclaredField("mapRegisteredSprites");
			f.setAccessible(true);
			
			Map mapRegisteredSprites = (Map) f.get(map);
			
			mapRegisteredSprites.put(sprite.getIconName(), sprite);
		} 
		catch (Exception e) 
		{
			EN2.log.error("Failed to overwrite texture: " + sprite.getIconName());
		}
	}
}
