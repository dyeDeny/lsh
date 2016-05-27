package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hankcs.hanlp.HanLP;

/**
 * �ṩ������̬�������ֱ������Ƚ������ı��� Jaccard ���ƶ� �� hammin���롣
 * @author dy5239
 *
 */

public class Similarity {
	
	/**
	 * �Ƚ�����������ʽ��ϣֵ��ĳһ���ڵĺ�������
	 * @param sig1	�����ϣֵ1
	 * @param sig2	�����ϣֵ2
	 * @param start	�ȽϹ�ϣֵ�εĿ�ʼλ��
	 * @param length	�ȽϹ�ϣֵ�εĽ���λ��
	 * @return	������
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
	 * �Ƚ������ı���Jaccard���ƶ�
	 * ��ȡ�������ı��Ĺؼ���A,B�����㼯��A,B��Jaccard���ƶȣ�Sim(A, B) = (A �� B)/(A �� B)
	 * @param text1	�ַ����ı�1
	 * @param text2	�ַ����ı�2
	 * @return	�ı����ƶ�
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
