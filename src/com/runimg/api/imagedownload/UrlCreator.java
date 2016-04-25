package com.runimg.api.imagedownload;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.runimg.api.imagedownload.module.ImageType;

public class UrlCreator {

	String tokenId;
	String secretKey;
	ImageType imageType;
	ImageOperator imageOperator = null;
	String timestamp;
	int expired = 3600;
	String version = "1.0";
	Map<String, String> keyValues = new HashMap<String, String>();

	public UrlCreator(String tokenId, String secretKey, ImageType imageType,
			int expired, ImageOperator imageOperator) {
		super();
		this.tokenId = tokenId;
		this.secretKey = secretKey;
		this.imageType = imageType;
		this.expired = expired;
		this.imageOperator = imageOperator;
		timestamp = String.valueOf(new Date().getTime());

		keyValues.put("expired", String.valueOf(expired));
		keyValues.put("img_opt", imageOperator.toString());
		keyValues.put("img_type", imageType.toString());
		keyValues.put("timestamp", timestamp);
		keyValues.put("token_id", tokenId);
		keyValues.put("version", version);

	}

	public final String toUrlString() {
		return null;

	}

	private String CalculateSignature(Map<String, String> key_values,
			String token_key) {
		return null;
	}

	private String CalculateParameters(Map<String, String> key_values) {
		return null;
	}

	String ImageTypeToString(ImageType image_type) {
		return null;

	}
}
