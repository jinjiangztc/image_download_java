package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.service.ImageOperator;


public class ImageBlurryTest01  extends GenerallTest{


	public ImageBlurryTest01(ImageOperator imageOperator) {
		super(imageOperator);
	}


	@Override
	public void process() {
		imageOperator.setImageBlurry(25, 25);
		testName= "setImageBlurry(25, 25)";
	}

}
