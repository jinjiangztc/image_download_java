package com.runimg.api.imagedownload.module;

import java.util.List;

/**
 * 
 * @author 3458968481@qq.com
 */
public class UpdateStanza {

	int status;
	List<Record> records;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

}
