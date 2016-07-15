package com.houhucun.util;

import java.security.Key;

import javax.crypto.Cipher;

import org.apache.commons.lang.ArrayUtils;

public class SecurityRSAUtils {

	/**
	 * 最大的加密字节数
	 */
	private static final int MAX_ENCRYPT_BLOCK = 53;

	/**
	 * 分段加密解密的分隔符
	 */
	private static final String TAG = " ";

	/**
	 * 公钥加密算法
	 * 
	 * @param source
	 *            要加密内容
	 * @param keyStr
	 *            密钥串,需要用UTF-8进行编码
	 * @return String 加密过后的内容
	 */
	public static String encrypt(String source, String keyStr) {
		try {
			return encrypt(source, KeyGenerate.getPublicKey(keyStr));
		} catch (Exception e) {
			throw new RuntimeException("加密失败：", e);
		}

	}

	/**
	 * 私钥解密算法
	 * 
	 * @param source
	 *            ： 密文
	 * @param keyStr
	 *            密钥Key,需要用UTF-8进行编码
	 * @return
	 */
	public static String decrypt(String source, String keyStr) {
		try {
			return decrypt(source, KeyGenerate.getPrivateKey(keyStr));
		} catch (Exception e) {
			throw new RuntimeException("解密失败：", e);
		}
	}

	/**
	 * 加密方法
	 * 
	 * @param source
	 *            ： 要加密的数据
	 * @param secretkey
	 *            ： 加密Key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String source, Key secretkey) throws Exception {
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(SecurityContants.RSA, "SunJCE");
		cipher.init(Cipher.ENCRYPT_MODE, secretkey);
		byte[] b = source.getBytes(SecurityContants.SECURITY_CHAR_SET);
		StringBuffer sb = new StringBuffer();
		/** 执行加密操作 */
		for (int i = 0; i < b.length; i += MAX_ENCRYPT_BLOCK) {
			// 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
			byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(b, i, i
					+ MAX_ENCRYPT_BLOCK));
			sb.append(ByteConvertUtils.parseByte2HexStr(doFinal));
			sb.append(TAG);
		}
		return sb.toString();
	}

	/**
	 * 解密算法
	 * 
	 * @param source
	 *            ：密文
	 * @param secretkey
	 *            ：解密Key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String source, Key secretkey) throws Exception {
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(SecurityContants.RSA, "SunJCE");
		cipher.init(Cipher.DECRYPT_MODE, secretkey);
		/** 执行解密操作 */
		StringBuilder sb = new StringBuilder();
		String[] strs = source.split(TAG);
		for (String temp : strs) {
			byte[] bytesTemp = ByteConvertUtils.parseHexStr2Byte(temp);
			byte[] doFinal = cipher.doFinal(bytesTemp);
			sb.append(new String(doFinal, SecurityContants.SECURITY_CHAR_SET));
		}
		return sb.toString();
	}
}
