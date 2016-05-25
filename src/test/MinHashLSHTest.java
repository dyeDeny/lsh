package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import LSH.MinHashLSH;
import domain.Blog;
import utils.Similarity;
import utils.TextConverter;

public class MinHashLSHTest {
	
	public static void main(String args[]){
		
		
		int n = 4;
		int p = 6;
		MinHashLSH mlsh = null;
		File mlshTxt = new File("E:\\MinHashLSH-" + n + "-" + p);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSSS");
		
		if(!mlshTxt.exists()){
			Date now = new Date();
			String base = "E:\\SinaBlog";
			
			System.out.println("初始化开始： " + dateFormat.format(now));
			mlsh = new MinHashLSH(base, n, p);
			now = new Date();
			System.out.println("初始化结束： " + dateFormat.format(now));
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mlshTxt));
				oos.writeObject(mlsh);
				oos.flush();
				oos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.printf("Loading...");
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(mlshTxt));
				mlsh = (MinHashLSH) ois.readObject();
				ois.close();
				System.out.println("done!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Scanner input=new Scanner(System.in);
		String qnum = "";
		while(!(qnum = input.nextLine()).equals("#")){
			//记录查询时间
			String qpath = "E:\\SinaBlog\\" + qnum.trim() + ".txt";
			Date now = new Date();
			System.out.println("query() start at : " + dateFormat.format(now));
			String text1 = TextConverter.File2String(qpath); 
			Set<Blog> rs = mlsh.query(qpath);
			
			for(Blog blog : rs){
				String text2 = TextConverter.File2String(blog.getPath());
				System.out.println(Similarity.JaccardSimilarity(text1, text2) + " : " + blog);
			}
			
			now = new Date();
			System.out.println("query() finish at : " + dateFormat.format(now));
		}
	}
}
