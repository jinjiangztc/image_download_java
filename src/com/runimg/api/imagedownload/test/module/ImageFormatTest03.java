package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.module.ImageFormat;
import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageFormatTest03 extends GenerallTest {

	public ImageFormatTest03(ImageOperator imageOperator) {
		super(imageOperator);
	}

	@Override
	public void process() {
		imageOperator.setImageFormat(ImageFormat.FORMAT_JPG);
		testName = "setImageFormat(ImageFormat.FORMAT_JPG)";
	}

}
