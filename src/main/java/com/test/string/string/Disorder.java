package com.test.string.string;


//判断字符中是否含有乱码，现在的判定中暂时没有加入这个条件。

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Disorder 
{

	/* 判断字符是否是中文
    *
    * @param c 字符
    * @return 是否是中文
    */
   public static boolean isChinese(char c) {
       Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
       if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
               || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
               || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
               || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
               || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
               || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
           return true;
       }
       return false;
   }

   /**
    * 判断字符串是否是乱码
    *
    * @param strName 字符串
    * @return 是否是乱码
    */
   public static boolean isMessyCode(String strName) {
       Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
       Matcher m = p.matcher(strName);
       String after = m.replaceAll("");
       String temp = after.replaceAll("\\p{P}", "");
       char[] ch = temp.trim().toCharArray();
       float chLength = ch.length;
       float count = 0;
       for (int i = 0; i < ch.length; i++) {
           char c = ch[i];
           if (!Character.isLetterOrDigit(c)) {
               if (!isChinese(c)) {
                   count = count + 1;
               }
           }
       }
       float result = count / chLength;
       if (result > 0.4) {
           return true;
       } else {
           return false;
       }

   }

   public static void main(String[] args) {
       System.out.println(isMessyCode("CN_20170101_EO_JAN_OTV_PDB_CPD_LRL_FH_PROGRAMMATIC_OTV_PC_MOE���"));
       System.out.println(isMessyCode("你好"));
   }

}


