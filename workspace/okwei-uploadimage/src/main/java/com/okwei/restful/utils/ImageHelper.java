package com.okwei.restful.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.alibaba.simpleimage.ImageFormat;
import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteParameter;
import com.alibaba.simpleimage.render.WriteRender;
import com.okwei.restful.enums.ImgTypeEnum;

import sun.misc.BASE64Decoder;

public class ImageHelper {

	// 图片临时文件
	public static String upLoadTemp = AppSettingUtil.getSingleValue("imgPathTemp");

	/**
	 * 保存到临时区
	 * 
	 * @param imgStr
	 * @param itype
	 * @return
	 * @throws Exception
	 */
	public static String saveToStack(String imgData, int itype) throws Exception {
		String imgBasenew = upLoadTemp;
		String uploadPath = DateOperate.getTimeString("yyyyMM") + "/";
		// 对字节数组字符串进行Base64解码并生成图片
		if (imgData == null || "".equals(imgData)) // 图像数据为空
			return "";
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] b = decoder.decodeBuffer(imgData);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) // 调整异常数据
			{
				b[i] += 256;
			}
		}
		// 生成jpeg图片
		String newFileName = DateOperate.getTimeString("yyyyMMddhhmmssSSS") + ".JPG";// 新生成的图片
		File f = new File(imgBasenew + uploadPath);
		if (!f.isDirectory()) {
			f.mkdir();
		}
		OutputStream out = new FileOutputStream(imgBasenew + uploadPath + newFileName);
		out.write(b);
		out.flush();
		out.close();
		return imgBasenew + uploadPath + newFileName;
	}

	/**
	 * 图片多规格切割、压缩
	 * 
	 * @param filePath
	 * @param type
	 * @return
	 */
	public static List<String> imglistPath(String filePath, int type) {
		List<String> resultList = new ArrayList<String>();
		String tailer = filePath.substring(filePath.lastIndexOf(".") + 1);
		String head = filePath.substring(0, filePath.lastIndexOf("."));
		if (type == Integer.parseInt(ImgTypeEnum.Photo.toString()) || type == Integer.parseInt(ImgTypeEnum.Product.toString())) {
			imgCutSave(filePath, head + "_80." + tailer, 80, 60);
			resultList.add(head + "_80." + tailer);
			imgCutSave(filePath, head + "_75." + tailer, 750, 750);
			resultList.add(head + "_75." + tailer);
			imgCutSave(filePath, head + "_32." + tailer, 320, 320);
			resultList.add(head + "_32." + tailer);
			imgCutSave(filePath, head + "_16." + tailer, 160, 160);
			resultList.add(head + "_16." + tailer);
			imgCutSave(filePath, head + "_24." + tailer, 240, 240);
			resultList.add(head + "_24." + tailer);
			imgCutSave(filePath, head + "_12." + tailer, 120, 120);
			resultList.add(head + "_12." + tailer);
			imgCutSave(filePath, head + "_8." + tailer, 80, 80);
			resultList.add(head + "_8." + tailer);
		} else {
			scale_ali(filePath, head + "_1." + tailer);
			scale_ali(filePath, head + "_75." + tailer);
			imgCutSave(filePath, head + "_24." + tailer, 240, 240);
			imgCutSave(filePath, head + "_12." + tailer, 120, 120);
			
			resultList.add(head + "_1." + tailer);
			resultList.add(head + "_75." + tailer);
			resultList.add(head + "_24." + tailer);
			resultList.add(head + "_12." + tailer);
		}
		return resultList;
	}

	/**
	 * 图片等比例压缩保存
	 * 
	 * @param srcImageFile
	 *            图片源 地址
	 * @param pathNew
	 *            图片新地址
	 * @param size
	 *            （原图的缩小比例 ps:小数）
	 */
	public static void imgScale(String srcImageFile, String pathNew, double size) {
		try {
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			double srcWidth = bi.getWidth(); // 源图宽度
			double srcHeight = bi.getHeight(); // 源图高度
			srcWidth = srcWidth * size;
			srcHeight = srcHeight * size;
			// 压缩处理
			scale_ali(srcImageFile, pathNew, srcWidth, srcHeight);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 图片等比例压缩保存
	 * 
	 * @param srcImageFile
	 * @param pathNew
	 * @param size
	 *            （原图的缩小比例）
	 */
	public static void imgScale(String srcImageFile, String pathNew, double size, double minSize) {
		try {
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			double srcWidth = bi.getWidth(); // 源图宽度
			double srcHeight = bi.getHeight(); // 源图高度
			srcWidth = srcWidth * size;
			srcHeight = srcHeight * size;
			if (srcHeight < minSize && srcWidth < minSize) {
				double temp = srcHeight / srcWidth;
				if (temp > 1) {
					srcHeight = minSize;
					srcWidth = minSize / temp;
				} else {
					srcWidth = minSize;
					srcHeight = minSize * temp;
				}
			}
			// 压缩处理
			scale_ali(srcImageFile, pathNew, srcWidth, srcHeight);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 图片压缩切割
	 * 
	 * @param srcImageFile
	 * @param pathNew
	 *            新保存的文件名
	 * @param cutWidth
	 *            裁剪后的宽
	 * @param cutHeight
	 *            裁剪后的高
	 */
	public static void imgCutSave(String srcImageFile, String pathNew, double cutWidth, double cutHeight) {
		try {
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			double srcWidth = bi.getWidth(); // 源图宽度
			double srcHeight = bi.getHeight(); // 源图高度
			double tem = srcWidth / srcHeight;
			double aWidth = 0, aHeight = 0;// 成功压缩的图片 宽、高（原图比例）
			if (cutWidth <= srcWidth || cutHeight <= srcHeight)// 小于原图宽度
			{
				if (tem > 1) {
					aHeight = cutHeight;
					aWidth = cutHeight * tem;
				} else {
					aWidth = cutWidth;
					aHeight = cutWidth / tem;
				}
			} else {
				aWidth = srcWidth;
				aHeight = srcHeight;
			}
			// 压缩处理
			scale_ali(srcImageFile, pathNew, aWidth, aHeight);
			// 裁剪
			cut(pathNew, cutWidth, cutWidth);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 图片 按指定宽高进行 等比例 压缩
	 * 
	 * @param srcImageFile
	 * @param standardWidth
	 * @param standardHeight
	 */
	// private static void scale(String srcImageFile, String pathNew, double
	// standardWidth, double standardHeight) {
	// try {
	// BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
	// Image image = src.getScaledInstance((int) standardWidth, (int)
	// standardHeight, Image.SCALE_DEFAULT);
	// BufferedImage tag = new BufferedImage((int) standardWidth, (int)
	// standardHeight, BufferedImage.TYPE_INT_RGB);
	// Graphics g = tag.getGraphics();
	// g.drawImage(image, 0, 0, null);
	// g.dispose();
	// ImageIO.write(tag, "JPG", new File(pathNew));// 输出到文件流
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }


	/**
	 * 图片压缩（来自阿里巴巴控件）
	 * 
	 * @param srcImageFile
	 * @param pathNew
	 * @param standardWidth
	 * @param standardHeight
	 */
	public static void scale_ali(String srcImageFile, String pathNew, double standardWidth, double standardHeight) {
		File in = new File(srcImageFile); // 原图片
		File out = new File(pathNew); // 目的图片
		ScaleParameter scaleParam = new ScaleParameter((int) standardWidth, (int) standardHeight); // 将图像缩略到1024x1024以内，不足1024x1024则不做任何处理
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		WriteRender wr = null;
		try {
			inStream = new FileInputStream(in);
			outStream = new FileOutputStream(out);
			ImageRender rr = new ReadRender(inStream);
			ImageRender sr = new ScaleRender(rr, scaleParam);
			wr = new WriteRender(sr, outStream);
			wr.render(); // 触发图像处理
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inStream); // 图片文件输入输出流必须记得关闭
			IOUtils.closeQuietly(outStream);
			if (wr != null) {
				try {
					wr.dispose(); // 释放simpleImage的内部资源
				} catch (SimpleImageException ignore) {
					// skip ...
				}
			}
		}
	}

	/**
	 * 图片 原理比 压缩 （来自阿里巴巴控件）
	 * 
	 * @param srcImageFile
	 * @param pathNew
	 */
	public static void scale_ali(String srcImageFile, String pathNew) {
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		WriteRender wr = null;
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			File in = new File(srcImageFile); // 原图片
			File out = new File(pathNew); // 目的图片
			ScaleParameter scaleParam = new ScaleParameter(src.getWidth(), src.getHeight()); // 将图像缩略到1024x1024以内，不足1024x1024则不做任何处理
			inStream = new FileInputStream(in);
			outStream = new FileOutputStream(out);
			ImageRender rr = new ReadRender(inStream);
			ImageRender sr = new ScaleRender(rr, scaleParam);

			long size = in.length() / 1024;
			if (size > 100) {//需要进行质量压缩
				WriteParameter iwp = new WriteParameter();
				iwp.setDefaultQuality(0.7f);
				System.out.println(size);
				wr = new WriteRender(sr, outStream,ImageFormat.JPEG,iwp);
			} else {
				wr = new WriteRender(sr, outStream,ImageFormat.JPEG);
			}
			wr.render(); // 触发图像处理
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inStream); // 图片文件输入输出流必须记得关闭
			IOUtils.closeQuietly(outStream);
			if (wr != null) {
				try {
					wr.dispose(); // 释放simpleImage的内部资源
				} catch (SimpleImageException ignore) {
					// skip ...
				}
			}
		}
	}

	/**
	 * 原比例压缩图片
	 * 
	 * @param srcImageFile
	 * @param pathNew
	 */
//	private static void scale_ali(String srcImageFile, String pathNew) {
//		try {
//			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
//			Image image = src.getScaledInstance(src.getWidth(), src.getHeight(), Image.SCALE_DEFAULT);
//			BufferedImage tag = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
//			Graphics g = tag.getGraphics();
//			g.drawImage(image, 0, 0, null);
//			g.dispose();
//			ImageIO.write(tag, "JPG", new File(pathNew));// 输出到文件流
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 图片切割
	 * 
	 * @param srcImageFile
	 * @param standardWidth
	 * @param standardHeight
	 */
	private static void cut(String srcImageFile, double standardWidth, double standardHeight) {
		try {
			Image img;
			ImageFilter cropFilter;
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			if (standardHeight >= srcHeight)
				standardHeight = srcHeight;
			if (standardWidth >= srcWidth)
				standardWidth = srcWidth;
			if (srcWidth >= standardWidth && srcHeight >= standardHeight) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				int w = 0;
				int h = 0;
				double wScale = srcWidth / standardWidth;
				double hScale = srcHeight / standardHeight;
				int srcWidth2;
				int srcHeight2;
				if (wScale > hScale) {
					srcWidth2 = (int) (standardWidth * hScale);
					w = (srcWidth - srcWidth2) / 2;
					srcWidth = srcWidth2;
					h = 0;
				} else {
					srcHeight2 = (int) (standardHeight * wScale);
					h = (srcHeight - srcHeight2) / 2;
					srcHeight = srcHeight2;
					w = 0;
				}
				cropFilter = new CropImageFilter(w, h, srcWidth, srcHeight);
				img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose();
				ImageIO.write(tag, "JPG", new File(srcImageFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// =====================================================================

	/**
	 * 上传网络图片 返回微店网可用图片地址
	 * 
	 * @param url
	 *            （如 http://wx.qlogo.cn/mmopen/
	 *            iby9JOqq5yWf4AtPAO0aPrLiaEWdE2AJtksu9uAutonEobiaeWeXf4aDKmdvvHktSwqK7WYj6V0naLX7ZblzDSTMgtfLf0dPulL
	 *            /0）
	 * @return
	 */
	public static String upImgReturnSrc(String url) {
		String imgSrc = "";
		try {
			byte[] btImg = getImageFromNetByUrl(url);
			if (null != btImg && btImg.length > 0) {
				String fileName = DateOperate.getTimeString("yyyyMMddhhmmssSSS") + "_1.JPG";// 新生成的图片
				imgSrc = writeImageToDisk(btImg, fileName);
			}
		} catch (Exception e) {
			// TODO: handle exception
			imgSrc = "";
		}
		return imgSrc;
	}

	/**
	 * 将图片写入到磁盘
	 * 
	 * @param img
	 *            图片数据流
	 * @param fileName
	 *            文件保存时的名称
	 */
	public static String writeImageToDisk(byte[] img, String fileName) throws Exception {

		String imgBasenew = AppSettingUtil.getSingleValue("imgUploadBase");
		String uploadPath = "Images/" + DateOperate.getTimeString("yyyyMM") + "/";
		File file = new File(imgBasenew + uploadPath);
		if (!file.isDirectory()) {
			// f.mkdirs();
			file.mkdir();
		}
		FileOutputStream fops = new FileOutputStream(imgBasenew + uploadPath + fileName);
		fops.write(img);
		fops.flush();
		fops.close();
		return ImgDomain.GetFullImgUrl(uploadPath + fileName);

	}

	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) throws Exception {
		URL url = new URL(strUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
		byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
		return btImg;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}
