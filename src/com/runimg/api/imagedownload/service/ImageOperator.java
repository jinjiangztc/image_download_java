package com.runimg.api.imagedownload.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;

import net.sf.json.JSONObject;

import com.runimg.api.imagedownload.module.ClipperDirect;
import com.runimg.api.imagedownload.module.ClipperType;
import com.runimg.api.imagedownload.module.ImageFormat;
import com.runimg.api.imagedownload.module.ProgressiveType;
import com.runimg.api.imagedownload.util.Base64Util;
import com.runimg.api.imagedownload.util.Log;

/**
 * 
 * @author jinjiangztc@gmail.com
 */
public class ImageOperator {

	private static final int NOT_ASSIGNMENT = 0x8fffffff;
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

	/**
	 * 
	 * @param height
	 *            图片长度
	 * @param width
	 *            图片宽度
	 * @param isProcess
	 *            目标图大于原图是否处理
	 * @return 是否设置成功
	 */
	public boolean setImageZoom(int height, int width, boolean isProcess) {

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
			this.isProcess = 1;
		}

		return true;
	}

	/**
	 * 
	 * @param height
	 *            图片长度
	 * @param width
	 *            图片宽度
	 * @return 是否设置成功
	 */
	public boolean setImageZoom(int height, int width) {
		return setImageZoom(height, width, false);
	}

	public boolean setImageZoom(int proportion) {
		if (proportion < MIN_PROPORTION_VALUE
				|| proportion > MAX_PROPORTION_VALUE) {
			Log.logInfo("输入值超过范围:proportion[1~1000]");
			return false;
		}

		this.proportion = proportion;
		return true;
	}

	/**
	 * 
	 * @param height
	 *            图片长度
	 * @param width
	 *            图片宽度
	 * @param rect
	 *            指定裁剪区域
	 * @return 是否设置成功
	 */
	public boolean setImageClipper(int height, int width, int rect) {
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

	/**
	 * 
	 * @param radius
	 *            内切圆半径
	 * @param clipperType
	 *            裁剪类型
	 * @return
	 */
	public boolean setImageClipper(int radius, ClipperType clipperType) {

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

	/**
	 * 
	 * @param chunk
	 *            切割长度
	 * @param clipperDirect
	 *            裁剪类型
	 * @param index
	 *            索引切割
	 * @return 是否设置成功
	 */
	public boolean setImageClipper(int chunk, ClipperDirect clipperDirect,
			int index) {

		this.clipperChunk = chunk;
		this.clipperDirect = clipperDirect;
		this.clipperIndex = index;

		return true;
	}

	/**
	 * 
	 * @param x
	 *            起点x坐标
	 * @param y
	 *            y起点y坐标
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @return 是否设置成功
	 */
	public boolean setImageClipper(int x, int y, int width, int height) {
		if (width < MIN_IMAGE_PROCESS_SIZE || width > MAX_IMAGE_PROCESS_SIZE
				|| height < MIN_IMAGE_PROCESS_SIZE
				|| height > MAX_IMAGE_PROCESS_SIZE) {
			Log.logInfo("The Clipper width or height out of the range [1, 4096]");
			return false;
		}
		this.clipperX = x;
		this.clipperY = y;
		this.clipperW = width;
		this.clipperH = height;

		return true;
	}

	/**
	 * 
	 * @param rotation
	 *            旋转角度
	 * @return 是否设置成功
	 */

	public boolean setImageRotator(int rotation) {
		if (rotation > 360 || rotation < 0) {
			Log.logInfo("The rotation out of the range [0, 360]");
			return false;
		}
		this.rotation = rotation;
		return true;
	}

	/**
	 * 
	 * @param sharpening
	 *            锐化度
	 * @return 是否设置成功
	 */
	public boolean setImageSharpen(int sharpening) {
		if (sharpening < 50 || sharpening > 399) {
			Log.logInfo("The image sharpen out of the range [50, 399]");
			return false;
		}
		this.imageSharpen = sharpening;
		return true;
	}

	/**
	 *
	 * @param radius
	 *            模糊的半径
	 * @param sigma
	 *            正态分布的标准差
	 * @return 是否设置成功
	 */
	public boolean setImageBlurry(int radius, int sigma) {
		if (radius < 1 || radius > 50) {
			Log.logInfo("The Blurry radius out of the range [1, 50]");
			return false;
		}
		if (sigma < 1 || sigma > 50) {
			Log.logInfo("The Blurry sigma out of the range [1, 50]");
			return false;
		}
		this.blurryRadius = radius;
		this.blurrySigma = sigma;
		return true;
	}

	/**
	 * 
	 * @param brightness
	 *            原图亮度
	 * @return 是否设置成功
	 */
	public boolean setImageBrightness(int brightness) {
		if (brightness < -100 || brightness > 100) {
			Log.logInfo("The image brightness out of range [-100, 100]");
			return false;
		}
		this.brightness = brightness;
		return true;
	}

	/**
	 * 
	 * @param contrast
	 *            对比度调节
	 * @return 是否设置成功
	 */
	public boolean setImageContrast(int contrast) {
		if (contrast < -100 || contrast > 100) {
			Log.logInfo("The image contrast out of range [-100, 100]");
			return false;
		}
		this.contrast = contrast;
		return true;
	}

	/**
	 * 
	 * @param quality
	 *            质量压缩
	 * @return 是否设置成功
	 */
	public boolean setJpgImageRelativeQuality(int quality) {
		if (quality < 1 || quality > 1000) {
			Log.logInfo("The Jpg image relative quality out of the range [1, 1000]");
			return false;
		}
		this.jpgRelativeQulity = quality;
		return true;
	}

	/**
	 * 
	 * @param quality
	 *            原图质量质量
	 * @return 是否设置成功
	 */

	public boolean setJpgImageAbsolutelyQuality(int quality) {
		if (quality < 1 || quality > 1000) {
			Log.logInfo("The Jpg image absolute quality out of the range [1, 1000]");
			return false;
		}
		this.jpgAbsolutelyQulity = quality;
		return true;
	}

	/**
	 * 
	 * @param progressive	具体jpg格式
	 * @return	是否设置成功
	 */
	public boolean setJpgProgressive(ProgressiveType progressive) {
		if (progressive == null) {
			Log.logInfo("The Jpg image progressive type error");
			return false;
		}
		this.progressive = progressive;
		return true;
	}

	/**
	 * 
	 * @param imageFormat 图片格式
	 * @return 是否设置成功
	 */
	public boolean setImageFormat(ImageFormat imageFormat) {
		if (imageFormat == null) {
			Log.logInfo("The image format settings failure");
			return false;
		}
		this.imageFormat = imageFormat;
		return true;
	}

	/**
	 * @return 讲设置好的参数转化成json,然后进行Base64处理返回
	 */
	public String toString() {
		Map<String, Object> keyValues = new HashMap<String, Object>();
		// 1. Zoom Settings
		if (zoomHeight != NOT_ASSIGNMENT) {
			keyValues.put("h", zoomHeight);
		}
		if (zoomWidth != NOT_ASSIGNMENT) {
			keyValues.put("w", zoomWidth);
		}
		if (isProcess == 0) {
			keyValues.put("l", 0);
		}
		if (proportion != NOT_ASSIGNMENT) {
			keyValues.put("p", proportion);
		}
		// 2. Clipper Settings
		if (clipperHeight != NOT_ASSIGNMENT && clipperWidth != NOT_ASSIGNMENT
				&& clipperRect > 0 && clipperRect < 10) {
			keyValues.put("rc", clipperHeight + "x" + clipperWidth + "-"
					+ clipperRect);
		}
		if (clipperRadius != NOT_ASSIGNMENT && clipperType != null) {
			keyValues.put("ci",
					clipperRadius + "-" + clipperType.clipperTypeToInt());

		}
		if (clipperChunk != NOT_ASSIGNMENT && clipperIndex != NOT_ASSIGNMENT
				&& clipperDirect != null) {

			keyValues.put("ic",
					clipperChunk + clipperDirect.clipperDirectToString() + "-"
							+ clipperIndex);
		}
		if (clipperX != NOT_ASSIGNMENT && clipperY != NOT_ASSIGNMENT
				&& clipperW != NOT_ASSIGNMENT && clipperH != NOT_ASSIGNMENT) {

			keyValues.put("a", clipperX + "-" + clipperY + "-" + clipperW + "-"
					+ clipperH);
		}
		// Rotator
		if (rotation != NOT_ASSIGNMENT) {
			keyValues.put("r", rotation);
		}
		// Sharpen
		if (imageSharpen != NOT_ASSIGNMENT) {
			keyValues.put("sh", imageSharpen);
		}
		// Blurry
		if (blurryRadius != NOT_ASSIGNMENT && blurrySigma != NOT_ASSIGNMENT) {
			keyValues.put("bl", blurryRadius + "-" + blurrySigma);
		}
		// Brightness
		if (brightness != NOT_ASSIGNMENT) {
			keyValues.put("b", brightness);
		}
		// Contrast
		if (contrast != NOT_ASSIGNMENT) {
			keyValues.put("d", contrast);
		}
		// Format
		if (imageFormat != null && imageFormat != ImageFormat.FORMAT_SRC) {
			keyValues.put("format", imageFormat.imageFormatToString());
		}
		if (imageFormat == ImageFormat.FORMAT_JPG
				&& jpgRelativeQulity != NOT_ASSIGNMENT) {
			keyValues.put("q", jpgRelativeQulity);
		}
		if (imageFormat == ImageFormat.FORMAT_JPG
				&& jpgAbsolutelyQulity != NOT_ASSIGNMENT) {
			keyValues.put("Q", jpgAbsolutelyQulity);
		}
		if (imageFormat == ImageFormat.FORMAT_JPG && progressive != null) {

			keyValues.put("pr", progressive.progressiveToInt());

		}
		JSONObject json = JSONObject.fromObject(keyValues);
		return Base64Util.encodeBase64(json.toString().getBytes());
	}

	/**
	 * 初始化设置
	 */

	public void reset() {
		zoomHeight = NOT_ASSIGNMENT;
		zoomWidth = NOT_ASSIGNMENT;
		isProcess = NOT_ASSIGNMENT;
		proportion = NOT_ASSIGNMENT;
		clipperHeight = NOT_ASSIGNMENT;
		clipperWidth = NOT_ASSIGNMENT;
		clipperRect = NOT_ASSIGNMENT;
		rotation = NOT_ASSIGNMENT;
		imageSharpen = NOT_ASSIGNMENT;
		blurryRadius = NOT_ASSIGNMENT;
		blurrySigma = NOT_ASSIGNMENT;
		brightness = NOT_ASSIGNMENT;
		contrast = NOT_ASSIGNMENT;
		jpgRelativeQulity = NOT_ASSIGNMENT;
		jpgAbsolutelyQulity = NOT_ASSIGNMENT;
		imageFormat = ImageFormat.FORMAT_SRC;
		clipperRadius = NOT_ASSIGNMENT;
		clipperType = ClipperType.SOURCE;
		clipperChunk = NOT_ASSIGNMENT;
		clipperDirect = ClipperDirect.CLIPPER_X;
		clipperIndex = NOT_ASSIGNMENT;
		clipperX = NOT_ASSIGNMENT;
		clipperY = NOT_ASSIGNMENT;
		clipperW = NOT_ASSIGNMENT;
		clipperH = NOT_ASSIGNMENT;
	}

	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties");
		ImageOperator imageOperator = new ImageOperator();
		imageOperator.setImageContrast(1);

	}

}
