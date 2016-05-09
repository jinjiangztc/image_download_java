package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.module.ClipperType;
import com.runimg.api.imagedownload.service.ImageOperator;

public  class ImageClipperTest02  extends GenerallTest{

	public ImageClipperTest02(ImageOperator imageOperator) {
		super(imageOperator);
	}

	@Override
	public void process() {
		imageOperator.setImageClipper(272 * 4,ClipperType.MIN_SQUARE);
		testName="setImageClipper(272 * 4,ClipperType.MIN_SQUARE)";
		
	}


}
