package hashFunctions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.hankcs.hanlp.HanLP;
import utils.Dictionary;

/**
 * MinHash �Ļ����ࡣ���������ɷ����ı���MinHashֵ��
 * @author dy5239
 *
 */
public class MinHash extends Hash{

	@Override
	public int[] hash(String text) {
		// TODO Auto-generated method stub
		return hash(text, new int[][]{ {2,3}, {3, 4}, {5,6}});
	}
	

	/**
	 * ���ı�����MinHash
	 * @param text �ַ����ı�
	 * @param hashPara n��hash���� (h(x) = a*x + b) �Ĳ��� a��b. hashPara[i][0] ��Ӧ a[i], hashPara[i][1]��Ӧ b[i]��
	 * @return ������ʽ��MinHashֵ.
	 */
	public int[] hash(String text, int[][] hashPara) {
		
		int n = hashPara.length;
		int[] signature = new int[n];
		
		List<String> keyWords = HanLP.extractKeyword(text, 50);
		List<Integer> features = new ArrayList<Integer>();
		for(String word : keyWords){
//			System.out.println("word:" + word);
			if(null != Dictionary.dict.get(word)){
				int index = Dictionary.dict.get(word);
				features.add(index);
//				System.out.println(index);
			}
		}
		
		
		for(int i = 0; i < n; i++){
			signature[i] = Integer.MAX_VALUE;
		}
		
		for(int ind : features){
			for(int i = 0; i < n; i++){
				int h = (int) ((1L * hashPara[i][0] * ind + 1L * hashPara[i][1]) % Dictionary.dict.size());
				signature[i] = Math.min(signature[i], h);
			}
		}
		
		return signature;
	}
	
	/**
	 * ���ı�����MinHash
	 * @param keyWords �ı��Ĺؼ���
	 * @param hashPara n��hash���� (h(x) = a*x + b) �Ĳ��� a��b. hashPara[i][0] ��Ӧ a[i], hashPara[i][1]��Ӧ b[i]��
	 * @return ������ʽ��MinHashֵ.
	 */
	public int[] hash(List<String> keyWords, int[][] hashPara) {
		
		int n = hashPara.length;
		int[] signature = new int[n];
		
		List<Integer> features = new ArrayList<Integer>();
		for(String word : keyWords){
//			System.out.println("word:" + word);
			if(null != Dictionary.dict.get(word)){
				int index = Dictionary.dict.get(word);
				features.add(index);
//				System.out.println(index);
			}
		}
		
		
		for(int i = 0; i < n; i++){
			signature[i] = Integer.MAX_VALUE;
		}
		
		for(int ind : features){
			for(int i = 0; i < n; i++){
				int h = (int) ((1L * hashPara[i][0] * ind + 1L * hashPara[i][1]) % Dictionary.dict.size());
				signature[i] = Math.min(signature[i], h);
			}
		}
		
		return signature;
	}
	
}
