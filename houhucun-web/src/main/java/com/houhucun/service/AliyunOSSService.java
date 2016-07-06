package com.houhucun.service;

import java.io.InputStream;

public interface AliyunOSSService {

	boolean uploadContentImg(InputStream is,String name);
	boolean uploadFaceImg(InputStream is,String name);
}
