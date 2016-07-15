package com.houhucun.util;

import java.io.Serializable;

/**
 * 密钥串实体
 * 
 * @author songk
 *
 */
public class SecretKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4165958128365763139L;

	/**
	 * 公钥
	 */
	private String pubSecretKey;

	/**
	 * 私钥
	 */
	private String priSecretKey;

	public SecretKey() {

	}

	public SecretKey(String pubSecretKey, String priSecretKey) {
		super();
		this.pubSecretKey = pubSecretKey;
		this.priSecretKey = priSecretKey;
	}

	public String getPubSecretKey() {
		return pubSecretKey;
	}

	public void setPubSecretKey(String pubSecretKey) {
		this.pubSecretKey = pubSecretKey;
	}

	public String getPriSecretKey() {
		return priSecretKey;
	}

	public void setPriSecretKey(String priSecretKey) {
		this.priSecretKey = priSecretKey;
	}

	@Override
	public String toString() {
		return "SecretKey [pubSecretKey=" + pubSecretKey + ", priSecretKey="
				+ priSecretKey + "]";
	}

}
