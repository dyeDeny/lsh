package hashFunctions;

import java.io.Serializable;

/**
 * 
 * @author dy5239
 * @see MinHash
 * @see SimHash
 */
public abstract class Hash implements Serializable{
	/**
	 * 
	 * @param text Ҫ��ϣ���ı�
	 * @return ������ʽ�Ĺ�ϣֵ
	 */
	
	public abstract int[] hash(String text);
}
