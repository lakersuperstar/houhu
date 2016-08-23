package com.houhucun.data;

import com.houhucun.controller.vo.WeixinConfigStatic;
import com.houhucun.controller.vo.WeixinTicket;
import com.houhucun.controller.vo.WeixinToken;
import com.houhucun.util.HttpclientUtils;
import com.houhucun.util.JsonSerializeUtil;

public class WeixinStatic {
	
	
	public static WeixinConfigStatic wcsStatic = new WeixinConfigStatic();
	static {
		Thread thread = new Thread(new ExecuteRunnable());
		thread.setDaemon(true);
		thread.start();
	}
}

class ExecuteRunnable implements Runnable {
	
	
	@Override
	public void run() {
		while (true) {
			try {
				this.refreshData();
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("查询静态微信配置数据异常" + e);
			}
		}
	}
	
	private void refreshData() {
		this.refreshTokenData();
		this.refreshJsticketData();
		WeixinStatic.wcsStatic.setTicket(JsticketStaticData.jsticket);
		WeixinStatic.wcsStatic.setToken(TokenStaticData.token);
	}
	
	private void refreshTokenData() {
		if (TokenStaticData.expire_in == 0 || System.currentTimeMillis() >= TokenStaticData.expire_in) {
			String tokenStr = null;
			try {
				tokenStr = HttpclientUtils.getAccessToken();
				if (tokenStr != null && !"".equals(tokenStr)) {
					WeixinToken token = JsonSerializeUtil.parserJson2Object(tokenStr, WeixinToken.class);
					if (token != null && token.getAccess_token() != null && !"".equals(token.getAccess_token())) {
						TokenStaticData.token = token.getAccess_token();
						TokenStaticData.expire_in = System.currentTimeMillis() + 1000 * token.getExpires_in();
					}
				}
			} catch (Exception e) {
				System.out.println("获取token失败" + e);
			}
		}
	}
	
	private void refreshJsticketData() {
		if (JsticketStaticData.expire_in == 0 || System.currentTimeMillis() >= JsticketStaticData.expire_in) {
			try {
				String ticketStr = HttpclientUtils.getjsTicket(TokenStaticData.token);
				if (ticketStr != null && !"".equals(ticketStr)) {
					WeixinTicket ticket = JsonSerializeUtil.parserJson2Object(ticketStr, WeixinTicket.class);
					if (ticket != null && ticket.getTicket() != null && !"".equals(ticket.getTicket())) {
						JsticketStaticData.jsticket = ticket.getTicket();
						JsticketStaticData.expire_in = System.currentTimeMillis() + 1000 * ticket.getExpires_in();
					}
				}
			} catch (Exception e) {
				System.out.println("获取ticket失败" + e);
			}
		}
	}
}
