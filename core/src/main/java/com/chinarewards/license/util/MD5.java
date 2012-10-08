package com.chinarewards.license.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
	public MD5() {
	}

	public  String MD5(String sInput) throws Exception {

		String algorithm = "";
		// 输入不能为空
		if (sInput.trim() == null) {
			return "null";
		}

		// 指定采用MD5算法
		try {
			algorithm = System.getProperty("MD5.algorithm", "MD5");
		} catch (SecurityException se) {
		}

		// 定义MessageDigest对象
		MessageDigest md = MessageDigest.getInstance(algorithm);

		// 按照系统缺省的字符编码方式把sInput 转换成字节，并把结果存到一新的字节数组buffer中
		byte buffer[] = sInput.getBytes();

		// 从指定的字节数组buffer的偏移量0开始，用指定的字节数组修改由sInput生成摘要
		// count为从 0 开始用的字节数长度。
		for (int count = 0; count < sInput.length(); count++) {
			md.update(buffer, 0, count);
		}

		// 通过执行最后的诸如填充的操作完成散列码的计算。 在调用之后复位该摘要
		// 返回存放结果散列值的字节数组bDigest
		byte bDigest[] = md.digest();

		// 将bDigest转换为大整数bi
		BigInteger bi = new BigInteger(bDigest);

		// 返回bi字符串表示，即最终的编码结果
		// System.out.println("-----" + bi.toString(16));
		return (bi.toString(16));
	}

	public boolean divideMD5(String serverCode, String clientCode) {
		MD5 m = new MD5();
		boolean md = false;
		String sCode = "";
		try {
			sCode = m.MD5(serverCode);
			System.out.println("*****" + sCode);
		} catch (Exception ex) {
		}
		if (sCode.equals(clientCode)) {
			md = true;
		}
		return md;
	}
}
