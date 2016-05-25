package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 主要提供文本读取功能，以及简单的shingle功能。
 * @author dy5239
 *
 */
public class TextConverter {

	/**
	 * 对文本进行简单切词
	 * @param text	字符串文本
	 * @return	切词的集合
	 */
	public static List<String> String2Tokens(String text) {

		return String2Tokens(text, 5);
	}
	
	/**
	 * 
	 * @param text	字符串文本
	 * @param num	切词的长度， 默认为5
	 * @return	切词的集合
	 */
	public static List<String> String2Tokens(String text, int num) {
		ArrayList<String> tokens = new ArrayList<String>();
		int textlen = text.length();
		int start = 0;

		for (; start + num < textlen; start++) {
			tokens.add(text.substring(start, start + num));
		}
		if (start != textlen) {
			tokens.add(text.substring(start, textlen));
		}
		return tokens;
	}

	/**
	 * 读取文件文本内容
	 * @param path	文件的存放路径
	 * @return	文本内容
	 */
	public static String File2String(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			reader = new BufferedReader(isr);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer data = new StringBuffer();
		String s = "";
		try {
			while (null != (s = reader.readLine())) {
				if(!s.trim().equals("")){
					data.append(s);					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data.toString().trim();
	}
	
	/**
	 * 对文本文件的内容进行切词
	 * @param path	文件路径
	 * @return	切词集合
	 */
	public static List<String> File2Tokens(String path) {
		return File2Tokens(path, 3);
	}
	
	/**
	 * 对文本文件的内容进行切词
	 * @param path	文件路径
	 * @param num	切词长度
	 * @return	切词集合
	 */
	public static List<String> File2Tokens(String path, int num) {
		String text = File2String(path);
		return String2Tokens(text, num);
	}

	public static void main(String[] args) {
		/*
		 * String text = "今天是"; // System.out.println(text.substring(0,1));
		 * List<String> tks = String2Tokens(text, 1); for (String e : tks) {
		 * System.out.println(e); }
		 */
		/*
		 * String path = "E:\\SinaBlog\\166.txt"; String data =
		 * File2String(path); List<String> tks = String2Tokens(data, 4); for
		 * (String e : tks) { System.out.println(e); }
		 */
		String path = "E:\\SinaBlog\\166.txt";
		List<String> tks = File2Tokens(path, 8);
		for (String e : tks) {
			System.out.println(e);
		}
	}
}
