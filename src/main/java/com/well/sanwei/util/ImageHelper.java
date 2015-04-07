package com.well.sanwei.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.well.sanwei.enu.FileType;


/**
 * @author Mr.Cactus
 * 图片压缩辅助类
 */
public class ImageHelper {

	private static ImageHelper imageHelper = new ImageHelper();
	private static Logger log = Logger.getLogger(ImageHelper.class);

	private ImageHelper() {
	}

	/**
	 * @return 获取help实例
	 */
	public static ImageHelper getInstance() {
		return imageHelper;
	}

	/**
	 * @param im
	 *            原始图像
	 * @param resizeTimes
	 *            需要缩小的倍数，缩小2倍为原来的1/2 ，这个数值越大，返回的图片越小
	 * @return 返回处理后的图像
	 */
	public BufferedImage resizeImage(BufferedImage im, float resizeTimes) {

		/* 原始图像的宽度和高度 */
		int width = im.getWidth();
		int height = im.getHeight();

		/* 调整后的图片的宽度和高度 */
		int toWidth = (int) (Float.parseFloat(String.valueOf(width)) / resizeTimes);
		int toHeight = (int) (Float.parseFloat(String.valueOf(height)) / resizeTimes);

		/* 新生成结果图片 */
		BufferedImage newImage = new BufferedImage(toWidth, toHeight,
				BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(
				im.getScaledInstance(toWidth, toHeight,
						java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		return newImage;
	}

	/**
	 * @param in
	 * @param resizeTimes
	 * @return 传入图片流
	 */
	public BufferedImage resizeImage(InputStream in, float resizeTimes) {

		BufferedImage im;
		try {
			im = ImageIO.read(in);
			/* 原始图像的宽度和高度 */
			int width = im.getWidth();
			int height = im.getHeight();

			/* 调整后的图片的宽度和高度 */
			int toWidth = (int) (Float.parseFloat(String.valueOf(width)) / resizeTimes);
			int toHeight = (int) (Float.parseFloat(String.valueOf(height)) / resizeTimes);

			/* 新生成结果图片 */
			BufferedImage newImage = new BufferedImage(toWidth, toHeight,
					BufferedImage.TYPE_INT_RGB);
			newImage.getGraphics().drawImage(
					im.getScaledInstance(toWidth, toHeight,
							java.awt.Image.SCALE_SMOOTH), 0, 0, null);
			return newImage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param im
	 *            原始图像
	 * @param resizeTimes
	 *            倍数,比如0.5就是缩小一半,0.98等等double类型
	 * @return 返回处理后的图像
	 */
	public BufferedImage zoomImage(BufferedImage im, float resizeTimes) {
		/* 原始图像的宽度和高度 */
		int width = im.getWidth();
		int height = im.getHeight();
		/* 调整后的图片的宽度和高度 */
		int toWidth = (int) (Float.parseFloat(String.valueOf(width)) * resizeTimes);
		int toHeight = (int) (Float.parseFloat(String.valueOf(height)) * resizeTimes);

		/* 新生成结果图片 */
		BufferedImage newImage = new BufferedImage(toWidth, toHeight,
				BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(
				im.getScaledInstance(toWidth, toHeight,
						java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		return newImage;
	}

	/**
	 * @param im
	 * @param toWidth
	 *            期望宽度
	 * @param toHeight
	 *            期望宽度
	 * @return 返回缩小到指定大小的图像
	 */
	public BufferedImage zoomImage(BufferedImage im, int toWidth, int toHeight) {
		/* 新生成结果图片 */
		BufferedImage newImage = new BufferedImage(toWidth, toHeight,
				BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(
				im.getScaledInstance(toWidth, toHeight,
						java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		return newImage;
	}

	/**
	 * @param in
	 *            输入流
	 * @param toWidth
	 *            指定宽度
	 * @param toHeight
	 *            指定高度
	 * @param fileType 
	 *            文件类型
	 * @param fileName
	 *            文件名称
	 * @return 成功写入磁盘返回true
	 */
	public boolean resizeImageThenWriteToDisk(InputStream in, int toWidth,
			int toHeight, FileType fileType, String fileName) {
		BufferedImage im;
		try {
			im = ImageIO.read(in);
			// 原图的宽
			int orginalWidth = im.getWidth();
			// 原图的高
			int orginalHeight = im.getHeight();
			// 压缩比例
			int targetWidth = toWidth;
			int targetHeight = toHeight;
			// 如果高大于宽
			if (orginalHeight > orginalWidth) {
				// 目标高度
				if (orginalHeight < toHeight) {
					targetHeight = orginalHeight;
				}
				// 目标宽度
				targetWidth = targetHeight * orginalWidth / orginalHeight;
			} else// 如果宽大于高
			{
				if (orginalWidth < toWidth) {
					targetWidth = orginalWidth;
				}
				// 首先根据宽度计算高度
				int tempHeight = orginalHeight * targetWidth / orginalWidth;
				// 如果计算后的高度大于toHeight
				if (tempHeight > toHeight) {
					targetHeight = toHeight;
					targetWidth = toHeight * targetWidth / tempHeight;
				} else {
					// 否则目标高度==tempheight
					targetHeight = tempHeight;
				}
			}
			/* 新生成结果图片 */
			BufferedImage newImage = new BufferedImage(targetWidth,
					targetHeight, BufferedImage.TYPE_INT_RGB);
			newImage.getGraphics().drawImage(
					im.getScaledInstance(targetWidth, targetHeight,java.awt.Image.SCALE_SMOOTH), 0, 0, null);
			if (fileType == null) {
				return false;
			}
			File file = new File(FileUtils.absoluteFileTypeDir(fileType)+"/" + fileName+fileType.name());
			ImageIO.write(newImage, fileType.name(), file);
			im.flush();
			in.close();
			return true;
		} catch (IOException e) {
			log.error("压缩图片失败", e);
			return false;
		}
	}

	/**
	 * 把图片写到本地磁盘上
	 * 
	 * @param im
	 * @param path
	 *            图片写入的文件夹地址
	 * @param fileName
	 *            写入图片的名字
	 * @return
	 */
	public boolean writeToDisk(BufferedImage im, String path, String fileName) {
		File f = new File(path + "/" + fileName);
		String fileType = getExtension(fileName);
		if (fileType == null)
			return false;
		try {
			ImageIO.write(im, fileType, f);
			im.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 返回文件的文件后缀名(不包含.)
	 * @param fileName
	 * @return
	 */
	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}

}
