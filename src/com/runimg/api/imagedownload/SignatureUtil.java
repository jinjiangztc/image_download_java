package com.runimg.api.imagedownload;

import java.lang.reflect.Method;
import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

public class SignatureUtil {

	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";

	@SuppressWarnings("deprecation")
	private static String getRequestUrl(String expired, String img_opt,
			String img_type, String timestamp, String token_id, String version,
			String token_key) {
		String[] key = { "expired", "img_opt", "img_type", "timestamp",
				"token_id", "version" };
		String[] values = { expired, img_opt, img_type, timestamp, token_id,
				version };

		String raw_string = "";
		String parameter = "";
		for (int i = 0; i < key.length; i++) {
			raw_string += key[i] + "=";

			if (key[i].equals("img_opt")) {
				raw_string += encodeBase64(values[i].getBytes()) + "&";
			} else {
				raw_string += values[i] + "&";
			}

			parameter += URLEncoder.encode(key[i]).replace("+", "%20")
					.replace("*", "%2A").replace("~", "%2A")
					+ "=";

			if (key[i].equals("img_opt")) {
				parameter += URLEncoder
						.encode(encodeBase64(values[i].getBytes()))
						.replace("+", "%20").replace("*", "%2A")
						.replace("~", "%2A")
						+ "&";
			} else {
				parameter += URLEncoder.encode(values[i]).replace("+", "%20")
						.replace("*", "%2A").replace("~", "%2A")
						+ "&";
			}

		}
		if (raw_string.endsWith("&")) {
			raw_string = raw_string.substring(0, raw_string.length() - 1);
		}
		// System.err.println(raw_string);
		String signature = encodeBase64(HmacSHA1Encrypt(raw_string, token_key));
		// System.err.println(signature);
		parameter += URLEncoder.encode("signature").replace("+", "%20")
				.replace("*", "%2A").replace("%7E", "~")
				+ "=";

		parameter += URLEncoder.encode(signature).replace("+", "%20")
				.replace("*", "%2A").replace("%7E", "~");
		return "http://www.runimg.com/services.php/lastupdate?" + parameter;
	}

	private static String encodeBase64(byte[] input) {
		try {
			@SuppressWarnings("rawtypes")
			Class clazz = Class
					.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			@SuppressWarnings("unchecked")
			Method mainMethod = clazz.getMethod("encode", byte[].class);
			mainMethod.setAccessible(true);
			Object retObj = mainMethod.invoke(null, new Object[] { input });
			return (String) retObj;
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage(), exception);
		}
	}

	private static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) {
		try {

			byte[] data = encryptKey.getBytes(ENCODING);
			// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
			SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
			// 生成一个指定 Mac 算法 的 Mac 对象
			Mac mac = Mac.getInstance(MAC_NAME);
			// 用给定密钥初始化 Mac 对象
			mac.init(secretKey);

			byte[] text = encryptText.getBytes(ENCODING);
			// 完成 Mac 操作
			return mac.doFinal(text);
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage(), exception);

		}
	}

	private static String getImageUrl(String html) {
		JSONObject jsonObject = JSONObject.fromObject(html);

		return ((String) jsonObject.get("url")).replaceAll("\\\\", "");
	}

	public static void downloadImage(String expired, String img_opt,
			String img_type, String timestamp, String token_id, String version,
			String token_key, String savePath, String filename) {

		String requestUrl = getRequestUrl(expired, img_opt, img_type,
				timestamp, token_id, version, token_key);
		String html = DownloadUtil.downloadHtml(requestUrl);
		String imageUrl = getImageUrl(html);
		DownloadUtil.downloadImage(imageUrl, "c:\\image\\", "51bi.gif");
	}


}
