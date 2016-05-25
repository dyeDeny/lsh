package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import hashFunctions.MinHash;

/**
 * ά��һ��HashMap���洢���йؼ��ʼ���Ӧ����š���{@link MinHash}ʹ�á�
 * @author dy5239
 * @see MinHash
 */
public class Dictionary {
	
	/** dict Ϊ�ؼ��ֵ��ֵ� */
	public static HashMap<String, Integer> dict = new HashMap<>();

	static {
		int cnt = 0;
		String coreDictPath = "C:\\Users\\dy5239\\Downloads\\HanLP\\data-for-1.2.8-standard\\data\\dictionary\\CoreNatureDictionary.mini.txt";
		File coreDict = new File(coreDictPath);
		BufferedReader reader = null;
		try{
			InputStreamReader isr = new InputStreamReader(new FileInputStream(coreDict), "utf-8");
			reader = new BufferedReader(isr);
		}catch(Exception e){
			e.printStackTrace();
		}
		String s;
		try {
			while((s = reader.readLine() ) != null){
				String word = s.split("\t")[0];
				dict.put(word, cnt++);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		HashMap<String, Integer> hm = Dictionary.dict;
		Set<String> ks = dict.keySet();
		for(String k : ks){
			System.out.println(k);
		}
		System.out.println(hm.get("һ��һʮ") + " / " + hm.size());
	}
}
