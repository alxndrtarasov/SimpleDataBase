package starters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bd.BDWorker;
import objtype.Obj;

public class StatMaker {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ParseException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt", true));
		BDWorker worker = (BDWorker) context.getBean("worker");
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Random random = new Random();
		int k = 10;
		int n = 100;
		long fastest = Long.MAX_VALUE;
		long slowest = 0;
		long sumk = 0;
		long sumn = 0;
		for (int j = 0; j < k; j++) {
			for (int i = 1; i < n; i++) {
				int id = (worker.getLastId() + 1);
				Date date = new Date(random.nextLong());
				String name = randomString(10);
				String descr;
				if (i % 2 == 0) {
					descr = "Fruit";
				} else {
					descr = "Vegetable";
				}
				long start = System.currentTimeMillis();
				worker.add(new Obj("" + id, name, date, descr));
				long finish = System.currentTimeMillis();
				long time = finish - start;
				if (time < fastest) {
					fastest = time;
				}
				if (time > slowest) {
					slowest = time;
				}
				sumn += time;
			}
			sumk += sumn / n;
			fastest = Long.MAX_VALUE;
			slowest = 0;
			sumn = 0;
		}
		writer.write("оценка добавления: для " + n + " элементов - " + " fastest=" + fastest + " slowest=" + slowest
				+ " average=" + sumk / k);
		writer.newLine();

		fastest = Long.MAX_VALUE;
		slowest = 0;
		sumn = 0;
		sumk = 0;
		for (int j = 0; j < k; j++) {
			for (int i = 1; i < n; i++) {
				long start = System.currentTimeMillis();
				worker.findById(i);
				long finish = System.currentTimeMillis();
				long time = finish - start;
				if (time < fastest) {
					fastest = time;
				}
				if (time > slowest) {
					slowest = time;
				}
				sumn += time;
			}
			sumk += sumn / n;
			fastest = Long.MAX_VALUE;
			slowest = 0;
			sumn = 0;
		}
		writer.write("оценка поиска: для " + n + " элементов - " + "fastest=" + fastest + " slowest=" + slowest
				+ " average=" + sumk / k);
		writer.newLine();

		fastest = Long.MAX_VALUE;
		slowest = 0;
		sumn = 0;
		sumk = 0;
		for (int j = 0; j < k; j++) {
			for (int i = 1; i < n; i++) {
				long start = System.currentTimeMillis();
				worker.deleteById(i);
				long finish = System.currentTimeMillis();
				long time = finish - start;
				if (time < fastest) {
					fastest = time;
				}
				if (time > slowest) {
					slowest = time;
				}
				sumn += time;
			}
			sumk += sumn / n;
			fastest = Long.MAX_VALUE;
			slowest = 0;
			sumn = 0;
		}
		writer.write("оценка удаления: для " + n + " элементов - " + "fastest=" + fastest + " slowest=" + slowest
				+ " average=" + sumk / k);
		writer.newLine();
		writer.close();
	}
}
