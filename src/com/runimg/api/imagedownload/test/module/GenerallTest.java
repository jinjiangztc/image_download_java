package com.runimg.api.imagedownload.test.module;

import com.runimg.api.imagedownload.module.UpdateStanza;
import com.runimg.api.imagedownload.service.ImageOperator;

public abstract class GenerallTest {
	protected String testName;
	protected  UpdateStanza resultStanza;
	protected ImageOperator imageOperator;
	
	public GenerallTest(ImageOperator imageOperator) {
		super();
		this.imageOperator = imageOperator;
		
	}
	public UpdateStanza getResultStanza() {
		return resultStanza;
	}
	public void setResultStanza(UpdateStanza resultStanza) {
		this.resultStanza = resultStanza;
	}
	

	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public abstract void process();

}
