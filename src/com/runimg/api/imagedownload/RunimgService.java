package com.runimg.api.imagedownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

import com.runimg.api.imagedownload.module.UpdateStanza;

public class RunimgService {

	private static final String baseUrl = "http://www.runimg.com/services.php/lastupdate?";

	public static UpdateStanza getImageUrl(String urlBase, UrlCreator urlCreator) {

		String server_url = urlBase + urlCreator.toString();
		// 构造URL

		try {
			URL url = new URL(server_url);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			StringBuffer html = new StringBuffer();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null) {
				html.append(temp).append("\n");
			}
			br.close();
			isr.close();
			return parseStanza(html.toString());
			// 开始读取
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage(), exception);
		}

	}

	public static boolean getImageByUrl(String imageUrl) {
		try {

			// 构造URL
			URL url = new URL(imageUrl);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 10K的数据缓冲
			byte[] bs = new byte[10 * 1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			// // File sf = new File(savePath);
			// if (!sf.exists()) {
			// sf.mkdirs();
			// }
			// OutputStream os = new FileOutputStream(sf.getPath() + "\\"
			// + filename);
			// // 开始读取
			// while ((len = is.read(bs)) != -1) {
			// os.write(bs, 0, len);
			// }
			// // 完毕，关闭所有链接
			// os.close();
			// is.close();
			return true;
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage(), exception);
		}

	}

	private static UpdateStanza parseStanza(String html) {
		JSONObject jsonObject = JSONObject.fromObject(html);

		UpdateStanza updateStanza = (UpdateStanza) JSONObject.toBean(
				jsonObject, UpdateStanza.class);
		return updateStanza;
	}

}
