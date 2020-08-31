package com.payn.ink.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: payn
 * @date: 2020/8/18 21:12
 */
public class MD5Util {

	/**
	 * MD5 加密
	 */
	public static String MD5encode(String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		}
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ignored) {
		}
		byte[] encode = messageDigest.digest(source.getBytes());
		StringBuilder hexString = new StringBuilder();
		for (byte anEncode : encode) {
			String hex = Integer.toHexString(0xff & anEncode);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
