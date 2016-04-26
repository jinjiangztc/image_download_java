package com.runimg.api.imagedownload.util;

import java.lang.reflect.Method;

public class Base64Util {

	@SuppressWarnings("unchecked")
	public static String encodeBase64(byte[] input) {

		try {

			@SuppressWarnings("rawtypes")
			Class clazz = Class
					.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method mainMethod = clazz.getMethod("encode", byte[].class);
			mainMethod.setAccessible(true);
			Object retObj = mainMethod.invoke(null, new Object[] { input });
			return (String) retObj;
		} catch (Exception message) {
			Log.logError(message.getMessage());
			throw new RuntimeException(message.getMessage(), message);
		}
	}
}
