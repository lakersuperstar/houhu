package com.houhucun.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpclientUtils {
	
	
	private static String accessTokenURL = "https://api.weixin.qq.com/cgi-bin/token";
	private static String accessGrant_type = "client_credential";
	public static String appid = "wx3fae96e9c1d3cca7";
	private static String appSecret = "48f96119e5b2bb3465a9962578b78ad1";
	private static String accessTokenRequestURL = accessTokenURL + "?grant_type=" + accessGrant_type + "&appid=" + appid + "&secret=" + appSecret;
	private static String js_ticketurl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	public static String getAccessToken() throws ClientProtocolException, IOException {
		try {
			HttpPost post = new HttpPost(accessTokenRequestURL);
			HttpClientBuilder hcb = HttpClientBuilder.create();
			CloseableHttpResponse response = hcb.build().execute(post);
			HttpEntity entity1 = response.getEntity();
			String body = EntityUtils.toString(entity1);
			return body;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public static String getjsTicket(String accesstoken) {
		try {
			String url = js_ticketurl.replace("ACCESS_TOKEN", accesstoken);
			HttpPost post = new HttpPost(url);
			HttpClientBuilder hcb = HttpClientBuilder.create();
			CloseableHttpResponse response = hcb.build().execute(post);
			HttpEntity entity1 = response.getEntity();
			String body = EntityUtils.toString(entity1);
			return body;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		System.out.println(getAccessToken());
	}
}
