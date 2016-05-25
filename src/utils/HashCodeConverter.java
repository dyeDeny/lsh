package utils;

import hashFunctions.SimHash;

/**
 * 将 simHash 的{0,1,0,1}数组 hashCode 转为 整型。用于{@link SimHash}.
 * @author dy5239
 *
 */
public class HashCodeConverter {

	
	/**
	 * @param hash    数组形式的 {0,1,0,1,...}的哈希值
	 * @param begin   要转换的哈希值段的起始位置
	 * @param end     要转换的哈希值段的结束位置
	 * @return  转化为整型的hash值
	 */
	public static int Hash2Int(int[] hash, int begin, int end) {
		int intCode = 0;
		for (int i = begin; i < end; i++) {
			intCode = (intCode << 1);
			intCode += hash[i];
		}
		return intCode;
	}
	/**
	 * 
	 * @param num long类型的哈希值
	 * @return	数组形式的64-bit哈希值
	 */
	public static int[] Long2bit64(long num){
		int[] bit = new int[64];
		for(int i = 0; i < bit.length; i++){
			bit[i] = Math.abs((int)(num % 2));
			num >>>= 1;
		}
		return bit;
	}
	
	
	public static void main(String[] arg){
		
		int[] hash = new int[]{0,1,1,1,1,0,1,1,1,0,1,0,1,1,0,0};
		int code = Hash2Int(hash, 0, 16);
		System.out.println(code);
	}
	
}
