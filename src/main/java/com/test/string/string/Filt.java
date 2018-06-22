package com.test.string.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Filt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String pathin ="/Users/wangyuanni/Desktop/campaigns/dd_campaigns.txt";
		String pathout ="/Users/wangyuanni/Desktop/campaigns/pvc.txt";

		// 原始数据
		List<Camp_entity> totalList = new ArrayList<Camp_entity>();
		// 归类数据
		List<Campcenter> centerlist = new ArrayList<Campcenter>();

		//读数据
		try {
			BufferedReader readTxt = new BufferedReader(new FileReader(new File(pathin)));
			String textLine = "";// 读行
			while ((textLine = readTxt.readLine()) != null) {
				String[] ary = textLine.split("\\^");
				String raw = ary[0];
				double pv = Double.parseDouble(ary[1]);
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

		//初整理
		List<String> sortFiledNameList = new ArrayList<String>();
		sortFiledNameList.add("name");
		List<Camp_entity> total = ListUtil.sortListByMultiFields(totalList, sortFiledNameList, 0);
		Campcenter center0 = new Campcenter(total.get(0));
		total.get(0).setCenter(center0);
		centerlist.add(center0);

		//初归类
		for (int i = 0; i < total.size() - 1; i++) {
			boolean judge = false;
			Camp_entity camp1 = total.get(i);
			Camp_entity camp2 = total.get(i + 1);
			Campcenter centertemp = camp1.getCenter();
			judge = Judge.list(camp1, camp2, centertemp);
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
		//簇处理
		for (Campcenter c : centerlist) {
			c.operation();
		}
		
		//进一步归类合并
		for (int i=0;i<centerlist.size();i++) {
			Campcenter c=centerlist.get(i);
			List<Camp_entity> list=c.getCon();
			int size=list.size();
			double pv=c.getPv();
			//需要重新归类的campaign
			if((size==1&&pv<2000)||pv<100) {
				Camp_entity camp2=new Camp_entity(c);
				for(int j=0;j<centerlist.size();j++) {
					if(j!=i) {
						Campcenter c_big=centerlist.get(j);
						List<Camp_entity> list_big=c_big.getCon();
						if(list_big.size()>1) {
							boolean merge=Judge.list(camp2, c_big);
							if(merge) {
								for(Camp_entity info:list) {
									info.setCenter(c_big);
									c_big.getCon().add(info);							
								}

//								camp2.setCenter(c_big);
//								c_big.getCon().add(camp2);
								c.getCon().clear();
								break;
							}
						}
					}
				}
			}
		}
		
		
		//输出结果
		try {
			BufferedWriter writetxt = new BufferedWriter(new FileWriter(pathout));
			int i=0;
			for (Campcenter c : centerlist) {
				c.operation();
				if(c.getCon().size()>0) {
					i=i+1;
					for (Camp_entity info : c.getCon()) {
//						writetxt.write(info.getRaw() +"("+info.getPv()+ ")^" + c.getName()+"("+c.getPv()+")");// 输出最大频率的campaign
						writetxt.write(info.getRaw() +"^" + c.getName());
						writetxt.newLine();
					}
				}
			}
			System.out.println(i);
			writetxt.flush();
			writetxt.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
