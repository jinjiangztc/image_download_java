package com.runimg.api.imagedownload;

import java.util.HashMap;
import java.util.Map;
import com.runimg.api.imagedownload.module.ClipperDirect;
import com.runimg.api.imagedownload.module.ClipperType;
import com.runimg.api.imagedownload.module.ImageFormat;
import com.runimg.api.imagedownload.module.ProgressiveType;

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
	ClipperType clipperType = ClipperType.SOURCE;
	int clipperChunk = NOT_ASSIGNMENT;
	ClipperDirect clipperDirect = ClipperDirect.CLIPPER_X;
	int clipperIndex = NOT_ASSIGNMENT;
	int clipperX = NOT_ASSIGNMENT;
	int clipperY = NOT_ASSIGNMENT;
	int clipperW = NOT_ASSIGNMENT;
	int clipperH = NOT_ASSIGNMENT;
	ProgressiveType progressive = null;

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
		if (height < MIN_IMAGE_PROCESS_SIZE || height > MAX_PROPORTION_VALUE
				|| width < MIN_IMAGE_PROCESS_SIZE
				|| width > MAX_PROPORTION_VALUE || rect < 1 || rect > 9) {
			Log.logInfo("输入值超过范围:height[1~4096],width[1~4096],rect[1~9]");
			return false;
		}
		this.clipperHeight = height;
		this.clipperWidth = width;
		this.clipperRect = rect;

		return true;
	}

	boolean setImageClipper(int radius, ClipperType clipperType) {

		if (radius < 1 || radius > 4096) {
			Log.logInfo("The clipper radius out of the range [1, 4096]");
			return false;
		}
		if (clipperType == null) {
			Log.logInfo("The clipper type setting error");
			return false;
		}
		this.clipperRadius = radius;
		this.clipperType = clipperType;
		return true;

	}

	boolean setImageClipper(int chunk, ClipperDirect clipper_direct, int index) {

		this.clipperChunk = chunk;
		this.clipperDirect = clipper_direct;
		this.clipperIndex = index;
		return true;
	}

	/*
	 * 比较疑惑
	 */
	boolean setImageClipper(int x, int y, int width, int height) {
		if (clipperW < MIN_IMAGE_PROCESS_SIZE
				|| clipperW > MAX_IMAGE_PROCESS_SIZE
				|| clipperH < MIN_IMAGE_PROCESS_SIZE
				|| clipperH > MAX_IMAGE_PROCESS_SIZE) {
			Log.logInfo("The Clipper width or height out of the range [1, 4096]");
			return false;
		}
		this.clipperX = x;
		this.clipperY = y;
		this.clipperW = width;
		this.clipperH = height;
		return true;
	}

	//
	boolean setImageRotator(int rotation) {
		if (rotation > 360 || rotation < 0) {
			Log.logInfo("The rotation out of the range [0, 360]");
			return false;
		}
		this.rotation = rotation;
		return true;
	}

	//
	boolean setImageSharpen(int sharpening) {
		if (sharpening < 50 || sharpening > 399) {
			Log.logInfo("The image sharpen out of the range [50, 399]");
		}
		this.imageSharpen = sharpening;
		return true;
	}

	boolean setImageBlurry(int radius, int sigma) {
		if (this.blurryRadius < 1 || this.blurryRadius > 50) {
			Log.logInfo("The Blurry radius out of the range [1, 50]");
			return false;
		}
		if (this.blurrySigma < 1 || this.blurrySigma > 50) {
			Log.logInfo("The Blurry sigma out of the range [1, 50]");
			return false;
		}
		this.blurryRadius = radius;
		this.blurrySigma = sigma;
		return true;
	}

	boolean setImageBrightness(int brightness) {
		if (brightness < -100 || brightness > 100) {
			Log.logInfo("The image brightness out of range [-100, 100]");
			return false;
		}
		this.brightness = brightness;
		return true;
	}

	boolean setImageContrast(int contrast) {
		if (this.contrast < -100 || this.contrast > 100) {
			Log.logInfo("The image contrast out of range [-100, 100]");
			return false;
		}
		this.contrast = contrast;
		return true;
	}

	//
	boolean setJpgImageRelativeQuality(int quality) {
		if (quality < 1 || quality > 1000) {
			Log.logInfo("The Jpg image relative quality out of the range [1, 1000]");
			return false;
		}
		this.jpgRelativeQulity = quality;
		return true;
	}

	boolean setJpgImageAbsolutelyQuality(int quality) {
		if (quality < 1 || quality > 1000) {
			Log.logInfo("The Jpg image absolute quality out of the range [1, 1000]");
			return false;
		}
		this.jpgAbsolutelyQulity = quality;
		return true;
	}

	boolean setJpgProgressive(ProgressiveType progressive) {
		if (progressive == null) {
			Log.logInfo("The Jpg image progressive type error");
			return false;
		}
		this.progressive = progressive;
		return true;
	}

	//
	boolean setImageFormat(ImageFormat image_format) {
		return true;
	}

	public String toString() {
		// Map<String , String>keyValues = new HashMap<String, String>();
		// if(this.zoomHeight != NOT_ASSIGNMENT) {
		// keyValues.put("h", String.valueOf(this.zoomHeight));
		// }
		// if(this.zoomWidth != NOT_ASSIGNMENT) {
		// keyValues.put("w", String.valueOf(this.zoomWidth));
		// }
		// if(this.isProcess!= NOT_ASSIGNMENT) {
		// value[JSON_IMAGE_ZOOM_IS_PROCESS] = 0;
		// }
		// if(this.proportion!= NOT_ASSIGNMENT) {
		// value[JSON_IMAGE_ZOOM_PROPORTION] = this.proportion;
		// }
		// // 2. Clipper Settings
		// if(this.clipperHeight != NOT_ASSIGNMENT
		// && clipperWidth != NOT_ASSIGNMENT
		// && clipperRect > 0 && clipperRect < 10) {
		// std::stringstream ss;
		// ss << clipperHeight_ << "x" << clipperWidth << "-" << clipperRect;
		// value[JSON_CLIPPER_RECT] = ss.str();
		// }
		// if(clipperRadius != NOT_ASSIGNMENT
		// && clipperType!=null) {
		// std::stringstream ss;
		// ss << clipper_radius_ << "-" << ClipperTypeToUint32(clipper_type_);
		// value[JSON_CLIPPER_RADIOUS] = ss.str();
		// }
		// if(clipperChunk != NOT_ASSIGNMENT
		// && clipperIndex != NOT_ASSIGNMENT
		// && ClipperDirectToString(clipper_direct_) != NULL) {
		// std::stringstream ss;
		// ss << clipper_chunk_ << ClipperDirectToString(clipper_direct_)
		// << "-" << clipper_index_;
		// value[JSON_CLIPPER_INDEX] = ss.str();
		// }
		// if(clipperX != NOT_ASSIGNMENT
		// && clipperY != NOT_ASSIGNMENT
		// && clipperW != NOT_ASSIGNMENT
		// && clipperH != NOT_ASSIGNMENT) {
		// std::stringstream ss;
		// ss << clipper_x_ << "-" << clipper_y_ << "-"
		// << clipper_w_ << "-" << clipper_h_;
		// value[JSON_CLIPPER_POINT] = ss.str();
		// }
		// // Rotator
		// if(rotation != NOT_ASSIGNMENT) {
		// value[JSON_ROTATION] = rotation_;
		// }
		// // Sharpen
		// if(imageSharpen!= NOT_ASSIGNMENT) {
		// value[JSON_SHARPEN] = imageSharpen;
		// }
		// // Blurry
		// if(blurryRadius != NOT_ASSIGNMENT && blurrySigma != NOT_ASSIGNMENT) {
		// std::stringstream ss;
		// ss << blurry_radius_ << "-" << blurrySigma;
		// value[JSON_BLURRY] = ss.str();
		// }
		// // Brightness
		// if(brightness!= NOT_ASSIGNMENT) {
		// value[JSON_BRIGHTNESS] = brightness;
		// }
		// // Contrast
		// if(contrast!= NOT_ASSIGNMENT) {
		// value[JSON_CONTRAST] = contrast;
		// }
		// // Format
		// if(imageFormat!= null
		// && imageFormat!= ImageFormat.FORMAT_SRC) {
		// value[JSON_IMAGE_FORMAT] = imageformat.;
		// }
		// if(imageFormat == ImageFormat.FORMAT_JPG && jpgRelativeQulity !=
		// NOT_ASSIGNMENT) {
		// value[JSON_JPG_RELATIVE_QULITY] = jpg_relative_qulity_;
		// }
		// if(image_format_ == FORMAT_JPG && jpg_absolutely_qulity_ !=
		// NOT_ASSIGNMENT) {
		// value[JSON_JPG_ABSOLUTELY_QULITY] = jpg_absolutely_qulity_;
		// }
		// if(image_format_ == FORMAT_JPG
		// && ProgressiveToUint32(progressive_) != NOT_ASSIGNMENT) {
		// value[JSON_JPG_PROGRESSIVE] = ProgressiveToUint32(progressive_);
		// }
		// Json::FastWriter fw;
		// return runimg::Base64::Encode(fw.write(value));

		return null;
	}
}
