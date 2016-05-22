package com.runimg.api.imagedownload.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.runimg.api.imagedownload.module.Record;
import com.runimg.api.imagedownload.module.UpdateStanza;

/**
 * 
 * @author jinjiangztc@gmail.com
 */
public class RunimgService {

	/**
	 * 获取图片地址列表
	 * 
	 * @param urlBase
	 *            基础url
	 * @param urlCreator
	 *            用户生成的UrlCreator对象
	 * @return
	 */
	public static UpdateStanza getImageUrl(String urlBase, UrlCreator urlCreator) {

		String server_url = urlBase + urlCreator.toUrlString();
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

	/**
	 * 根据图片地址下载图片
	 * 
	 * @param imageUrl
	 *            下载图片的url地址
	 * @param savePath
	 *            保存路径
	 * @param filename
	 *            保存文件名
	 * @return 是否下载成功
	 */
	public static boolean getImageByUrl(String imageUrl, String savePath,
			String filename) {
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
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\"
					+ filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
			return true;
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage(), exception);
		}

	}

	private static UpdateStanza parseStanza(String html) {

		UpdateStanza result = null;
		JSONObject jsonObject = JSONObject.fromObject(html);
		JSONArray jsonRecord = jsonObject.getJSONArray("records");
		List<Record> records = new ArrayList<Record>();

		result = new UpdateStanza();
		result.setStatus(Integer.parseInt(jsonObject.getString("status")));
		for (Object object : jsonRecord) {
			Record record = (Record) JSONObject.toBean((JSONObject) object,
					Record.class);
			record.setUrl(record.getUrl().replaceAll("\\\\", ""));
			records.add(record);
		}
		result.setRecords(records);

		return result;
	}

}
