package com.runimg.api.imagedownload;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageAPI {
	public static void main(String[] args) {
		
		
		String url = SignatureUtil.getRequestUrl("3600", "{\"h\":250,\"w\":250}", "4d", "1460295881372", "123456789ABCDEF0", "1.0", "0123456789ABCDEF");
		String html = DownloadUtil.downloadHtml(url);
		System.err.println(html);
		
		Pattern pattern = Pattern.compile("\"url\"\\:\"(.*?)\"\\}");
		Matcher matcher = pattern.matcher(html);
		String imageUrl ="";
		if(matcher.find())
		{
			imageUrl=matcher.group(1).replaceAll("\\\\", "");
		}
		System.err.println(imageUrl);
		 DownloadUtil.downloadImage(imageUrl,"c:\\image\\","51bi.gif");
		
		
	}
}
