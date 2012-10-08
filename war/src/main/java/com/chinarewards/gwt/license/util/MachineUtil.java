package com.chinarewards.gwt.license.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MachineUtil {

	public static boolean getIsWindowsOS() {
		String osName = getOSName();
		System.out.println(osName);
		if (osName != null) {
			if (osName.toLowerCase().startsWith(("Windows").toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	public static String getMACAddress() {

		String address = "";
		String os = System.getProperty("os.name");
		System.out.println(os);
		if (os != null) {
			if (os.startsWith("Windows")) {
				try {
					ProcessBuilder pb = new ProcessBuilder("ipconfig", "/all");
					Process p = pb.start();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(p.getInputStream(), "GBK"));

					String line;
					while ((line = br.readLine()) != null) {
						System.out.println(line);
						if (line.toLowerCase().indexOf(
								("Physical Address").toLowerCase()) != -1) {
							System.out
									.println(" ====================================find Physical Address flag");
							int index = line.indexOf(":");
							address = line.substring(index + 1);
							break;
						}
						if (line.indexOf("物理地址") != -1) {
							int index = line.indexOf(":");
							address = line.substring(index + 1);
							break;
						}
					}
					br.close();
					return address.trim();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (os.startsWith("Linux")) {
				try {
					ProcessBuilder pb = new ProcessBuilder("ifconfig");
					Process p = pb.start();

					BufferedReader br = new BufferedReader(
							new InputStreamReader(p.getInputStream(), "UTF-8"));

					String line;
					while ((line = br.readLine()) != null) {

						int index = line.indexOf("硬件地址");// 中文的
						if (index != -1) {
							address = line.substring(index + 4);
							break;
						}
						index = line.toLowerCase().indexOf("hwaddr");// 英文
						if (index >= 0) {// 找到了
							address = line.substring(index + 6).trim();// 取出mac地址并去除2边空格
							break;
						}
					}
					br.close();
					return address.trim();
				} catch (IOException ex) {
					Logger.getLogger(MachineUtil.class.getName()).log(
							Level.SEVERE, null, ex);
				}

			}
		}
		return address;
	}

	public static void main(String[] args) {
//		System.out.println("" + getMACAddress());
		System.out.println(getIsWindowsOS());
	}
}