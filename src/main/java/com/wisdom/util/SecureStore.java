package com.wisdom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;

public class SecureStore {
	
	private static Logger log = LoggerFactory.getLogger(SecureStore.class);
	/**
	 * DES encoder
	 */
	public static byte[] desEncode(String key, byte[] src) throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		DESKeySpec keySpec = new DESKeySpec(key.getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey sKey = keyFactory.generateSecret(keySpec);
		
		Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
		cipher.init(Cipher.ENCRYPT_MODE, sKey);
		byte[] dst = cipher.doFinal(src);
		
		return dst;
	}
	
	/**
	 * DES decoder
	 */
	public static byte[] desDecode(String key, byte[] src) throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		DESKeySpec keySpec = new DESKeySpec(key.getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey sKey = keyFactory.generateSecret(keySpec);
		
		Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
		cipher.init(Cipher.DECRYPT_MODE, sKey);
		byte[] dst = cipher.doFinal(src);
		
		return dst;
	}
	
	/**
	 * DES encoder
	 */
	@SuppressWarnings("restriction")
	public static String desEncode(String key, String src) throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		return new sun.misc.BASE64Encoder().encode(desEncode(key, src.getBytes("UTF8")));
	}
	
	/**
	 * DES decoder
	 */
	@SuppressWarnings("restriction")
	public static String desDecode(String key, String src) throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		return new String(desDecode(key, new sun.misc.BASE64Decoder().decodeBuffer(src)), "UTF8");
	}
	
	/**
	 * Base64 encoder
	 */
	public static String b64Encode(byte[] src) {
		return new String(Base64.encodeBase64(src));
	}
	
	/**
	 * Base64 decoder
	 */
	public static byte[] b64Decode(String src) {
		return Base64.decodeBase64(src.getBytes());
	}
	
	 /**
	  * 根据输入流获得文件MD5
	  * @param inputStream
	  * @return
	  */
	public static String md5Encode(InputStream inputStream) throws NoSuchAlgorithmException{
		try{
			long sizeRet = 0l;
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[4*1024];
			int numRead = 0;
			
			while ((numRead = inputStream.read(buffer)) > 0) {
				sizeRet += numRead;
				mdTemp.update(buffer, 0, numRead);
			}
						
			byte[] dst = mdTemp.digest();
			   
			StringBuilder sb = new StringBuilder();
			   
			for(int i = 0; i < dst.length; i++) {
				sb.append(Integer.toHexString((dst[i] >> 4) & 0xf));
				sb.append(Integer.toHexString(dst[i] & 0xf));
			}
			sb.append(String.format("%016x", sizeRet).substring(8)); //在MD5值后面附加文件大小后四个字节的HexString;
						
			return sb.toString()+"|"+sizeRet;
		} catch (Exception e) {
			log.error("md5Encode Exception:{}",e);
			return null;
		}
	}
	
	/**
	 * MD5 encoder
	 */
	public static String md5Encode(byte[] src) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] dst = md.digest(src);
		
		for (int i = 0; i < dst.length; i++) {
			sb.append(Integer.toHexString((dst[i] >> 4) & 0xf));
			sb.append(Integer.toHexString(dst[i] & 0xf));
		}
		
		return sb.toString();
	}
	
	public static String md5Encode(String src) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] dst = md.digest(Utf8.encode(src));
		return new String(Hex.encode(dst));
	}
	
	/**
	 * MD5 encoder
	 */
	public static String md5Encode(File file) throws NoSuchAlgorithmException, IOException {
		StringBuilder sb = new StringBuilder();
		MessageDigest md = MessageDigest.getInstance("MD5");
		InputStream is = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int numRead;
		
		try {
			do {
				if ((numRead = is.read(buffer)) > 0) {
					md.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
			
			byte[] dst = md.digest();
			
			for (int i = 0; i < dst.length; i++) {
				sb.append(Integer.toHexString((dst[i] >> 4) & 0xf));
				sb.append(Integer.toHexString(dst[i] & 0xf));
			}
		} finally {
			IOUtils.closeQuietly(is);
		}
		
		return sb.toString();
	}
	
	/**
	 * Password generator.
	 */
	public static String generatePassword(int lenght) {
		return RandomStringUtils.randomAlphanumeric(lenght);
	}
}
