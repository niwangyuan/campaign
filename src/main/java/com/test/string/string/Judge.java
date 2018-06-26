package com.test.string.string;

/*
 * 修改判定条件的类
 * 
 * 
 */

public class Judge {

	public static boolean merge(Camp_entity camp1, Camp_entity camp2, Campcenter center) {

		int k = 3;// 距离阀值
		int f = 1000;// 高频阀值
		int low = 100;// 低频阀值

		double pv = center.getPv();
		double pv1 = camp1.getPv();
		double pv2 = camp2.getPv();

		String str1 = camp1.getName();
		String str2 = camp2.getName();
		String str = center.getName();

		int distance = Russia.dis(str1, str2);// 相邻字符的距离
		int l1 = str1.length();
		int l2 = str2.length();
		int l = str.length();// 中心字符长度

		double duan = Russia.min(l2, l);// 第二个字符和中心字符的短字符
		double duan1 = Russia.min(l1, l2);// 第一个和第二个字符的短字符
		double same = Russia.same(str2, str);// 中心和第二个字符的相同个数
		double same1 = Russia.same(str1, str2);// 相邻字符的字符个数
		double near = same / duan;// 中心与第二个字符的相邻度
		double near1 = same1 / duan1;// 相邻字符的相邻度

		if (str1.equals(str2)) {
			System.out.println("same string true");
			return true;
		}

		else if (pv1 > f && pv2 > f) {
			System.out.println("high freq false");
			return false;
		} else if (pv > f && pv2 > f) {
			// System.out.println("chigh freq false");
			return false;
		}
		// if (str2.contains(str) || str.contains(str2) || str1.contains(str2) ||
		// str2.contains(str1)) {
		// System.out.println("包含关系");
		// return true;
		// }
		if (l1 == l2 && l1 < 30) {
			// System.out.println("短字符字数相同false");
			return false;
		}
		if ((l2 > 60) && near > 0.95) {
			// System.out.println("c长字符靠近合并true");
			return true;//
		}
		if ((l2 > 60 || l1 > 60) && near > 0.95) {
			// System.out.println("长字符靠近合并true");
			return true;//
		}
		if ((pv1 < low || pv2 < low) && Russia.similarity(str1, str2) > 0.9) {
			// System.out.println("低频词相似度合并true");
			return true;
		}
		if ((pv1 < low || pv2 < low) && Russia.similarity(str, str2) > 0.9) {
			// System.out.println("c低频词相似度合并true");
			return true;
		}
		if ((pv1 < low || pv2 < low) && same > 55) {
			// System.out.println("低频词相同字数合并true");
			return true;
		}
		if ((pv2 < low || pv1 < low) && duan > 15) {
			if (near > 0.95) {
				// System.out.println("c低频词靠近附近短字符true");
				return true;
			}
			if (str2.contains(str) || str.contains(str2) || str1.contains(str2) || str2.contains(str1)) {
				// System.out.println("包含关系");
				return true;
			}
		}
		if ((pv1 < low || pv2 < low) && near1 > 0.95 && duan1 > 15) {
			if (near1 > 0.95) {
				// System.out.println("c低频词靠近附近短字符true");
				return true;
			}
			if (str2.contains(str) || str.contains(str2) || str1.contains(str2) || str2.contains(str1)) {
				// System.out.println("包含关系");
				return true;
			}
		}
		if (near1 > 0.99 && duan1 > 5) {
			if ((pv1 > f && pv2 < 5) || (pv2 > f && pv1 < 5)) {
				// System.out.println("高低频率相近true");
				return true;
			}
		}
		if (near > 0.99 && duan > 5) {
			if ((pv > f && pv2 < 5) || (pv2 > f && pv < 5)) {
				// System.out.println("高低频率相近true");
				return true;
			}
		}

		if (l1 == l2 && distance < 3) {
			// System.out.println("字数相同相近false");
			return false;
		}
		if (distance <= k && Russia.similarity(str1, str2) > 0.85) {
			// System.out.println("相似度合并true");
			return true;// 合并
		}
		// System.out.println("no match false");
		return false;

	}

	public static boolean merge(Camp_entity camp2, Campcenter center) {

		int k = 3;// 距离阀值
		int f = 500;// 频率阀值
		int low = 30;// 低频阀值

		double pv = center.getPv();
		double pv2 = camp2.getPv();

		String str2 = camp2.getName();
		String str = center.getName();

		int distance = Russia.dis(str, str2);
		int l2 = str2.length();
		int l = str.length();// 中心字符长度

		double duan = Russia.min(l2, l);// 第二个字符和中心字符的短字符
		double same = Russia.same(str2, str);// 中心和第二个字符的相同个数
		double near = same / duan;// 中心与第二个字符的相邻度

		if (str.equals(str2)) {
			// System.out.println("same string true");
			return true;
		}

		else if (pv2 > f) {
			// System.out.println("high freq false");
			return false;
		}

		// if(str2.contains(str)||str.contains(str2)) {
		// System.out.println("包含关系");
		// return true;
		// }

		if ((l2 > 60) && near > 0.95) {
			// System.out.println("c长字符靠近合并true");
			return true;//
		}

		if ((pv2 < low) && Russia.similarity(str, str2) > 0.9) {
			// System.out.println("c低频词相似度合并true");
			return true;
		}
		if ((pv2 < low) && same > 55) {
			// System.out.println("低频词相同字数合并true");
			return true;
		}
		if ((pv2 < low) && duan > 15) {
			if (str2.contains(str) || str.contains(str2)) {
				// System.out.println("包含关系");
				return true;
			}
			if (near > 0.95) {
				// System.out.println("c低频词靠近附近短字符true");
				return true;
			}
		}

		if (near > 0.99 && duan > 5) {
			if ((pv > f && pv2 < 5) || (pv2 > f && pv < 5)) {
				System.out.println("高低频率相近true");
				return true;
			}
		}

		if (distance <= k && Russia.similarity(str, str2) > 0.85) {
			System.out.println("相似度合并true");
			return true;// 合并
		}
		if (distance < 2 && pv2 < low && pv > f) {
			System.out.println("高低频相似");
			return true;
		}
		System.out.println("no match false");
		return false;

	}

}
