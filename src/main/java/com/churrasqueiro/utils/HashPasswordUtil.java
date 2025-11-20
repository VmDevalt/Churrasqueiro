package com.churrasqueiro.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class HashPasswordUtil {
	
	private static final String HASH_ALGORITHM = "SHA-256";
	
	public static String hashPassword(String password) {
		if(password == null || password.isEmpty()) {
			return null;
		}

        password = password.strip();

        try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(hash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("tipo de hash não encontrado:" + HASH_ALGORITHM);
			e.printStackTrace();
			return null;
		}
	}
	
	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
