package com.wisdom.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isNum(String str){
		 if(isNotEmpty(str)){
			 str = str.startsWith("-")?str.substring(1):str;
			 if(isNotEmpty(str)){
				 Pattern pattern = Pattern.compile("[0-9]*");
				 Matcher isNum = pattern.matcher(str);
				 return isNum.matches();
			 }
		 }
		 return false;
   }
	
	public static void main(String[] args) {
		System.out.println(StringUtil.isNum("0"));
	}
	
	public static boolean isNotEmpty(String str){
		 return str!=null && !str.trim().equals("");
   }
	
	public static boolean isEmpty(String str){
		 return str==null || str.trim().equals("");
	}

	public static String getUUID(){
		String s = UUID.randomUUID().toString();
		//去掉“-”符号
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	}
}
