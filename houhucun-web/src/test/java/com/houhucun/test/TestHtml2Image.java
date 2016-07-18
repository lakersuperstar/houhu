package com.houhucun.test;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

public class TestHtml2Image {

	public static void main(String[] args) {
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
//		String htmlstr = "<table width='654' cellpadding='0' cellspacing='0' bordercolor='#FFFFFF'><tr><td><img src='http://qr.api.cli.im/qr?data=http%253A%252F%252Fwww.baidu.com&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=bb4953cb0bd73a8390e22623a1cda668'/></td><td><img src='http://print.cli.im/images/72dpi.png'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr><tr><td><img src='http://www.apkfather.com/yhqserver/images/mdl.jpg'/></td></tr></table>";
		String htmlstr2 = "<div><img src='http://print.cli.im/images/72dpi.png' /></div>";
		imageGenerator.loadHtml(htmlstr2);
		BufferedImage bufferedImage = imageGenerator.getBufferedImage();
//		imageGenerator.saveAsImage("/Users/songk/Desktop/a.png");
//		imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
	}
}
