package com.dstrube.gatech;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File; 
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.QRCodeWriter;


/*
Author: David Strube
Date: 2019-08-07
Purpose: For each URL in a file, generate a QR Code

Compile:
Windows:
javac -cp bin;bin\core-3.2.1.jar; -d bin com\dstrube\gatech\QRGenerator.java
Mac:
javac -cp bin:bin/core-3.2.1.jar -d bin com/dstrube/gatech/QRGenerator.java

Run:
Windows:
java -cp bin;bin\core-3.2.1.jar; com.dstrube.gatech.QRGenerator
Mac:
java -cp bin:bin/core-3.2.1.jar com.dstrube.gatech.QRGenerator

https://zxing.github.io/zxing/apidocs/index.html
https://crunchify.com/java-simple-qr-code-generator-example/
*/

public class QRGenerator {
	private static final List<String> URLs = new ArrayList<>();
	private static final List<String> Names = new ArrayList<>();
	private static final String INFO_PATH = "in_QR.txt";
	
	public static void main(final String[] args) {
		if (!getNamesAndUrls(args)){
			return;
		}
		
		if (URLs.size() == 0 || URLs.size() != Names.size()){
			System.out.println("Something went wrong; URLs is empty or count of names (" + Names.size() 
				+ ") != count of URLs (" + URLs.size() + ")");
			return;
		}
		int i = 0;
		final HashMap<String,String> QRPairs = new HashMap<>();
		for (final String url : URLs){
			//System.out.println("url: " + url);
			QRPairs.put(url, Names.get(i));
			i++;
		}
		
		for (final String key : QRPairs.keySet()){
			//System.out.println("key " + key + " = value " + QRPairs.get(key));
			if (!generate(key, QRPairs.get(key))){
				break;
			}
		}
		System.out.println("\nDone");		
	}
	
	private static boolean getNamesAndUrls(final String[] args){
		if (args.length > 0){
			//TODO
			System.out.println("Not ready to take info path from command line");
			return false;
		}
		final File infoFile = new File(INFO_PATH);
		if (!infoFile.exists()){
			System.out.println("Info path doesn't exist: " + INFO_PATH);
			return false;
		}
		if (infoFile.isDirectory()){
			System.out.println("Info path is a directory: " + INFO_PATH);
			return false;
		}
		if (!infoFile.canRead()){
			System.out.println("Info path is not readable: " + INFO_PATH);
			return false;
		}
		try{
			final Scanner scanner = new Scanner(infoFile); 
			String urlsHeader = "";
			final String expectedUrlsHeader = "URLs:";
			final String exectedNamesHeader = "names:";
			final String exectedCommentHeader = "COMMENT: ";
			if (scanner.hasNextLine()) {
				urlsHeader = scanner.nextLine();
			}else{
				System.out.println("Empty file: " + INFO_PATH);
				return false;
			}
			if (!urlsHeader.trim().equals(expectedUrlsHeader)){
				System.out.println("Invalid input in file: " + INFO_PATH + " : " + urlsHeader + " != " + expectedUrlsHeader);
				return false;
			}
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (!line.trim().equals(exectedNamesHeader)){
					URLs.add(line);
				}else{
					break;
				}
			}
			if (!scanner.hasNextLine()){
				System.out.println("Only URLs?");
				return false;
			}
			String name = scanner.nextLine();
			while (!name.trim().startsWith(exectedCommentHeader)){
				//System.out.println("Name: " + name);
				Names.add(name);
				name = scanner.nextLine();
			}
			scanner.close();
		}catch(FileNotFoundException fileNotFoundException){
			System.out.println("Caught FileNotFoundException: " + INFO_PATH);
			return false;
		}
		return true;
	}
	
	private static boolean generate(final String url, final String name){
		try {
			final int size = 80;
			final String fileType = "png";
			final String imagesDir = "images";
			final String outputFilePath = imagesDir + File.separator + name + "." + fileType;
			final File outputFile = new File(outputFilePath);
			final File dirTest = new File(imagesDir);
			
			if (!dirTest.exists()){
				System.out.println("Images directory does not exist: " + imagesDir);
				return false;
			}
			if (!dirTest.isDirectory()){
				System.out.println("Images directory is not a directory: " + imagesDir);
				return false;
			}
			
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 0); /* default = 4 */
			//Q seems to be best out of L,M,Q, and H
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
			
			final QRCodeWriter qrCodeWriter = new QRCodeWriter();
			final BitMatrix byteMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			final int width = byteMatrix.getWidth();
			
			final BufferedImage image = new BufferedImage(width, width,
					BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
 
			final Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, width, width);
			graphics.setColor(Color.BLACK);
			
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < width; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, outputFile);
			System.out.println("Created QR Code: " + outputFilePath);

		} catch (WriterException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}