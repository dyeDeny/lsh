package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hankcs.hanlp.HanLP;

/**
 * 提供两个静态方法，分别用来比较两个文本的 Jaccard 相似度 和 hammin距离。
 * @author dy5239
 *
 */

public class Similarity {
	
	/**
	 * 比较两个数组形式哈希值在某一段内的汉明距离
	 * @param sig1	数组哈希值1
	 * @param sig2	数组哈希值2
	 * @param start	比较哈希值段的开始位置
	 * @param length	比较哈希值段的结束位置
	 * @return	汉明距
	 */
	public static int HammingDistance(int[] sig1, int[] sig2, int start, int length){
		
		if(sig1.length != sig2.length){
			return 64;
		}
		int cnt = 0;
		for(int i = 0; i < length; i++){
			if(i+start < sig1.length){
				if(sig1[i+start] != sig2[i+start]) cnt++;
			}else{
				break;
			}
		}
		return cnt;
	}
	
	/**
	 * 比较两个文本的Jaccard相似度
	 * 提取出两个文本的关键词A,B，计算集合A,B的Jaccard相似度：Sim(A, B) = (A ∩ B)/(A ∪ B)
	 * @param text1	字符串文本1
	 * @param text2	字符串文本2
	 * @return	文本相似度
	 */
	public static double JaccardSimilarity(String text1, String text2){
		double sim = 0.0;
		int size = 30;
		List<String> ls1 = HanLP.extractKeyword(text2, size);
		List<String> ls2 = HanLP.extractKeyword(text1, size);
		
		Set<String> comm = new HashSet<String>(ls1);
		Set<String> union = new HashSet<String>(ls1);
		
		comm.retainAll(ls2);
		union.addAll(ls2);
		
		if(union.size() == 0) return sim;
		else{
			sim = 1.0 * comm.size() / union.size();
		}
		
		return sim;
	}
	
	public static double JaccardSimilarity_path(String path1, String path2){
		double sim = 0.0;
		int size = 30;
		List<String> ls1 = ExtractkeyWords.getKeyWords(path1);
		List<String> ls2 = ExtractkeyWords.getKeyWords(path2);
		
		Set<String> comm = new HashSet<String>(ls1);
		Set<String> union = new HashSet<String>(ls1);
		
		comm.retainAll(ls2);
		union.addAll(ls2);
		
		if(union.size() == 0) return sim;
		else{
			sim = 1.0 * comm.size() / union.size();
		}
		
		return sim;
	}
}
