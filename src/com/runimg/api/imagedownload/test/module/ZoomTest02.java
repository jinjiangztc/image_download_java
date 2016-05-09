package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.service.ImageOperator;

public class ZoomTest02 extends GenerallTest {

	public ZoomTest02(ImageOperator imageOperator) {
		super(imageOperator);
	}

	@Override
	public void process() {
		testName= "setImageZoom(256, 512, true)";
		imageOperator.setImageZoom(256, 512, true);
		
	}

}
