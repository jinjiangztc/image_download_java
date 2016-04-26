package com.runimg.api.imagedownload.util;

import org.apache.log4j.Logger;

public class Log {

	static Logger log = Logger.getLogger(Log.class.getName());

	public static void logError(String message) {
		log.error(message);
	}

	public static void logInfo(String message) {
		log.info(message);
	}

}
