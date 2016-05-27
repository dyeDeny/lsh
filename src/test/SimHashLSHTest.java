package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import LSH.SimHashLSH;
import domain.Blog;
import utils.Similarity;
import utils.TextConverter;

public class SimHashLSHTest {

	public static void main(String[] args) {

		int cm = 5; //切词长度
		File simHashLSHtxt = new File("E:\\SimHashLSH-" + cm);
		SimHashLSH simLSH = null;

		if (!simHashLSHtxt.exists()) {
			try {
				simLSH = new SimHashLSH("E:\\data", cm);
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(simHashLSHtxt));
				oos.writeObject(simLSH);
				oos.flush();
				oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				System.out.printf("loading...");
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(simHashLSHtxt));
				simLSH = (SimHashLSH) ois.readObject();
				ois.close();
				System.out.printf("done!\n");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Scanner input = new Scanner(System.in);
		Set<Blog> allBlogs = simLSH.getAllBlog();

		String qnum = "";
		while (!(qnum = input.nextLine()).equals("#")) {
			// 记录查询时间
			String qpath = "E:\\data\\" + qnum.trim() + ".txt";
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSSS");
			System.out.println("query() start at : " + dateFormat.format(now));

			Set<Blog> rs = simLSH.query(qpath);

			System.out.println(rs + "\n" + rs.size());
			now = new Date();
			System.out.println("query() finish at : " + dateFormat.format(now));

			String text1 = TextConverter.File2String(qpath);
			String text2 = null;
			boolean debug = true;
			if (debug) {
				System.out.println("Debuging...");
				for (Blog b : allBlogs) {
//					text2 = TextConverter.File2String(b.getPath());
//					Double similarity = Similarity.JaccardSimilarity(text1, text2);
					Double similarity = Similarity.JaccardSimilarity_path(qpath, b.getPath());

					if (similarity.compareTo(0.7) > 0) {
						System.out.println(b.getPath() + " : " + similarity);
					}
				}
				System.out.println("Done");
			}
		}
	}
}
