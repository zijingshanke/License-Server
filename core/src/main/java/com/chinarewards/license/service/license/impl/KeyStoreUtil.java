package com.chinarewards.license.service.license.impl;

import java.io.File;
import java.util.Random;

import com.chinarewards.license.util.CmdUtil;
import com.chinarewards.license.util.DateUtil;
import com.chinarewards.license.util.StringUtil;

/**
 * @author yanrui
 * @version 1.0
 */
public class KeyStoreUtil {
//
//	public static String PRIVATE_PATH = "D:\\cert";
//	public static String PUBLIC_PATH = "D:\\cert";

	public static String PRIVATE_STORE_PASS = "	privatestore123";
	public static String PUBLIC_STORE_PASS = "publicstore123";
	public static String PRIVATE_KEY_PASS = "privatekey123";
	public static String D_NAME = "CN=china-rewards CA, OU=CA Center, O=china-rewards corporation, L=SH, ST=GD, C=CN ";

	public static String PRIVATE_KEY_STORE_PATH = "";
	public static String PUBLIC_CERT_STORE_PATH = "";
	public static String PUBLIC_KEY_STORE_PATH="";

	public static void main(String[] args) {

		createPrivateKeyStore();
		exportPublicKeyCert();
		createPublicKeyStore();
		
		System.out.println("========1=======:"+PRIVATE_KEY_STORE_PATH);
		System.out.println("========2=======:"+PUBLIC_CERT_STORE_PATH);
		System.out.println("========3=======:"+PUBLIC_KEY_STORE_PATH);
		
		System.exit(0);
	}

	/**
	 * 创建私钥库
	 * */
	private static String createPrivateKeyStore() {
		// keytool -genkey -alias privatekey -keystore privateKeys.store

		String fileName = DateUtil.getDateString("yyyyMMddHHmmss");
		fileName += new Random().nextInt(1000);
		fileName += ".store";
		
		String filePath= getCertPath()+ File.separator + "privateKeys"+File.separator+fileName;

		String cmd = " keytool -genkey -alias privatekey -keystore " + filePath;
		cmd += " -storepass " + PRIVATE_STORE_PASS;
		cmd += " -keypass " + PRIVATE_KEY_PASS;
		cmd += " -dname \"" + D_NAME + "\"";

		// System.out.println(cmd);
		String result = CmdUtil.exec(cmd);
		System.out.println("private key store result:" + result);

		PRIVATE_KEY_STORE_PATH = filePath;
		return fileName;
	}

	/**
	 * 导出私匙库内的公匙
	 * */
	private static String exportPublicKeyCert() {
		// keytool -export -alias privatekey -file certfile.cer -keystore
		// privateKeys.store
		
		String fileName = DateUtil.getDateString("yyyyMMddHHmmss");
		fileName += new Random().nextInt(1000);
		fileName += ".cer";
		
		String filePath= getCertPath()+File.separator+fileName;

		String cmd = " keytool -export -alias privatekey -file " + filePath
				+ " -keystore " + PRIVATE_KEY_STORE_PATH;
		cmd += " -storepass  " + PRIVATE_STORE_PASS;
		String result = CmdUtil.exec(cmd);
		System.out.println("EXPORT PUBLIC CERT RESULT:" + result);

		PUBLIC_CERT_STORE_PATH = filePath;
		return fileName;
	}

	/**
	 * 生成公钥库
	 * */
	private static String createPublicKeyStore() {
		// keytool -import -alias publiccert -file certfile.cer -keystore
		// publicCerts.store

		String fileName =  DateUtil.getDateString("yyyyMMddHHmmss");
		fileName += new Random().nextInt(1000);
		fileName += ".store";
		
		String filePath= getCertPath()+File.separator+fileName;

		String cmd = " keytool -import -alias publiccert -file "
				+ PUBLIC_CERT_STORE_PATH + " -keystore " + filePath;
		cmd += " -storepass  " + PUBLIC_STORE_PASS;
		

		String result = CmdUtil.exec(cmd);
		
		System.out.println(result);
		
//		CmdUtil.exec(" y ");
		
		PUBLIC_KEY_STORE_PATH=filePath;
		
		System.out.println("====================end---------------");
		
		return fileName;
	}
	
	
	public static String getCertPath() {
		String realPath = "";
		realPath = MyLicenseManager.class.getResource("").getPath();

//		System.out.println(realPath);

		if (!StringUtil.isEmptyString(realPath)) {
			int rootIndex = realPath.indexOf("jboss-5.1.0.GA");
			if (rootIndex < 0) {
				rootIndex = realPath.indexOf("core");
			}

			if (rootIndex < 0) {
				return null;
			} else {
				realPath = realPath.substring(0, rootIndex);
			}

			int firstIndex = realPath.indexOf("/");
			if (firstIndex == 0) {
				realPath = realPath.substring(1, realPath.length());
			}

			realPath = realPath + "cert" + File.separator;
		}

//		System.out.println(realPath);
		return realPath;
	}

}
