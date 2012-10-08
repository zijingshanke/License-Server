package com.chinarewards.gwt.license.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yanrui
 * 
 * */
public class ImageShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
	}

	// 处理post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/gif");
		try {
			String imgName = request.getParameter("imageName");
			if (imgName != null && !"".equals(imgName)) {
				String uploadPath = getUploadPath(request);
				if (uploadPath != null) {
					String imagePath = uploadPath + File.separator + imgName;

					// System.out.println("---imageShow:" + imagePath);

					// 获取图片文件内容并输出
					File file = new File(imagePath);
					if (file.exists()) {
						int fileLength = (int) file.length();
						response.setContentLength(fileLength);

						if (fileLength != 0) {

							/* 创建输入流 */
							InputStream inStream = new FileInputStream(file);
							byte[] buff = new byte[6090];

							/* 创建输出流 */
							ServletOutputStream out = response
									.getOutputStream();

							int readDataLen;
							while (((readDataLen = inStream.read(buff)) != -1)) {
								out.write(buff, 0, readDataLen);
							}
							inStream.close();
							out.flush();
							out.close();
						}
					} else {
						// System.out.println(file.getPath() + "文件找不到,无法输出显示");
					}
				}
			} else {
				System.out.println("fileName imageName 不能为空！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getUploadPath(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		String uploadPath = null;
		// System.out.println("============realPath:" + realPath);
		int rootIndex = realPath.indexOf("jboss-5.1.0.GA");
		if (rootIndex < 0) {
			rootIndex = realPath.indexOf("war");
		}

		if (rootIndex < 0) {
			return null;
		} else {
			realPath = realPath.substring(0, rootIndex);
		}
		
		uploadPath = realPath + "upload";

		return uploadPath;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void destroy() {
	}

}
