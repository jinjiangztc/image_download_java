package com.runimg.api.imagedownload.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.runimg.api.imagedownload.module.ImageType;
import com.runimg.api.imagedownload.util.Base64Util;
import com.runimg.api.imagedownload.util.Log;

public class UrlCreator {

	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";

	String tokenId;
	String secretKey;
	ImageType imageType;
	ImageOperator imageOperator = null;
	String timestamp;
	int expired = 3600;
	String version = "1.0";

	String startTime;
	String endTime;

	public UrlCreator(String tokenId, String secretKey, ImageType imageType,
			int expired, ImageOperator imageOperator) {
		super();
		this.tokenId = tokenId;
		this.secretKey = secretKey;
		this.imageType = imageType;
		this.expired = expired;
		this.imageOperator = imageOperator;
		timestamp = String.valueOf(new Date().getTime());

	}

	public final String toUrlString() {

		Map<String, String> keyValues = new HashMap<String, String>();
		keyValues.put("token_id", tokenId);
		keyValues.put("expired", String.valueOf(expired));

		keyValues.put("img_type", imageType.imageTypeToString());
		if (imageOperator != null) {
			keyValues.put("img_opt", imageOperator.toString());
		}
		keyValues.put("timestamp", String.valueOf(new Date().getTime()));
		keyValues.put("version", version);
		keyValues.put("rec_inv", recordIntervalTime());
		keyValues.put("ignature", calculateSignature(keyValues, secretKey));
		return calculateParameters(keyValues);

	}

	private String calculateSignature(Map<String, String> keyValues,
			String token_key) {
		String keyString = "";

		for (String key : keyValues.keySet()) {
			keyString += key + "=" + keyValues.get(key) + "&";
		}
		if (keyString.endsWith("&")) {
			keyString = keyString.substring(0, keyString.length() - 1);
		}

		try {
			return Base64Util.encodeBase64(HmacSHA1Encrypt(keyString, token_key));
		} catch (Exception e) {
			Log.logError(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	private String calculateParameters(Map<String, String> keyValues) {

		String keyString = "";
		for (String key : keyValues.keySet()) {
			keyString += java.net.URLEncoder.encode(key).replace("+", "%20")
					.replace("*", "%2A").replace("~", "%2A")
					+ "=";

			if (key.equals("img_opt")) {
				keyString += java.net.URLEncoder
						.encode(Base64Util.encodeBase64(keyValues.get(key).getBytes()))
						.replace("+", "%20").replace("*", "%2A")
						.replace("~", "%2A")
						+ "&";
			} else {
				keyString += java.net.URLEncoder.encode(keyValues.get(key))
						.replace("+", "%20").replace("*", "%2A")
						.replace("~", "%2A")
						+ "&";
			}

		}

		return keyString;
	}

	String ImageTypeToString(ImageType image_type) {
		return null;

	}

	String recordIntervalTime() {
		// Json::Value value;
		// Json::FastWriter fw;
		// value[JSON_RECORD_START_INTERVAL] = start_time_;
		// value[JSON_RECORD_END_INTERVAL] = end_time_;
		// return runimg::Base64::Encode(fw.write(value));

		return null;
	}

	

	public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) {
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
		} catch (Exception message) {
			Log.logError(message.getMessage());
		}
		return null;
	}
}
