package com.houhucun.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.houhucun.util.FileUtils;

public class TestFileUtil {

	public static void main(String[] args) throws FileNotFoundException {

		InputStream is = new FileInputStream("/Users/songk/Desktop/22.jpg");
		FileUtils.saveFileFromInputStream(is, "/Users/songk/Desktop", "1.jpg");
	}

}
