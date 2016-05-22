package com.runimg.api.imagedownload.test;

import org.apache.log4j.PropertyConfigurator;

import com.runimg.api.imagedownload.module.ImageType;
import com.runimg.api.imagedownload.module.Record;
import com.runimg.api.imagedownload.module.UpdateStanza;
import com.runimg.api.imagedownload.service.ImageOperator;
import com.runimg.api.imagedownload.service.RunimgService;
import com.runimg.api.imagedownload.service.UrlCreator;

public class Test {

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		ImageOperator imageOperator = new ImageOperator();
		System.err.println(imageOperator.setImageClipper(225, 225, 16, 16));
		UrlCreator urlCreator = new UrlCreator("123456789ABCDEF0",
				"0123456789ABCDEF", ImageType.TYPE_2D_0_0, 3600, imageOperator);
		urlCreator.setRecordInterval(0L, 0L);
		System.err.println(urlCreator.toUrlString());
		UpdateStanza updateStanza = RunimgService.getImageUrl(
				"http://www.runimg.com/services.php/lastupdate?", urlCreator);

		System.err.println(updateStanza.getStatus());
		for (Record record : updateStanza.getRecords()) {
			System.err.println(record.getUrl());
		}

		RunimgService.getImageByUrl(updateStanza.getRecords().get(0).getUrl(),
				"c:\\image\\", "51bi.gif");
	}
}
