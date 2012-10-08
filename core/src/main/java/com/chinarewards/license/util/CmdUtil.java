package com.chinarewards.license.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CmdUtil {

	public CmdUtil() {
	}

	public static String exec(String cmd) {
		StringBuffer sb1;
		StringBuffer sb2;
		sb1 = new StringBuffer();
		sb2 = new StringBuffer();
		String temp1 = "";
		String temp2 = "";
		Runtime rt = Runtime.getRuntime();
		try {
			System.out.println(cmd);
			Process p = rt.exec(cmd);
			BufferedReader bufferedReader1 = new BufferedReader(
					new InputStreamReader(p.getInputStream(), "GBK"));
			BufferedReader bufferedReader2 = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));

			while ((temp1 = bufferedReader1.readLine()) != null) {
				sb1.append(temp1);
				System.out.println(temp1);
			}

			while ((temp2 = bufferedReader2.readLine()) != null) {
				sb2.append(temp2);
				System.out.println("error:" + temp2);
			}

			p.waitFor();

			return sb1.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb1.toString();
	}

	public static String exec2(String cmd) {
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();		
		StringBuffer sb3 = new StringBuffer();
		String lineStr = "";
		String temp2 = "";
		Runtime rt = Runtime.getRuntime();
		try {
			System.out.println(cmd);
			Process p = rt.exec(cmd);

			BufferedInputStream in = new BufferedInputStream(p.getInputStream());

			BufferedReader bufferedReader1 = new BufferedReader(
					new InputStreamReader(in, "GBK"));
			BufferedReader bufferedReader2 = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));

			while ((lineStr = bufferedReader1.readLine()) != null) {
				sb1.append(lineStr);
				System.out.println(lineStr);
				
//				sb3.append(p.exitValue());
//				System.out.println("exitValue:"+sb3);
				
				System.out.println("===================4=============");
			}
			System.out.println("===================4=============");
			while ((temp2 = bufferedReader2.readLine()) != null) {
				sb2.append(temp2);
				System.out.println("error:" + temp2);
			}

			return sb1.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb1.toString();
	}

	public static void main(String args[]) {
		try {
			String cmd = "cmd   /c   dir   c:";
			cmd = " keytool -import -alias publiccert -file F:/project/license-server/cert\\20120330095338404.cer -keystore F:/project/license-server/cert\\201203281035422591.store -storepass  publicstore123 -validity 3500";
			// cmd+=" y";
			String result = exec2(cmd);

			System.out.println("result:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
