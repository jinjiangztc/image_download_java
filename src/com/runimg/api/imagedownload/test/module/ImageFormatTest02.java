package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.module.ImageFormat;
import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageFormatTest02 extends GenerallTest{

	public ImageFormatTest02(ImageOperator imageOperator) {
		super(imageOperator);
	}


	@Override
	public void process() {

		imageOperator.setImageFormat(ImageFormat.FORMAT_GIT);
		testName="setImageFormat(ImageFormat.FORMAT_GIT)";
	}

}
