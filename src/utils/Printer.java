package utils;

/**
 * ������ hashCode תΪ�ַ�������ӡ�����ڲ��Թ��̡�
 * @author dy5239
 *
 */
public class Printer {
	
	/**
	 * ������������ʽ�Ĺ�ϣֵת�����ַ���
	 * @param sig	������ʽ��hashֵ
	 * @return	�ַ�����ʽ�Ĺ�ϣֵ
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
	 * ��ӡ����������ʽ�Ĺ�ϣֵ
	 * @param sig	������ʽ�Ĺ�ϣֵ
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
