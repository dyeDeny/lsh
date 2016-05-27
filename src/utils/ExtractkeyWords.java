package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.HanLP;

/**
 * 提取文本关键字
 * @author dy5239
 *
 */
public class ExtractkeyWords {

	/**
	 * 根据文本的路径path读取文本数据，可返回文本的关键词(最多50词)。并将这些关键词存储在同目录下的key_words 文件夹中。
	 * @param path 文本的绝对路径
	 * @return List<String> 文本的关键词 
	 */
	public static List<String> getKeyWords(String path) {

		List<String> keyWords = new ArrayList<String>(50);
		// System.out.println(path);
		String basePath = path.substring(0, path.lastIndexOf("\\"));
		// System.out.println(basePath);
		String fileName = path.substring(path.lastIndexOf("\\") + 1);
		// System.out.println(fileName);
		String outFolder = basePath + "\\key_words";
		String outPath = outFolder + "\\kw-" + fileName;
		// System.out.println(outPath);
		File kwFile = new File(outPath);
		if (!kwFile.exists()) {
			new File(outFolder).mkdirs();
			System.out.println("Generating key_word file at " + outPath );
			ExtractkeyWords.extract(path, outPath);
		}

		String[] kwList = TextConverter.File2String(outPath).split(",");
		for (String word : kwList) {
			keyWords.add(word.trim());
		}
		return keyWords;
	}
	/**
	 * 按照路径inPath读取文本，提取关键词并存储在outPath。
	 * @param inPath
	 * @param outPath
	 */
	public static void extract(String inPath, String outPath) {
		try {
			OutputStream ops = new FileOutputStream(new File(outPath));
			OutputStreamWriter ow = new OutputStreamWriter(ops, "utf-8");
			String context = TextConverter.File2String(inPath);
			List<String> wordList = HanLP.extractKeyword(context, 50);
			String wl = wordList.toString();
			ow.write(wl.substring(1, wl.length() - 1));
			ow.flush();
			ow.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 将basePath目录下的所有文本进行关键词提取，并存储在 bathPath\key_words 文件夹下。
	 * @param basePath
	 */
	public static void extract(String basePath) {
		File base = new File(basePath);
		String[] list = base.list();
		String kwFolder = basePath + "\\key_words";
		File folder = new File(kwFolder);
		folder.mkdir();
		for (String f : list) {
			String filePath = basePath + "\\" + f;
			String kw = "kw-" + f;
			String kwFilePath = kwFolder + "\\" + kw;
			if (new File(filePath).isDirectory())
				continue;
			System.out.println(filePath);
			extract(filePath, kwFilePath);
		}
	}

	public static void main(String[] args) {
		String basePath = "E:\\data";
//		String filePath = "E:\\KaixinBlog\\1.txt";
		 System.out.println("Start");
		 ExtractkeyWords.extract(basePath);
		 System.out.println("Finished");
//		System.out.println(ExtractkeyWords.getKeyWords(filePath));
	}
}
