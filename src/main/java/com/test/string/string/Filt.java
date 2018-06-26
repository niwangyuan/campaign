package com.test.string.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Filt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String pathin = "/Users/wangyuanni/Desktop/vb_dis_20180620/vb_media_dis_20180620.txt";
		// 原始数据
		List<Camp_entity> totalList = new ArrayList<Camp_entity>();
		Map<String, String> map = new HashMap<String, String>();

		// 读数据
		try {
			BufferedReader readTxt = new BufferedReader(new FileReader(new File(pathin)));
			String textLine = "";// 读行
			while ((textLine = readTxt.readLine()) != null) {
				String[] ary = textLine.split("	");
				String raw;
				Long pv;
				if (ary.length > 1) {
					raw = ary[1];
					pv = Long.parseLong(ary[0]);
				} else {
					raw = "";
					pv = Long.parseLong(ary[0]);
				}
				raw = raw.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
				String str = URLDecoder.decode(raw, "UTF-8");
				Camp_entity a = new Camp_entity(str, pv, raw);
				totalList.add(a);
			}
			readTxt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map = opr_list(totalList);
		for (String key : map.keySet()) {
			System.out.println(key + "====" + map.get(key));
		}
	}

	public static Map<String, String> operation(Map<String, Long> map) throws UnsupportedEncodingException {
		List<Camp_entity> totalList = loadmap(map);
		Map<String, String> res = opr_list(totalList);
		return res;
	}

	public static List<Camp_entity> loadmap(Map<String, Long> map) throws UnsupportedEncodingException {
		List<Camp_entity> list = new ArrayList<Camp_entity>();
		for (String key : map.keySet()) {
			Long pv = map.get(key);
			key = key.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
			String str = URLDecoder.decode(key, "UTF-8");
			Camp_entity a = new Camp_entity(str, pv, key);
			list.add(a);
		}
		return list;
	}

	public static Map<String, String> opr_list(List<Camp_entity> totalList) {

		// 归类数据
		List<Campcenter> centerlist = new ArrayList<Campcenter>();
		// 返回值
		Map<String, String> resmap = new HashMap<String, String>();
		// 初整理
		List<String> sortFiledNameList = new ArrayList<String>();
		sortFiledNameList.add("name");
		List<Camp_entity> total = ListUtil.sortListByMultiFields(totalList, sortFiledNameList, 0);
		Campcenter center0 = new Campcenter(total.get(0));
		total.get(0).setCenter(center0);
		centerlist.add(center0);

		// 初归类
		for (int i = 0; i < total.size() - 1; i++) {
			boolean judge = false;
			Camp_entity camp1 = total.get(i);
			Camp_entity camp2 = total.get(i + 1);
			Campcenter centertemp = camp1.getCenter();
			judge = Judge.merge(camp1, camp2, centertemp);
			if (judge)// 合并
			{
				centertemp.getCon().add(camp2);
				camp2.setCenter(centertemp);

			} else// 不合并
			{
				Campcenter newcenter = new Campcenter(camp2);
				camp2.setCenter(newcenter);
				centerlist.add(newcenter);
			}
		}
		// 簇处理
		for (Campcenter c : centerlist) {
			c.operation();
		}

		// 进一步归类合并
		for (int i = 0; i < centerlist.size(); i++) {
			Campcenter c = centerlist.get(i);
			List<Camp_entity> list = c.getCon();
			int size = list.size();
			Long pv = c.getPv();
			// 需要重新归类的campaign
			if ((size == 1 && pv < 2000) || pv < 100) {
				Camp_entity camp2 = c.getCon().get(0);
				for (int j = 0; j < centerlist.size(); j++) {
					if (j != i) {
						Campcenter c_big = centerlist.get(j);
						List<Camp_entity> list_big = c_big.getCon();
						if (list_big.size() >= 1) {
							boolean merge = Judge.merge(camp2, c_big);
							if (merge) {
								for (Camp_entity info : list) {
									info.setCenter(c_big);
									c_big.getCon().add(info);
								}
								c.getCon().clear();
								break;
							}
						}
					}
				}
			}
		}

		// 未归类低频词条析出
		for (int i = 0; i < centerlist.size(); i++) {
			Campcenter c = centerlist.get(i);
			Long pv = c.getPv();
			// 需要析出的campaign
			if (pv < 15) {
				c.setName("unknow");
			}
		}

		// 输出结果
		int i = 0;
		for (Campcenter c : centerlist) {
			c.operation();
			if (c.getCon().size() > 0) {
				i = i + 1;
				for (Camp_entity info : c.getCon()) {
					resmap.put(info.getRaw(), c.getName());
				}
			}
		}
		return resmap;
	}

}
