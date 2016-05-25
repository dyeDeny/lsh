package test;

import java.util.Random;

import hashFunctions.MinHash;
import utils.Dictionary;
import utils.Printer;
import utils.Similarity;
import utils.TextConverter;

public class MinHashTest {
	
	public static void main(String[] args){
		Random r = new Random();
		int n = 5;
		int[][] hashPara = new int[n][2];
		for(int i = 0; i < n; i++){
			hashPara[i][0] = r.nextInt(Dictionary.dict.size());
			hashPara[i][1] = r.nextInt(Dictionary.dict.size());
			
//			System.out.printf("%6d %6d\n", hashPara[i][0], hashPara[i][1]);
		}
		
		MinHash mh = new MinHash();
		String path1 = "E:\\SinaBlog\\0.txt";
		String path2 = "E:\\SinaBlog\\-1.txt";
		String text1 = TextConverter.File2String(path1);
		String text2 = TextConverter.File2String(path2);
		int[] sig1 = mh.hash(text1, hashPara);
		int[] sig2 = mh.hash(text2, hashPara);
		Printer.printSig(sig1);
		Printer.printSig(sig2);
		System.out.println(Similarity.JaccardSimilarity(text1, text2));
	}
}
