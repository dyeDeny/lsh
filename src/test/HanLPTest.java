package test;

import com.hankcs.hanlp.HanLP;

import utils.TextConverter;

public class HanLPTest {

	public static void main(String[] args) {
		
		
		String text = TextConverter.File2String("E:\\SinaBlog\\844.txt");
		System.out.println(text);
		System.out.println(HanLP.extractKeyword(text, 30));
	}
}
