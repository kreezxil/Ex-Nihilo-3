package exnihilo2.client.textures.manipulation;

import java.awt.image.BufferedImage;

import exnihilo2.EN2;
import exnihilo2.util.Color;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class TextureCompositing {
	public static BufferedImage composite(BufferedImage imgBackground, BufferedImage imgForeground)
	{
		if (imgBackground == null || imgForeground == null)
		{
			EN2.log.error("Null image sent to compositor.");
			return null;
		}

		if (!normalCompositePossible(imgBackground, imgForeground))
		{
			EN2.log.error("Images with different sizes can't be composited.");
			return null;
		}

		int w = imgBackground.getWidth();
		int h = imgBackground.getHeight();

		// create an output image that we will use to override
		BufferedImage imgOutput = new BufferedImage(w, h, imgBackground.getType());

		int[] backgroundData = new int[w * h];
		int[] foregroundData = new int[w * h];
		int[] outputData = new int[w * h];

		// read the rgb color data into our array
		imgBackground.getRGB(0, 0, w, h, backgroundData, 0, w);
		imgForeground.getRGB(0, 0, w, h, foregroundData, 0, w);

		// 'over' blend each pixel
		for (int i = 0; i < backgroundData.length; i++) {
			Color colorBackground = new Color(backgroundData[i]);
			Color colorForeground = new Color(foregroundData[i]);

			//Debug code!
			//EN2.log.error("Background pixel r:" + colorBackground.r + ", g:" +  colorBackground.g + ", b:" + colorBackground.b + ", a:" +  colorBackground.a);
			//EN2.log.error("Foreground pixel r:" + colorForeground.r + ", g:" +  colorForeground.g + ", b:" + colorForeground.b + ", a:" +  colorForeground.a);

			outputData[i] = backgroundData[i];

			if (colorForeground.a > 0)
			{
				float alpha = colorForeground.a;

				float a = colorBackground.a;
				float r = (colorForeground.r * alpha) + (colorBackground.r) * (1.0f - alpha);
				float g = (colorForeground.g * alpha) + (colorBackground.g) * (1.0f - alpha);
				float b = (colorForeground.b * alpha) + (colorBackground.b) * (1.0f - alpha);

				//EN2.log.error("Blended pixel r:" + r + ", g:" +  g + ", b:" + b + ", a:" +  a);
				outputData[i] = (new Color(r, g, b, a).toInt());
			}else
			{
				outputData[i] = backgroundData[i];
			}
		}

		// write the new image data to the output image buffer
		imgOutput.setRGB(0, 0, w, h, outputData, 0, w);

		return imgOutput;
	}

	private static boolean normalCompositePossible(BufferedImage imgBackground, BufferedImage imgForeground)
	{
		//if the size is identical, then compositing in normal mode is possible. 
		return (imgBackground.getWidth() == imgForeground.getWidth()) && (imgBackground.getHeight() == imgForeground.getHeight());
	}
}
