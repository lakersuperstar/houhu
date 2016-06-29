package com.houhucun.util;

import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtils {

	/**
	 * 将字符流保存为图片
	 * 
	 * @param stream
	 * @param path
	 * @param savefile
	 */
	public static void saveFileFromInputStream(InputStream stream, String path,
			String savefile) {
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(path + "/" + savefile);
			byte[] buffer = new byte[1024 * 1024 * 5];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
		} catch (Exception e) {
			throw new RuntimeException("保存文件异常,", e);
		} finally {
			try {
				if (fs != null)
					fs.close();
				if (stream != null)
					stream.close();
			} catch (Exception e) {
				throw new RuntimeException("保存文件,关闭文件异常", e);
			}
		}

	}
}
