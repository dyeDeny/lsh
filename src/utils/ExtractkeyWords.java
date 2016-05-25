package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.hankcs.hanlp.HanLP;

public class ExtractkeyWords {
	
	public static void extract(String inPath, String outPath){
		try {
			OutputStream ops = new FileOutputStream(new File(outPath));
			OutputStreamWriter ow = new OutputStreamWriter(ops, "utf-8");
			String context = TextConverter.File2String(inPath);
			List<String> wordList = HanLP.extractKeyword(context, 50);
			String wl = wordList.toString();
			ow.write(wl.substring(1,wl.length()-1));
			ow.flush();
			ow.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void extract(String basePath){
		File base = new File(basePath);
		String[] list = base.list();
		String kwFolder = basePath + "\\key_words";
		File folder = new File(kwFolder);
		folder.mkdir();
		for(String f : list){
			String filePath = basePath + "\\" + f;
			String kw = "kw-" + f;
			String kwFilePath = kwFolder + "\\" + kw;
			if(new File(filePath).isDirectory()) continue;
			System.out.println(filePath);
			extract(filePath, kwFilePath);
		}
	}
	
	public static void main(String[] args){
		String basePath = "E:\\KaixinBlog";
		System.out.println("Start");
		ExtractkeyWords.extract(basePath);
		System.out.println("Finished");
	}
}
