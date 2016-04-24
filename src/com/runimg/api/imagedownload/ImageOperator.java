package com.runimg.api.imagedownload;

import com.runimg.api.imagedownload.module.ClipperDirect;
import com.runimg.api.imagedownload.module.ClipperType;
import com.runimg.api.imagedownload.module.ImageFormat;

public class ImageOperator {

	private static final int NOT_ASSIGNMENT = -1;
	int zoomHeight = NOT_ASSIGNMENT;
	int zoomWidth = NOT_ASSIGNMENT;
	int isProcess = NOT_ASSIGNMENT;
	int proportion = NOT_ASSIGNMENT;
	//
	int clipperHeight = NOT_ASSIGNMENT;
	int clipperWidth = NOT_ASSIGNMENT;
	int clipperRect = NOT_ASSIGNMENT;
	//
	//
	int rotation = NOT_ASSIGNMENT;
	//
	int imageSharpen = NOT_ASSIGNMENT;
	//
	int blurryRadius = NOT_ASSIGNMENT;
	int blurrySigma = NOT_ASSIGNMENT;
	//
	int brightness = NOT_ASSIGNMENT;
	int contrast = NOT_ASSIGNMENT;
	//
	int jpgRelativeQulity = NOT_ASSIGNMENT;
	int jpgAbsolutelyQulity = NOT_ASSIGNMENT;
	ImageFormat imageFormat = null;

	boolean setImageZoom(int height, int width, boolean isProcess) {

		if (height < 1 || height > 4096 || width < 1 || width > 4096) {
			throw new RuntimeException("输入值超过范围:height[1~4096],width[1~4096]");
		}

		this.zoomHeight = height;
		this.zoomWidth = width;
		if (isProcess) {
			this.isProcess = 0;
		} else {
			this.isProcess = -1;
		}

		return true;
	}

	boolean setImageZoom(int proportion) {
		if (proportion < 1 || proportion > 1000) {
			throw new RuntimeException("输入值超过范围:proportion[1~1000]");
		}

		this.proportion = proportion;
		return true;
	}

	//
	boolean setImageClipper(int height, int width, int rect) {
		if (height < 1 || height > 4096 || width < 1 || width > 4096
				|| rect < 1 || rect > 9) {
			this.clipperHeight = height;
			this.clipperWidth = width;
			this.clipperRect = rect;
			throw new RuntimeException(
					"输入值超过范围:height[1~4096],width[1~4096],rect[1~9]");

		}
		return true;
	}

	boolean setImageClipper(int radius, ClipperType clipper_type) {
		return true;
	}

	boolean setImageClipper(int chunk, ClipperDirect clipper_direct, int index) {
		return true;
	}

	boolean setImageClipper(int x, int y, int width, int height) {
		return true;
	}

	//
	boolean setImageRotator(int rotation) {
		return true;
	}

	//
	boolean setImageSharpen(int sharpening) {
		return true;
	}

	boolean setImageBlurry(int radius, int sigma) {
		return true;
	}

	boolean setImageBrightness(int brightness) {
		return true;
	}

	boolean setImageContrast(int contrast) {
		return true;
	}

	//
	boolean setJpgImageRelativeQuality(int quality) {
		return true;
	}

	boolean setJpgImageAbsolutelyQuality(int quality) {
		return true;
	}

	//
	boolean setImageFormat(ImageFormat image_format) {
		return true;
	}

}
