package test;

import hashFunctions.SimHash;
import utils.Printer;
import utils.Similarity;

public class SimHashTest {
	public static void main(String[] args){
		SimHash sh = new SimHash();
		String path1 = "E:\\SinaBlog\\56.txt";
		String path2 = "E:\\SinaBlog\\333.txt";
		String path3 = "E:\\SinaBlog\\740.txt";
		String path4 = "E:\\SinaBlog\\2668.txt";
		String path5 = "E:\\SinaBlog\\106409.txt";
		String path6 = "E:\\SinaBlog\\116573.txt";
		
		int num = 5;
		int[] sig1 = sh.hashFile(path1, num);
		int[] sig2 = sh.hashFile(path2, num);
		int[] sig3 = sh.hashFile(path3, num);
		int[] sig4 = sh.hashFile(path4, num);
		int[] sig5 = sh.hashFile(path5, num);
		int[] sig6 = sh.hashFile(path6, num);
		
		Printer.printSig(sig1);
		Printer.printSig(sig2);
		Printer.printSig(sig3);
		Printer.printSig(sig4);
		Printer.printSig(sig5);
		Printer.printSig(sig6);
		
		int simi = Similarity.HammingDistance(sig1, sig4, 0, 64);
		System.out.println(simi);
	}
}
