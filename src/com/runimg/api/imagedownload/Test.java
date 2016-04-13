package com.runimg.api.imagedownload;

public class Test {
	public static void main(String[] args) {
		
		
//		String url = SignatureUtil.getRequestUrl("3600", "{\"h\":250,\"w\":250}", "4d", "1460295881372", "123456789ABCDEF0", "1.0", "0123456789ABCDEF");
		
		SignatureUtil.downloadImage("3600", "{\"h\":250,\"w\":250}", "4d", "1460295881372", "123456789ABCDEF0", "1.0", "0123456789ABCDEF","c:\\image\\","51bi.gif");
		
		
	}
}
