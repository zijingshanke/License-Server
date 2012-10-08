package com.chinarewards.gwt.license.util;

import java.awt.*;
import java.io.*;
import java.awt.image.*;
import com.sun.image.codec.jpeg.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.io.*;

/**
 * java图片压缩处理
 * */
public class ImageDisposeUtil {
	private static final Log logger = LogFactory.getLog(ImageDisposeUtil.class);

	public ImageDisposeUtil() {
	}

	// 引用
	// ImageDisposeUtil.reduceImg(this.getUpload().getPath(), dstPath, 200, 180,
	// 0);
	// 本地文件的地址：this.getUpload().getPath()
	// 上传文件的地址：dstPath

	/**
	 * @param imgsrc  本地图片路径 
	 * @param imgdist 上传服务器路径 
	 * @param widthdist 压缩宽度 
	 * @param heightdist 压缩高度
	 * @param int benchmark 说明:0,长宽哪个长，以哪个为标准；1，以宽为基准；2，以高为基准
	 * 
	 */
	public static void reduceImg(String imgsrc, String imgdist, int widthdist,
			int heightdist, int benchmark) {

		try {
			// System.out.println("*******widthdist********:"+widthdist);
			// System.out.println("*******heightdist********:"+heightdist);
			// System.out.println("*******benchmark********:"+benchmark);
			File srcfile = new File(imgsrc);
			if (!srcfile.exists()) {
				return;
			}
			Image src = javax.imageio.ImageIO.read(srcfile);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			if (width <= widthdist && height <= heightdist) {
				// SysUtil.cpoyFile(imgsrc, imgdist);
				FileUtils.copyFile(new File(imgsrc), new File(imgdist));
				return;
			}
			// 宽度除以高度的比例
			float wh = (float) width / (float) height;
			if (benchmark == 0) {
				if (wh > 1) {
					float tmp_heigth = (float) widthdist / wh;
					BufferedImage tag = new BufferedImage(widthdist,
							(int) tmp_heigth, BufferedImage.TYPE_INT_RGB);
					tag.getGraphics().drawImage(src, 0, 0, widthdist,
							(int) tmp_heigth, null);
					FileOutputStream out = new FileOutputStream(imgdist);
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					encoder.encode(tag);
					out.close();
				} else {
					float tmp_width = (float) heightdist * wh;
					BufferedImage tag = new BufferedImage((int) tmp_width,
							heightdist, BufferedImage.TYPE_INT_RGB);
					tag.getGraphics().drawImage(src, 0, 0, (int) tmp_width,
							heightdist, null);
					FileOutputStream out = new FileOutputStream(imgdist);
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					encoder.encode(tag);
					out.close();
				}
			}
			if (benchmark == 1) {
				float tmp_heigth = (float) widthdist / wh;
				BufferedImage tag = new BufferedImage(widthdist,
						(int) tmp_heigth, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(src, 0, 0, widthdist,
						(int) tmp_heigth, null);
				FileOutputStream out = new FileOutputStream(imgdist);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
			if (benchmark == 2) {
				float tmp_width = (float) heightdist * wh;
				BufferedImage tag = new BufferedImage((int) tmp_width,
						heightdist, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(src, 0, 0, (int) tmp_width,
						heightdist, null);
				FileOutputStream out = new FileOutputStream(imgdist);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
		} catch (IOException ex) {
			logger.error(ex);
		}
	}
}
