package LSH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import domain.Blog;
import hashFunctions.SimHash;
import utils.HashCodeConverter;
import utils.Similarity;

/**
 * ����SimHash���ı�����ʵ�֣��������ļ�·����ѯ���Ƶ��ı���
 * 
 * @author dy5239
 * @see SimHash
 */
public class SimHashLSH implements Serializable {

	private HashMap<Integer, Set<Blog>>[] hasm = new HashMap[4];
	private int shingNum;
	private SimHash sh = null;

	public HashMap<Integer, Set<Blog>>[] getHasm() {
		return hasm;
	}

	public void setHasm(HashMap<Integer, Set<Blog>>[] hasm) {
		this.hasm = hasm;
	}

	public SimHashLSH() {
		init();
	}

	/**
	 * 
	 * @param basePath
	 *            �����ļ����Ŀ¼
	 */
	public SimHashLSH(String basePath) {
		init(basePath, 5);
	}

	/**
	 * LSH��ʼ������ʱ
	 * 
	 * @param basePath
	 *            �����ļ����Ŀ¼
	 * @param num
	 *            �ı��дʳ��ȡ�Ĭ��Ϊ5
	 */
	public SimHashLSH(String basePath, int num) {
		init(basePath, num);
	}

	private void init() {
		String basePath = "E:\\SinaBlog";
		init(basePath, 5);
	}

	private void init(String basePath, int num) {
		this.shingNum = num;
		sh = new SimHash();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("Init() start at : " + dateFormat.format(now));
		for (int i = 0; i < hasm.length; i++) {
			hasm[i] = new HashMap<Integer, Set<Blog>>();
		}

		File file = new File(basePath);
		String[] list = file.list();

		// System.out.println(list.length);
		for (int i = 0; i < list.length; i++) {
			// System.out.println(basePath + "\\" + list[i]);
			String path = basePath + "\\" + list[i];
			// System.out.println(path);
			int[] hashCode = sh.hashFile(path, shingNum);
			// Printer.printSig(hashCode);
			Blog blog = new Blog(path, hashCode);
			int[] hcode = new int[4];
			for (int j = 0; j < 4; j++) {
				hcode[j] = HashCodeConverter.Hash2Int(hashCode, j * 16, j * 16 + 16);
				if (!hasm[j].containsKey(hcode[j])) {
					Set<Blog> set = new HashSet<Blog>();
					set.add(blog);
					hasm[j].put(hcode[j], set);
				} else {
					hasm[j].get(hcode[j]).add(blog);
				}
			}
		}
		now = new Date();
		System.out.println("Init() finish at : " + dateFormat.format(now));
	}

	/**
	 * 
	 * @return �ı��������в���
	 */
	public Set<Blog> getAllBlog() {
		Set<Blog> rs = new HashSet<>();
		HashMap<Integer, Set<Blog>>[] hsmp = this.getHasm();
		for (int i = 0; i < 4; i++) {
			Set<Map.Entry<Integer, Set<Blog>>> es = hsmp[i].entrySet();
			for (Map.Entry<Integer, Set<Blog>> entry : es) {
				rs.addAll(entry.getValue());
			}
		}

		return rs;
	}

	/**
	 * ��ѯ�ı��������Ƶ��ļ�
	 * 
	 * @param path
	 *            ����ѯΪ�ļ����·��
	 * @return ���ƵĲ��ͼ���
	 */
	public Set<Blog> query(String path) {
		Set<Blog> res = new HashSet<>();
		int[] qCode = sh.hashFile(path, shingNum);
		HashMap<Integer, Set<Blog>>[] hsmp = this.getHasm();
		int[] sCode = new int[4]; // �ĸ����ֵ� 16-bit
		for (int i = 0; i < 4; i++) {
			sCode[i] = HashCodeConverter.Hash2Int(qCode, i * 16, i * 16 + 16);
			Set<Blog> ts = null;
			if ((ts = hsmp[i].get(sCode[i])) != null) {
				res.addAll(ts);
			}
		}

		Iterator<Blog> itrator = res.iterator();
		while (itrator.hasNext()) {
			Blog e = itrator.next();
			if (Similarity.HammingDistance(e.getHashId(), qCode, 0, 64) > 3) {
				itrator.remove();
			}
		}
		return res;
	}

	public static void main(String[] args) {
		File simHashLSHtxt = new File("E:\\SimHashLSH.txt");
		if (!simHashLSHtxt.exists()) {
			try {
				SimHashLSH smh = new SimHashLSH();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(simHashLSHtxt));
				oos.writeObject(smh);
				oos.flush();
				oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(simHashLSHtxt));
			SimHashLSH smh1 = (SimHashLSH) ois.readObject();
			ois.close();
			Set<Blog> rs = smh1.query("E:\\SinaBlog\\56.txt");
			for (Blog blog : rs) {
				System.out.println(blog);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
