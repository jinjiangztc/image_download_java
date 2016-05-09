package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageClipperTest01  extends GenerallTest{

	public ImageClipperTest01(ImageOperator imageOperator) {
		super(imageOperator);
	}


	@Override
	public void process() {
		testName = "SetImageClipper(128, 128, 1)";
		imageOperator.setImageClipper(128, 128, 1);
		
	}

}
