package utils.project;

import java.util.Date;
import java.util.Random;

public class Order {
	public static final String key = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String keyLow = "abcdefghijklmnopqrstuvwxyz0123456789";
	public static final String keyNum = "0123456789";

	// 生成订单号
	public static String getUnique() {
		// 随机生成订单编号时间戳加两位随机数
		java.util.Random r1 = new java.util.Random();
		int random1 = 0;
		while (random1 < 100) {
			random1 = r1.nextInt(1000);
		}
		String unique = "D" + System.currentTimeMillis() + (random1);
		return unique;
	}

	// 生成 * + 时间戳 + 2位随机数 编号
	public static String getUnique(String t) {

		// 随机生成订单编号时间戳加两位随机数
		java.util.Random r1 = new java.util.Random();
		int random1 = 0;
		while (random1 < 100) {
			random1 = r1.nextInt(1000);
		}
		String unique = t + System.currentTimeMillis() + (random1);
		return unique;
	}

	// 生成N位 随机数getRandom2
	public static String getRandomx(int num) {
		StringBuffer buffer = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			int random = rand.nextInt(key.length());
			char c = key.charAt(random);
			buffer.append(c);
		}
		return buffer.toString();
	}

	// 生成N位 随机数getRandom2 数字
	public static String getRandomNum(int num) {
		StringBuffer buffer = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			int random = rand.nextInt(keyNum.length());
			char c = keyNum.charAt(random);
			buffer.append(c);
		}
		return buffer.toString();
	}

	// 生成N位 整数 随机数小写字母和数字
	public static String getRandomLow(int num) {
		StringBuffer buffer = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			int random = rand.nextInt(key.length());
			char c = key.charAt(random);
			buffer.append(c);
		}
		return buffer.toString();
	}

	public static String getRandom(int num) {
		String sRand = "";
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			String rand1 = String.valueOf(random.nextInt(10));
			sRand += rand1;
		}
		return sRand;
	}

	// 生成字符串ID
	public static String getIdStr() {
		Date date = new Date();
		long millisecond = date.getTime();
		String idStr = millisecond + "_" + Order.getRandom(10);
		return idStr;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			System.out.println(Order.getIdStr());
		}

	}
}
