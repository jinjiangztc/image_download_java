package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.module.ClipperDirect;
import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageClipperTest03 extends GenerallTest {

	public ImageClipperTest03(ImageOperator imageOperator) {
		super(imageOperator);
	}


	@Override
	public void process() {
		imageOperator.setImageClipper(225, ClipperDirect.CLIPPER_X, 1);
		testName = "setImageClipper(225, ClipperDirect.CLIPPER_X, 1)";
	}

}
