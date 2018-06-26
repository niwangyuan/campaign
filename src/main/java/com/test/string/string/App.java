package com.test.string.string;

/**
 * 测试
 *
 */
public class App {

	//修改条件测试


	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String centername="CN_20170301_FACETHERUNWAY_LPD_GAB_MU_REGULAR_NVD_DISP_MO";
		Long pv=100000L;
		Campcenter center=new Campcenter(centername,pv);
		String name1="CN_20170301_FACETHERUNWAY_LPD_GAB_MU_REGULAR_NVD_DISP_MO";
		String name2="CN_20170301_FACETHERUNWAY_LPD_GAB_MU_REGULAR_VD_DISP_MO";
		Long pv1=100000L;
		Long pv2=1L;
		Camp_entity c1 = new Camp_entity(name1,pv1,"");
		Camp_entity c2 = new Camp_entity(name2,pv2,"");
		
		int k=3;//距离阀值
 	   int i=Russia.dis(c1.getName(), c2.getName());//相邻字符的距离 
 	   int m=c1.getName().length();
 	   int n=c2.getName().length();
 	   int l=center.getName().length();//中心字符长度
 	   double duan=Russia.min(n,l);//第二个字符和中心字符的短字符
 	   double duan1=Russia.min(m,n);//第一个和第二个字符的短字符
 	   double same=Russia.same(c2.getName(), center.getName());//中心和第二个字符的相同个数
 	   double same1=Russia.same(c1.getName(), c2.getName());//相邻字符的字符个数
 	   int f=500;//高率阀值
 	   int low=30;//低频阀值
 	   double near=same/duan;//中心与第二个字符的相邻度
 	   double near1=same1/duan1;//相邻字符的相邻度
 	   System.out.println("字符长度分别为："+m+","+n+","+l);
 	   System.out.println("相似度分别为："+same+","+same1);
 	   System.out.println("靠近度分别为"+near+","+near1);

 	   
		
		boolean rs=Judge.merge(c1, c2, center);
				
				
		

	}

}
