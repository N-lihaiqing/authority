package com.wisdom.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathUtils {

	private static Logger log = LoggerFactory.getLogger(PathUtils.class);
	
	/**
	 * Get node name.
	 */
	public static String getName(String path) {
		log.debug("getName({})", path);
		String ret = path.substring(path.lastIndexOf('/') + 1);
		log.debug("getName: {}", ret);
		return ret;
	}
}
