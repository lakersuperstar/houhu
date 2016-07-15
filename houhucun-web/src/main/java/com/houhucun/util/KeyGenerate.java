package com.houhucun.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public abstract class KeyGenerate {
	
	/**
	 * 得到公钥
	 * 
	 * @param keyStr
	 * @return
	 */
	public static Key getPublicKey(String keyStr) {
		try {
			// 拿到keyBytes
			byte[] keyBytes = getKeyBytes(keyStr);
			// 取得公钥，公钥用X509
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(SecurityContants.RSA);
			Key key = keyFactory.generatePublic(x509KeySpec);
			return key;
		} catch (Exception e) {
			throw new RuntimeException("从密钥串生成公钥失败：", e);
		}
	}
	
	/**
	 * 得到私钥
	 * 
	 * @param keyStr
	 * @return
	 */
	public static Key getPrivateKey(String keyStr) {
		try {
			byte[] keyBytes = getKeyBytes(keyStr);
			// 私钥用pkcs8
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(SecurityContants.RSA);
			Key key = keyFactory.generatePrivate(pkcs8KeySpec);
			return key;
		} catch (Exception e) {
			throw new RuntimeException("从密钥串生成私钥失败：", e);
		}
	}
	
	/**
	 * 获取到字符串密钥对
	 * 
	 * @param secureSeed
	 * @param keysize
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static SecretKey getSecretKey(String secureSeed, int keysize) throws UnsupportedEncodingException {
		KeyPair keyPair = getKey(secureSeed, keysize);
		Key pubKey = keyPair.getPublic();
		Key priKey = keyPair.getPrivate();
		SecretKey secretKey = new SecretKey(getKeyString(pubKey), getKeyString(priKey));
		return secretKey;
	}
	
	/**
	 * 生成密钥对Key对象
	 * 
	 * @param secureSeed
	 * @param keysize
	 * @return
	 */
	public static KeyPair getKey(String secureSeed, int keysize) {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			SecureRandom secrand = new SecureRandom(secureSeed.getBytes());// 初始化随机产生器
			keyGen.initialize(keysize, secrand);
			KeyPair keys = keyGen.genKeyPair();
			return keys;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("生成密钥对失败，请重新生成：", e);
		}
	}
	
	/**
	 * 得到密钥字符串（经过base64编码）
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getKeyString(Key key) throws UnsupportedEncodingException {
		byte[] keyBytes = key.getEncoded();
		byte[] keyBytesBase64 = Base64.encodeBase64(keyBytes);
		return new String(keyBytesBase64, SecurityContants.SECURITY_CHAR_SET);
	}
	
	/**
	 * 将密钥串转换为byte数组
	 * 
	 * @param keyString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] getKeyBytes(String keyString) throws UnsupportedEncodingException {
		return Base64.decodeBase64(keyString.getBytes(SecurityContants.SECURITY_CHAR_SET));
	}
}
