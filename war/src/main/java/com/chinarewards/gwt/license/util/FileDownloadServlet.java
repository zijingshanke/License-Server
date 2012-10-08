package com.chinarewards.gwt.license.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinarewards.license.service.license.impl.MyLicenseManager;
import com.chinarewards.license.util.MachineUtil;
import com.chinarewards.license.util.StringUtil;

public class FileDownloadServlet  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String licenseFileName = request.getParameter("licenseFileName");
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachement;filename="
					+ new String(licenseFileName.getBytes("GBK"), "ISO-8859-1"));
			
			File file = new File(getLicenseStorePath() + licenseFileName);
			if (!file.exists()) {
				throw new IOException(licenseFileName + ",所下载的文件不存在!");
			}
			response.setContentLength(Integer.parseInt(file.length() + ""));
			InputStream fs = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] buff = new byte[1024];
			int readCount = 0;
			while ((readCount = fs.read(buff)) != -1) {
				os.write(buff, 0, readCount);
			}
			if (fs != null) {
				fs.close();
			}
			if (os != null) {
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setStatus(response.SC_OK);
		response.flushBuffer();
	}

	public static String getLicenseStorePath() {
		String realPath = "";
		realPath = MyLicenseManager.class.getResource("").getPath();

		System.out.println(realPath);

		if (!StringUtil.isEmptyString(realPath)) {
			int rootIndex = realPath.indexOf("jboss-5.1.0.GA");
			if (rootIndex < 0) {
				rootIndex = realPath.indexOf("elt/war");
				if (rootIndex > -1) {
					rootIndex += 4;
				}
			}
			if (rootIndex < 0) {
				rootIndex = realPath.indexOf("war/war");
				// if (rootIndex>-1) {
				// rootIndex+=4;
				// }
			}
			if (rootIndex < 0) {
				rootIndex = realPath.indexOf("elt/core");
				if (rootIndex > -1) {
					rootIndex += 4;
				}
			}

			if (rootIndex < 0) {
				return null;
			} else {
				realPath = realPath.substring(0, rootIndex);
			}

			int firstIndex = realPath.indexOf("/");
			if (firstIndex == 0) {
				if (MachineUtil.getIsWindowsOS()) {
					realPath = realPath.substring(1, realPath.length());
				}
			}

			realPath = realPath.replace("file:/", "");

			realPath = realPath + "licenseStore" + File.separator;
		}

		System.out.println(realPath);
		return realPath;
	}

}
