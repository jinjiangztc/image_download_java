package com.runimg.api.imagedownload;

import java.util.HashMap;
import java.util.Map;

import com.runimg.api.imagedownload.module.ClipperDirect;
import com.runimg.api.imagedownload.module.ClipperType;
import com.runimg.api.imagedownload.module.ImageFormat;

public class ImageOperator {

	private static final int NOT_ASSIGNMENT = -1;

	private static final int MAX_IMAGE_PROCESS_SIZE = 4096;
	private static final int MIN_IMAGE_PROCESS_SIZE = 1;

	private static final int MIN_PROPORTION_VALUE = 1;
	private static final int MAX_PROPORTION_VALUE = 1000;

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
	int clipperRadius = NOT_ASSIGNMENT;
	ClipperType  clipperType = ClipperType.SOURCE;
	int clipperChunk= NOT_ASSIGNMENT;
	ClipperDirect  clipperDirect = ClipperDirect.CLIPPER_X;
	int   clipperIndex = NOT_ASSIGNMENT;
	int    clipperX = NOT_ASSIGNMENT;
	int  clipperY = NOT_ASSIGNMENT;
	int   clipperW = NOT_ASSIGNMENT;
	int   clipperH = NOT_ASSIGNMENT;

	boolean setImageZoom(int height, int width, boolean isProcess) {

		if (height < MIN_IMAGE_PROCESS_SIZE || height > MAX_IMAGE_PROCESS_SIZE
				|| width < MIN_IMAGE_PROCESS_SIZE
				|| width > MAX_IMAGE_PROCESS_SIZE) {
			Log.logInfo("输入值超过范围:height[1~4096],width[1~4096]");
			return false;
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
		if (proportion < MIN_PROPORTION_VALUE
				|| proportion > MAX_PROPORTION_VALUE) {
			Log.logInfo("输入值超过范围:proportion[1~1000]");
			return false;
		}

		this.proportion = proportion;
		return true;
	}

	//
	boolean setImageClipper(int height, int width, int rect) {
		if (height < MIN_IMAGE_PROCESS_SIZE|| height > MAX_PROPORTION_VALUE || width < MIN_IMAGE_PROCESS_SIZE || width > MAX_PROPORTION_VALUE
				|| rect < 1 || rect > 9) {
			Log.logInfo("输入值超过范围:height[1~4096],width[1~4096],rect[1~9]");
			return false;
		}
			this.clipperHeight = height;
			this.clipperWidth = width;
			this.clipperRect = rect;

		return true;
	}

	boolean setImageClipper(int radius, ClipperType clipperType) {

		if(radius < 1 || radius > 4096) {
			Log.logInfo("The clipper radius out of the range [1, 4096]");
		    return false;
		  }
		  if(clipperType == null) {
			  Log.logInfo("The clipper type setting error");
		    return false;
		  }
		  this.clipperRadius= radius;
		  this.clipperType  = clipperType;
		  return true;
		
	}

	boolean setImageClipper(int chunk, ClipperDirect clipper_direct, int index) {
		
		this.clipperChunk = chunk;
		this.clipperDirect= clipper_direct;
		this. clipperIndex = index;
		  return true;
	}

	boolean setImageClipper(int x, int y, int width, int height) {
		if(clipper_w_ < 1
			      || clipper_w_ > 4096
			      || clipper_h_ < 1
			      || clipper_h_ > 4096) {
			    LOG(INFO) << "The Clipper width or height out of the range [1, 4096]";
			    return false;
			  }
			  clipper_x_ = x;
			  clipper_y_ = y;
			  clipper_w_ = width;
			  clipper_h_ = height;
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
	
	public String toString()
	{
		Map<String, String> keyValues = new HashMap<String, String>();
		
		 if(zoomHeight != NOT_ASSIGNMENT) {
			 keyValues.put("w", String.valueOf(zoomHeight));
			  }
		
		
		return null;
	}

}
