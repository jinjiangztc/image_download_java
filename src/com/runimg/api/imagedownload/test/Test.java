package com.runimg.api.imagedownload.test;

import com.runimg.api.imagedownload.module.ImageType;
import com.runimg.api.imagedownload.module.Record;
import com.runimg.api.imagedownload.module.UpdateStanza;
import com.runimg.api.imagedownload.service.ImageOperator;
import com.runimg.api.imagedownload.service.RunimgService;
import com.runimg.api.imagedownload.service.UrlCreator;

public class Test {

	public static void main(String[] args) {

		ImageOperator imageOperator = new ImageOperator();
		imageOperator.setImageZoom(100, 100, true);
		UrlCreator urlCreator = new UrlCreator("123456789ABCDEF0",
				"0123456789ABCDEF", ImageType.TYPE_4D, 3600, imageOperator);
		urlCreator.setRecordInterval(1461700865L, 1461740865L);
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
