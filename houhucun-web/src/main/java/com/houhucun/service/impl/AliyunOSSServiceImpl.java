package com.houhucun.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.houhucun.service.AliyunOSSService;

@Service("aliyunOSSService")
public class AliyunOSSServiceImpl implements AliyunOSSService,InitializingBean{

	@Value("#{configProperties['endpoint']}")
	private String endpoint;
	@Value("#{configProperties['accessKeyId']}")
	private String accessKeyId;
	@Value("#{configProperties['accessKeySecret']}")
	private String accessKeySecret;
	@Value("#{configProperties['content-bucket']}")
	private String contentbucket;
	@Value("#{configProperties['faceimg-bucket']}")
	private String faceimgbucket;
	
	/**
	 * ossclient 
	 */
	private OSSClient client;
	@Override
	public boolean uploadContentImg(InputStream is, String name) {
		try{
			this.getClient().putObject(contentbucket, name, is);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean uploadFaceImg(InputStream is, String name) {
		try{
			this.getClient().putObject(faceimgbucket, name, is);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.initOSSClient();
		
	}
	
	private void initOSSClient(){
		// 创建ClientConfiguration实例
		ClientConfiguration conf = new ClientConfiguration();
		// 设置OSSClient使用的最大连接数，默认1024
		conf.setMaxConnections(1024);
		// 设置请求超时时间，默认50秒
		conf.setSocketTimeout(10000);
		// 设置失败请求重试次数，默认3次
		conf.setMaxErrorRetry(0);
		this.client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getContentbucket() {
		return contentbucket;
	}

	public void setContentbucket(String contentbucket) {
		this.contentbucket = contentbucket;
	}

	public String getFaceimgbucket() {
		return faceimgbucket;
	}

	public void setFaceimgbucket(String faceimgbucket) {
		this.faceimgbucket = faceimgbucket;
	}

	public OSSClient getClient() {
		if (this.client != null) {
			return client;
		} else {
			synchronized (this.client) {
				if(this.client != null){
					this.initOSSClient();
				}
			}
			return this.client;
		}
	}

	public void setClient(OSSClient client) {
		this.client = client;
	}
	
	

}
