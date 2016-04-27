package com.runimg.api.imagedownload.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import com.runimg.api.imagedownload.module.ImageType;
import com.runimg.api.imagedownload.util.Base64Util;
import com.runimg.api.imagedownload.util.Log;

public class UrlCreator {

	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";

	private String tokenId;
	private String secretKey;
	private ImageType imageType;
	private ImageOperator imageOperator = null;
	private Long timestamp;
	private int expired = 3600;
	private String version = "1.0";

	private Long startTime;
	private Long endTime;

	public UrlCreator(String tokenId, String secretKey, ImageType imageType,
			int expired, ImageOperator imageOperator) {
		super();
		this.tokenId = tokenId;
		this.secretKey = secretKey;
		this.imageType = imageType;
		this.expired = expired;
		this.imageOperator = imageOperator;

		timestamp = new Date().getTime();

	}

	public final String toUrlString() {

		Map<String, Object> keyValues = new HashMap<String, Object>();
		keyValues.put("token_id", tokenId);
		keyValues.put("expired", String.valueOf(expired));

		keyValues.put("img_type", imageType.imageTypeToString());
		if (imageOperator != null) {
			keyValues.put("img_opt", imageOperator.toString());
		}
		 keyValues.put("timestamp", String.valueOf(timestamp/1000));
		 keyValues.put("version", version);
		 keyValues.put("rec_inv", recordIntervalTime());
		keyValues.put("signature", calculateSignature(keyValues, secretKey));
		return calculateParameters(keyValues);

	}

	private String calculateSignature(Map<String, Object> keyValues,
			String tokenKey) {
		String keyString = "";

		Object[] keys = keyValues.keySet().toArray();
		Arrays.sort(keys);

		for (Object object : keys) {
			keyString += object + "=" + keyValues.get(object) + "&";
		}
		if (keyString.endsWith("&")) {
			keyString = keyString.substring(0, keyString.length() - 1);
		}
		try {
			return Base64Util
					.encodeBase64(HmacSHA1Encrypt(keyString, tokenKey));
		} catch (Exception e) {
			Log.logError(e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("deprecation")
	private String calculateParameters(Map<String, Object> keyValues) {

		String keyString = "";
		Object[] keys = keyValues.keySet().toArray();
		Arrays.sort(keys);

		for (Object object : keys) {
			keyString += java.net.URLEncoder.encode((String) object)
					.replace("+", "%20").replace("*", "%2A")
					.replace("~", "%2A")
					+ "=";
			keyString += java.net.URLEncoder
					.encode(((String) keyValues.get(object)))
					.replace("+", "%20").replace("*", "%2A")
					.replace("~", "%2A")
					+ "&";

		}
		if (keyString.endsWith("&")) {
			keyString = keyString.substring(0, keyString.length() - 1);
		}
		return keyString;
	}

	public String ImageTypeToString(ImageType image_type) {
		return null;

	}

	String recordIntervalTime() {

		Map<String, Object> keyValues = new HashMap<String, Object>();
		keyValues.put("st", startTime);
		keyValues.put("et", endTime);
		JSONObject json = JSONObject.fromObject(keyValues);
		return Base64Util.encodeBase64(json.toString().getBytes());
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
	public boolean setRecordInterval(Long startTime, Long endTime) {
		  this.startTime = startTime;
		  this.endTime   = endTime;
		  return true;
		}
}
