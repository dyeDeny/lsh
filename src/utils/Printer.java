package utils;

/**
 * 将数组 hashCode 转为字符串并打印。用于测试过程。
 * @author dy5239
 *
 */
public class Printer {
	
	/**
	 * 将整型数组形式的哈希值转换成字符串
	 * @param sig	数组形式的hash值
	 * @return	字符串形式的哈希值
	 */
	public static String getString(int[] sig){
		String s = "";
		boolean flag = true;
		for(int i = 0; i < sig.length; i++){
			if(flag) flag = false;
			else s += " ";
			s += sig[i];
		}
		return s;
	}
	
	/**
	 * 打印整型数组形式的哈希值
	 * @param sig	数组形式的哈希值
	 */
	public static void printSig(int[] sig){
		System.out.println(getString(sig));
	}
	

	public static String getString(char[] arr){
		String s = "";
		boolean flag = true;
		for(int i = 0; i < arr.length; i++){
			if(flag) flag = false;
			else s += " ";
			s += arr[i];
		}
		
		return s;
	}
	
	
	public static void printChar(char[] arr){
		System.out.println(getString(arr));
	}
}
