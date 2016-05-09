package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.service.ImageOperator;

public class ZoomTest01 extends GenerallTest{

	public ZoomTest01(ImageOperator imageOperator) {
		super(imageOperator);
	}

	@Override
	public void process() {
		
		testName="setImageZoom(50)";
		imageOperator.setImageZoom(256, 512);
		
	}

}
