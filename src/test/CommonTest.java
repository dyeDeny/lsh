package test;

import java.io.UnsupportedEncodingException;

import utils.CityHash;

public class CommonTest {
	
	public static void main(String[] args){
		String s1 = "¹þ¹þ";
		byte[] val = null;
		try {
			val = s1.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long sig1 = CityHash.cityHash64(val, 0, val.length);
		System.out.println(Long.toBinaryString(sig1));
	
		int[][] ar = new int[][]{ {2,3}, {4,5}, {6,7}};
		System.out.println(ar[0][1]);
	}
}
