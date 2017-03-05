package hufs.ces.appletclock;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class BufferedImageUtility {

	static public BufferedImage getOneColorImage (int width, int height, Color color){
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int[] data = new int[width * height];
		int i = 0;
		for (int y = 0; y < height; y++) {
			int alpha = color.getAlpha();
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();
			for (int x = 0; x < width; x++) {
				data[i++] = (alpha << 24) | (red << 16) | (green << 8) | blue;
			}
		}
		result.setRGB(0, 0, width, height, data, 0, width);

		return result;
	}
	static public BufferedImage getBlurImage (BufferedImage bimage, int blur){
		BufferedImage result = new BufferedImage(bimage.getWidth(), bimage.getHeight(),
				BufferedImage.TYPE_INT_ARGB);

		Kernel kernel = KernelFactory.createKernel(blur);
		ConvolveOp convolveOp = new ConvolveOp(kernel);
		convolveOp.filter(bimage, result);
		return result;
	}

}
