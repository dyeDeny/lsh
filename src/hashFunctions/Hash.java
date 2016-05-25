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
	 * @param text 要哈希的文本
	 * @return 数组形式的哈希值
	 */
	
	public abstract int[] hash(String text);
}
