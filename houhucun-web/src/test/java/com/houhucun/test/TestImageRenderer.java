package com.houhucun.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.fit.cssbox.demo.ImageRenderer;

public class TestImageRenderer {

	public static void main(String[] args) throws Exception {

		ImageRenderer render = new ImageRenderer();
		System.out.println("kaishi");
		String url = "http://worldwide.espacenet.com/publicationDetails/originalDocument?CC=AU&NR=2014200109A1&KC=A1&FT=D&ND=3&date=20140821&DB=EPODOC&locale=en_EP";
		FileOutputStream out = new FileOutputStream(new File("D:"
				+ File.separator + "html.png"));
		render.renderURL(url, out, ImageRenderer.Type.PNG);
		System.out.println("OK");

	}

}
