package com.houhucun.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;

public class TestOSS {

	public static void main(String[] args) throws FileNotFoundException {
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "http://oss-cn-beijing.aliyuncs.com";
		// accessKey请登录https://ak-console.aliyun.com/#/查看
		String accessKeyId = "QEIe7eCeG4uAWEKP";
		String accessKeySecret = "Af8qrbfucoq0iF2emdHmjqV6WFbccQ";
		// 创建OSSClient实例
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 使用访问OSS
		InputStream is = new FileInputStream("E:\\123.png");
		client.putObject("sk-houhu", "2365.jpg", is);
		// 关闭client
		client.shutdown();

	}

}
