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
 * ��Ҫ�ṩ�ı���ȡ���ܣ��Լ��򵥵�shingle���ܡ�
 * @author dy5239
 *
 */
public class TextConverter {

	/**
	 * ���ı����м��д�
	 * @param text	�ַ����ı�
	 * @return	�дʵļ���
	 */
	public static List<String> String2Tokens(String text) {

		return String2Tokens(text, 5);
	}
	
	/**
	 * 
	 * @param text	�ַ����ı�
	 * @param num	�дʵĳ��ȣ� Ĭ��Ϊ5
	 * @return	�дʵļ���
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
	 * ��ȡ�ļ��ı�����
	 * @param path	�ļ��Ĵ��·��
	 * @return	�ı�����
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
	 * ���ı��ļ������ݽ����д�
	 * @param path	�ļ�·��
	 * @return	�дʼ���
	 */
	public static List<String> File2Tokens(String path) {
		return File2Tokens(path, 3);
	}
	
	/**
	 * ���ı��ļ������ݽ����д�
	 * @param path	�ļ�·��
	 * @param num	�дʳ���
	 * @return	�дʼ���
	 */
	public static List<String> File2Tokens(String path, int num) {
		String text = File2String(path);
		return String2Tokens(text, num);
	}

	public static void main(String[] args) {
		/*
		 * String text = "������"; // System.out.println(text.substring(0,1));
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
