package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.module.ImageFormat;
import com.runimg.api.imagedownload.service.ImageOperator;

public class ImageFormatTest05 extends GenerallTest{

	public ImageFormatTest05(ImageOperator imageOperator) {
		super(imageOperator);
	}


	@Override
	public void process() {
		imageOperator.setImageFormat(ImageFormat.FORMAT_SRC);
		testName = "setImageFormat(ImageFormat.FORMAT_SRC)";
		
		
	}

}
