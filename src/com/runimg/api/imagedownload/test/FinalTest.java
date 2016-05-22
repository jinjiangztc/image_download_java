package com.runimg.api.imagedownload.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.runimg.api.imagedownload.module.ImageType;
import com.runimg.api.imagedownload.module.UpdateStanza;
import com.runimg.api.imagedownload.service.ImageOperator;
import com.runimg.api.imagedownload.service.RunimgService;
import com.runimg.api.imagedownload.service.UrlCreator;
import com.runimg.api.imagedownload.test.module.GenerallTest;
import com.runimg.api.imagedownload.test.module.ImageBlurryTest01;
import com.runimg.api.imagedownload.test.module.ImageBrightnessTest01;
import com.runimg.api.imagedownload.test.module.ImageClipperTest01;
import com.runimg.api.imagedownload.test.module.ImageClipperTest02;
import com.runimg.api.imagedownload.test.module.ImageClipperTest03;
import com.runimg.api.imagedownload.test.module.ImageClipperTest04;
import com.runimg.api.imagedownload.test.module.ImageContrastTest01;
import com.runimg.api.imagedownload.test.module.ImageFormatTest01;
import com.runimg.api.imagedownload.test.module.ImageFormatTest02;
import com.runimg.api.imagedownload.test.module.ImageFormatTest03;
import com.runimg.api.imagedownload.test.module.ImageFormatTest04;
import com.runimg.api.imagedownload.test.module.ImageFormatTest05;
import com.runimg.api.imagedownload.test.module.ImageFormatTest06;
import com.runimg.api.imagedownload.test.module.ImageRotatorTest01;
import com.runimg.api.imagedownload.test.module.ImageSharpenTest01;
import com.runimg.api.imagedownload.test.module.ZoomTest01;
import com.runimg.api.imagedownload.test.module.ZoomTest02;

public class FinalTest {

	
	static String HTML_VIEW_HEADER= "<!DOCTYPE html>"+
			"<html>"
			+"<head>"
			+"<meta charset=\"utf-8\">"
			+"<title>runimg test</title>"
			+"</head>"
			+"<body>";
	
	static String  HTML_VIEW_END = "</body>"+
			"</html>";
			static String SPARE_LINE = "<HR style=\"FILTER: alpha(opacity=100,finishopacity=0,style=1)\" color=#987cb9 SIZE=3>";
	
	public static void main(String[] args) {
		ImageOperator  imageOperator= new ImageOperator(); 
		UrlCreator urlCreator = new UrlCreator("123456789ABCDEF0", "0123456789ABCDEF", ImageType.TYPE_1D, 3600, imageOperator);
		urlCreator.setRecordInterval(0L, 0L);
		
		
		List<GenerallTest> tests	=getTestCase(imageOperator);
		 for(GenerallTest generallTest :tests) {
			 imageOperator.reset();
		 // 1. Process Image Settings
			 generallTest.process();
		 // 2. Get image url
			 generallTest.setResultStanza(RunimgService.getImageUrl("http://www.runimg.com/services.php/lastupdate?", urlCreator));
		 }
		String htmlContent = generatorHtml(tests);
		saveFile(htmlContent);
		show("view.html");
		// return
	}

	static List<GenerallTest> getTestCase(ImageOperator imageOperator) {
		List<GenerallTest> tests = new ArrayList<GenerallTest>();
		GenerallTest generallTest = null;
		
		generallTest  =new ZoomTest01(imageOperator); 
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ZoomTest02(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageClipperTest01(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageClipperTest02(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageClipperTest03(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageClipperTest04(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageRotatorTest01(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageSharpenTest01(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageBlurryTest01(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageBrightnessTest01(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageContrastTest01(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageFormatTest01(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageFormatTest02(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageFormatTest03(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageFormatTest04(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageFormatTest05(imageOperator);
		tests.add(generallTest);
		// ---------------------------------------------------------------------------
		generallTest  =new ImageFormatTest06(imageOperator);
		tests.add(generallTest);
		
		return tests;
	}
	static String generatorHtml(List<GenerallTest> generallTests)
	{
		String ss = "";
		// 1. Add Header
		ss+= HTML_VIEW_HEADER;
		for(GenerallTest generallTest:generallTests) {
		UpdateStanza updateStanza = generallTest.getResultStanza();
		if(updateStanza.getStatus()!= 200) {
		System.err.println("The result status is not 200");
		continue;
		}
		ss+=  "<h2>";
		ss+=  generallTest.getTestName();
		ss+=  "</h2>";
		ss+=  "<img src=\"";
		ss+=  updateStanza.getRecords().get(0).getUrl();
		ss+=  "\" alt=\"Failure to Load Image\" />\n";
		ss+=  SPARE_LINE;
		}
		ss+=  HTML_VIEW_END;
		return ss;
	}
	
	private  static void saveFile(String str)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File("view.html"));
			fos.write(str.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void show(String url) { 
		   try { 
		    java.net.URI uri = java.net.URI.create(url); 
		    // 获取当前系统桌面扩展 
		    java.awt.Desktop dp = java.awt.Desktop.getDesktop(); 
		    // 判断系统桌面是否支持要执行的功能 
		    if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) { 
		     //File file = new File("D:\\aa.txt"); 
		     //dp.edit(file);// 　编辑文件 
		      dp.browse(uri);// 获取系统默认浏览器打开链接 
		     // dp.open(file);// 用默认方式打开文件 
		     // dp.print(file);// 用打印机打印文件 
		    } 
		   } catch (java.lang.NullPointerException e) { 
		    // 此为uri为空时抛出异常 
		    e.printStackTrace(); 
		   } catch (java.io.IOException e) { 
		    // 此为无法获取系统默认浏览器 
		    e.printStackTrace(); 
		   } 
		  }
}
