package com.chinarewards.license.util;

public class StringUtil {
	public static boolean isEmptyString(String str) {
		return str == null || "".equals(str) || "null".equals(str);
	}
	
    public static String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }  
}
