package LSH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.hankcs.hanlp.HanLP;

import domain.Blog;
import hashFunctions.MinHash;
import utils.Dictionary;
import utils.ExtractkeyWords;
import utils.Printer;
import utils.Similarity;
import utils.TextConverter;

/**
 * 基于MinHash的LSH算法实现。初始化时根据参数确定 n 和 p 。可输入文件路径查询相似的文本。
 * 
 * @author dy5239
 * @see MinHash
 */
public class MinHashLSH implements Serializable {
	private HashMap<String, Set<Blog>>[] hsmp;
	private MinHash minHash = new MinHash();
	private int[][][] hashPara;
	private String basePath = null;

	public HashMap<String, Set<Blog>>[] getHsmp() {
		return hsmp;
	}

	public void setHsmp(HashMap<String, Set<Blog>>[] hsmp) {
		this.hsmp = hsmp;
	}

	public MinHash getMinHasH() {
		return minHash;
	}

	public void setMinHasH(MinHash minHasH) {
		this.minHash = minHasH;
	}

	public MinHashLSH() {
		init();
	}

	/**
	 * 
	 * @param path
	 *            文本数据的存放目录
	 */
	public MinHashLSH(String path) {
		init(path, 5, 3);
	}

	public MinHashLSH(String path, int n, int p) {
		init(path, n, p);
	}

	private void init() {
		String base = "E:\\SinaBlog";
		init(base, 5, 3);
	}

	private void init(String path, int n, int p) {

		this.basePath = path;
		this.hashPara = new int[p][n][2];
		this.hsmp = new HashMap[p];
		Random random = new Random();
		for (int j = 0; j < p; j++) {
			for (int i = 0; i < n; i++) {
				this.hashPara[j][i][0] = random.nextInt(Dictionary.dict.size());
				this.hashPara[j][i][1] = random.nextInt(Dictionary.dict.size());
			}
		}
		File file = new File(basePath);
		String[] list = file.list();
		int num = list.length;
		for (int i = 0; i < num; i++) {
			String f = list[i];
			String filePath = basePath + "\\" + f;
			if (new File(filePath).isDirectory())
				continue;
//			String kwPath = basePath + "\\key_words\\kw-" + f;
//			File kwFile = new File(kwPath);
//			List<String> keyWords = new ArrayList<String>();
//			if (!kwFile.exists()) {
//				ExtractkeyWords.extract(filePath, kwPath);
//				kwFile = new File(kwPath);
//			}
//
//			String[] kwList = TextConverter.File2String(kwPath).split(",");
//			for (String word : kwList) {
//				keyWords.add(word.trim());
//			}
			List<String> keyWords = ExtractkeyWords.getKeyWords(filePath);

			if (i % 100 == 0)
				System.out.printf("%d/%d\n", i, num);
			for (int j = 0; j < p; j++) {
				if (null == hsmp[j])
					hsmp[j] = new HashMap<String, Set<Blog>>();
				int[] signature = minHash.hash(keyWords, hashPara[j]);
				String finger = Printer.getString(signature);
				Blog blog = new Blog(filePath, signature);
				if (null == hsmp[j].get(finger)) {
					Set<Blog> bs = new HashSet<Blog>();
					hsmp[j].put(finger, bs);
				}
				hsmp[j].get(finger).add(blog);
			}
		}
	}

	/**
	 * 查询文本库中相似的文件
	 * 
	 * @param path
	 *            查询文件的存放目录
	 * @return 相似博客的集合
	 */
	public Set<Blog> query(String path) {

		Set<Blog> res = new HashSet<Blog>();
		String text = TextConverter.File2String(path);
		int p = this.hsmp.length;
		for (int j = 0; j < p; j++) {
			int[] sig = minHash.hash(text, hashPara[j]);
			String fp = Printer.getString(sig);
			Set<Blog> hs = hsmp[j].get(fp);
			;
			if (null != hs) {
				res.addAll(hs);
			}
		}
		return res;
	}

	public static void main(String args[]) {
		MinHashLSH mlsh = new MinHashLSH();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\MinHashLSH.txt"));
			oos.writeObject(mlsh);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:\\MinHashLSH.txt"));
			MinHashLSH mlsh1 = (MinHashLSH) ois.readObject();
			Set<Blog> rs = mlsh1.query("E:\\SinaBlog\\56.txt");
			for (Blog blog : rs) {
				System.out.println(blog);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
