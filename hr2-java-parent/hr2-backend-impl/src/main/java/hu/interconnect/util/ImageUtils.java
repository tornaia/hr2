package hu.interconnect.util;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.exception.UzletiHiba;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.apache.sanselan.ImageFormat;

public final class ImageUtils {

	public static byte[] toJpg(String type, byte[] bytes, int width, int height) {
		boolean isKnown = KnownImageType.isKnown(type);
		if (!isKnown) {
			throw new UzletiHiba("Unknown image extension: " + type);
		}
		try {
			return scaleImage(bytes, width, height);
		} catch (Exception e) {
			throw new UzletiHiba("Converting image from " + type + " to JPG failed", e);
		}
	}

	private static byte[] scaleImage(byte[] sourceImage, int targetWidth, int targetHeight) throws IOException {
		InputStream imageStream = new ByteArrayInputStream(sourceImage);
		Image image = ImageIO.read(imageStream);
		int width = targetWidth;
		int height = targetHeight;
		// Make sure the aspect ratio is maintained, so the image is not skewed
		double thumbRatio = (double) width / (double) height;
		int actualWidth = image.getWidth(null);
		int actualHeight = image.getHeight(null);
		if (actualWidth < -1 || actualHeight < 1) {
			throw new ProgramozasiHiba("Ismeretlen kép! width: " + actualWidth + ", height: " + actualHeight);
		}
		double imageRatio = (double) actualWidth / (double) actualHeight;
		if (thumbRatio < imageRatio) {
			height = (int) (width / imageRatio);
		} else {
			width = (int) (height * imageRatio);
		}

		if (actualWidth < width && actualHeight < height) {
			width = actualWidth;
			height = actualHeight;
		}

		// Draw the scaled image
		BufferedImage thumbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, width, height, null);
		// Write the scaled image to the outputstream
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(thumbImage, ImageFormat.IMAGE_FORMAT_JPEG.extension, out);
		return out.toByteArray();
	}

	public enum KnownImageType {
		BMP,
		GIF,
		JPG,
		JPEG,
		PNG;

		public static boolean isKnown(String type) {
			try {
				KnownImageType.valueOf(type.toUpperCase(Locale.ENGLISH));
				return true;
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
	}

	private ImageUtils() {
	}
}
