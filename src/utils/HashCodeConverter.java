package utils;

import hashFunctions.SimHash;

/**
 * �� simHash ��{0,1,0,1}���� hashCode תΪ ���͡�����{@link SimHash}.
 * @author dy5239
 *
 */
public class HashCodeConverter {

	
	/**
	 * @param hash    ������ʽ�� {0,1,0,1,...}�Ĺ�ϣֵ
	 * @param begin   Ҫת���Ĺ�ϣֵ�ε���ʼλ��
	 * @param end     Ҫת���Ĺ�ϣֵ�εĽ���λ��
	 * @return  ת��Ϊ���͵�hashֵ
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
	 * @param num long���͵Ĺ�ϣֵ
	 * @return	������ʽ��64-bit��ϣֵ
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
