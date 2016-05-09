package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageRotatorTest01 extends GenerallTest{

	public ImageRotatorTest01(ImageOperator imageOperator) {
		super(imageOperator);
	}

	@Override
	public void process() {
		imageOperator.setImageRotator(180);
		testName ="setImageRotator(180)"; 
		
	}

}
