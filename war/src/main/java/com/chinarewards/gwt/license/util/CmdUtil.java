package com.chinarewards.gwt.license.util;

import java.io.*;

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
			Process p = rt.exec(cmd);
			BufferedReader bufferedReader1 = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			BufferedReader bufferedReader2 = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));

			while ((temp1 = bufferedReader1.readLine()) != null) {
				sb1.append(temp1);
				System.out.println(temp1);
			}

			while ((temp2 = bufferedReader2.readLine()) != null) {
				sb2.append(temp2);
				System.out.println(temp2);
			}
			p.waitFor();
			return sb1.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb1.toString();
	}

	public static void main(String args[]) {
		try {
			System.out.println((new StringBuilder("out:")).append(
					exec("cmd   /c   dir   c:")).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
