package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageClipperTest04  extends GenerallTest{

	public ImageClipperTest04(ImageOperator imageOperator) {
		super(imageOperator);
	}


	@Override
	public void process() {
		imageOperator.setImageClipper(225, 225, 16, 16);
		testName = "setImageClipper(225, 225, 16, 16)";
		
	}

}
