package hashFunctions;

import java.util.List;

import utils.CityHash;
import utils.HashCodeConverter;
import utils.Printer;
import utils.Similarity;
import utils.TextConverter;

/**
 * SimHash 的基本类。包含方法可返回文本的SimHash值。
 * @author dy5239
 *
 */
public class SimHash extends Hash {

	@Override
	public int[] hash(String text) {
		return hash(text, 3);
	}
	
	/**
	 * 对文本进行SimHash
	 * @param text	字符串文本
	 * @param num	切词长度
	 * @return	数组形式的SimHash值
	 */
	public int[] hash(String text, int num) {
		int[] signature = new int[64];
		List<String> tokens = TextConverter.String2Tokens(text, num);
		for(String tk : tokens){
			byte[] s = tk.getBytes();
			long fp = CityHash.cityHash64(s, 0, s.length);
			int[] bit = HashCodeConverter.Long2bit64(fp);
			for(int i = 0; i < bit.length; i++){
				if(bit[i] == 1) signature[i]++;
				else signature[i]--;
			}
		}
		for(int i = 0; i < signature.length; i++){
			if(signature[i] > 0) signature[i] = 1;
			else signature[i] = 0;
		}
		return signature;
	}
	/**
	 * 
	 * @param path	文本路径
	 * @return	数组形式的SimHash值
	 */
	public int[] hashFile(String path){
		String text = TextConverter.File2String(path);
		return this.hash(text);
	}
	/**
	 * 对文件进行SimHash
	 * @param path	文本路径
	 * @param num	切词长度
	 * @return	数组形式的SimHash值
	 */
	public int[] hashFile(String path, int num){
		String text = TextConverter.File2String(path);
		return this.hash(text, num);
	}
	
	

	public static void main(String[] args) {

		String text1 = "今天是个好天气啊";
		String text2 = "今天是个好天气啊";
		SimHash sh = new SimHash();
		
//		List<String> tokens = TextConverter.String2Tokens(text1, 8);
		
		
		
		int n = 2;
		int[] sig1 = sh.hash(text1, n);
		int[] sig2 = sh.hash(text2, n);
		
		Printer.printSig(sig1);
		Printer.printSig(sig2);
		System.out.println(Similarity.HammingDistance(sig1, sig2, 0, sig1.length));
	}

}
