package com.wisdom.util;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtil {
	private static Logger log = LoggerFactory.getLogger(FormatUtil.class);
	private static final String[] UNITS = new String[] { "B", "KB", "MB", "GB", "TB", "PB", "EB" };
	
	/**
	 * Detect if the current browser is a mobile one
	 */
	public static final boolean isMobile(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent").toLowerCase();
		return userAgent.contains("android") || userAgent.contains("iphone") || userAgent.contains("ipad")
				|| userAgent.contains("blackberry");
	}
	
	/**
	 * Format the document size for human readers
	 */
	public static String formatSize(long bytes) {
		for (int i = 6; i > 0; i--) {
			double step = Math.pow(1024, i);
			if (bytes > step)
				return String.format(Locale.ROOT, "%3.1f %s", bytes / step, UNITS[i]);
		}
		
		return Long.toString(bytes) + " " + UNITS[0];
	}
	
	/**
	 * Parse human-readable sizes
	 */
	public static long parseSize(String text) {
	    double d = Double.parseDouble(text.replaceAll("[GMK]B$", ""));
	    long l = Math.round(d * 1024 * 1024 * 1024L);
	    
	    switch (text.charAt(Math.max(0, text.length() - 2))) {
	        default:  l /= 1024;
	        case 'K': l /= 1024;
	        case 'M': l /= 1024;
	        case 'G': return l;
	    }
	}
	
	/**
	 * Format time for human readers
	 */
	public static String formatTime(long time) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
		String str = df.format(time);
		return str;
	}
	
	/**
	 * Format time interval for humans
	 */
	public static String formatSeconds(long time) {
		long hours, minutes, seconds;
		time = time / 1000;
		hours = time / 3600;
		time = time - (hours * 3600);
		minutes = time / 60;
		time = time - (minutes * 60);
		seconds = time;
		return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":"
				+ (seconds < 10 ? "0" + seconds : seconds);
	}
	
	/**
	 * Format time interval for humans
	 */
	public static String formatMiliSeconds(long time) {
		long hours, minutes, seconds, mseconds;
		mseconds = time % 1000;
		time = time / 1000;
		hours = time / 3600;
		time = time - (hours * 3600);
		minutes = time / 60;
		time = time - (minutes * 60);
		seconds = time;
		return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":"
				+ (seconds < 10 ? "0" + seconds : seconds) + "."
				+ (mseconds < 10 ? "00" + mseconds : (mseconds < 100 ? "0" + mseconds : mseconds));
	}
	
	/**
	 * Format calendar date
	 */
	public static String formatDate(Calendar cal) {
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(cal.getTime());
	}
	
	/**
	 * Format string array
	 */
	public static String formatArray(String[] values) {
		if (values != null) {
			if (values.length == 1) {
				return values[0];
			} else {
				return ArrayUtils.toString(values);
			}
		} else {
			return "NULL";
		}
	}
	
	/**
	 * Format object
	 */
	public static String formatObject(Object value) {
		if (value != null) {
			if (value instanceof Object[]) {
				return ArrayUtils.toString(value);
			} else {
				return value.toString();
			}
		} else {
			return "NULL";
		}
	}
	
	/**
	 * Escape html tags
	 */
	public static String escapeHtml(String str) {
		return str.replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br/>");
	}
	
	/**
	 * Split by separator
	 */
	public static String splitBySeparator(String str) {
		String ret = str.replace(File.pathSeparator, "<br/>");
		ret = ret.replace(",", "<br/>");
		return ret;
	}
	
	/**
	 * Sanitize HTML
	 * 
	 * @see http://www.rgagnon.com/javadetails/java-0627.html
	 */
	public static String sanitizeInput(String string) {
		return string
				.replaceAll("(?i)<script.*?>.*?</script.*?>", "") // case 1 - Open and close
				.replaceAll("(?i)<script.*?/>", "") // case 1 - Open / close
				.replaceAll("(?i)<script.*?>", "") // case 1 - Open and !close
				.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2 - Open and close
				.replaceAll("(?i)<.*?javascript:.*?/>", "") // case 2 - Open / close
				.replaceAll("(?i)<.*?javascript:.*?>", "") // case 2 - Open and !close
				.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "") // case 3 - Open and close
				.replaceAll("(?i)<.*?\\s+on.*?/>", "") // case 3 - Open / close
				.replaceAll("(?i)<.*?\\s+on.*?>", ""); // case 3 - Open and !close
	}
	
	
	/**
	 * Check for valid UTF8
	 */
	public static boolean validUTF8(byte[] input) {
		CharsetDecoder cd = Charset.availableCharsets().get("UTF-8").newDecoder();
		
		try {
			cd.decode(ByteBuffer.wrap(input));
		} catch (CharacterCodingException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Fix UTF-8 NULL
	 */
	public static byte[] fixUTF8(byte[] input) {
		byte[] fixed = new byte[input.length];
		
		for (int i = 0; i < input.length; i++) {
			if (input[i] == 0x00) {
				fixed[i] = 0x20;
			} else {
				fixed[i] = input[i];
			}
		}
		
		return fixed;
	}
	
	/**
	 * Fix UTF-8 NULL
	 */
	public static String fixUTF8(String input) {
		return input.replace('\u0000', '\u0020');
	}
	
	/**
	 * Trim Unicode surrogate characters
	 * 
	 * http://en.wikipedia.org/wiki/Mapping_of_Unicode_characters#Surrogates
	 */
	public static String trimUnicodeSurrogates(String text) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			
			if (!Character.isHighSurrogate(ch) && !Character.isLowSurrogate(ch)) {
				sb.append(ch);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Check if the string is a valid UUID.
	 */
	public static boolean isValidUUID(String str) {
		try {
			UUID.fromString(str);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	/**
	 * Get configkey by path
	 */
	//设为默认根目录可定位到根节点
	public static String changeString(String fldId)
	{
		String cfgfldId=fldId.replace("/","");
		return cfgfldId=cfgfldId.replace(":",".");
	}
}
