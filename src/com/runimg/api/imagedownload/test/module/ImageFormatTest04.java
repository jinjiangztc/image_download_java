package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.module.ImageFormat;
import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageFormatTest04  extends GenerallTest{

	public ImageFormatTest04(ImageOperator imageOperator) {
		super(imageOperator);
	}

	@Override
	public void process() {
		imageOperator.setImageFormat(ImageFormat.FORMAT_PNG);
		testName="setImageFormat(ImageFormat.FORMAT_PNG)";
	}

}
