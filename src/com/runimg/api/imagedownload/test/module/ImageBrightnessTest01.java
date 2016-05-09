package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageBrightnessTest01 extends GenerallTest {

	public ImageBrightnessTest01(ImageOperator imageOperator) {
		super(imageOperator);
	}


	@Override
	public void process() {
		imageOperator.setImageBrightness(50);
		testName = "setImageBrightness(50)";
		
	}

}
