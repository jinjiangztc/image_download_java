package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageContrastTest01 extends GenerallTest{

	public ImageContrastTest01(ImageOperator imageOperator) {
		super(imageOperator);
	}
	@Override
	public void process() {
		imageOperator.setImageContrast(50);
		testName = "setImageContrast(50)";
		
	}

}
