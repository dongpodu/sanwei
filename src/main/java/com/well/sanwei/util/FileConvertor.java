package com.well.sanwei.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.well.sanwei.enu.FileType;

/**
 * 需要安装OpenOffice软件
 * 开启OpenOffice服务命令：soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
 * 需要安装pdf2swf到c盘，其安装地址为：C://Program Files/SWFTools/pdf2swf.exe
 * 需安装ffmpeg到C:/Program Files/ffmpeg/bin/ffmpeg.exe
 * @author duyisong
 *
 */
public class FileConvertor {
	// connect to an OpenOffice.org instance running on port 8100
	private static OpenOfficeConnection connection = 
			new SocketOpenOfficeConnection(8100);
	private static DefaultDocumentFormatRegistry documentFormat = 
			new DefaultDocumentFormatRegistry();
	/**
	 * pdf2swf的安装地址
	 */
	private static final String PDF2SWF_PATH="C://Program Files/SWFTools/pdf2swf.exe";
	private static final String FFMPEG_PATH="C:/Program Files/ffmpeg/bin/ffmpeg.exe";
	private static Logger log = Logger.getLogger(FileConvertor.class);

	/**
	 * 将office转换为swf，返回值是swf文件的路径
	 * @param fileType  被转换的文件类型
	 * @param input  office输入流
	 * @param fileName  文件名（不加后缀）
	 * @throws Exception
	 */
	public static String convertOffice2SWF(FileType fileType,InputStream input,String fileName) 
			throws Exception{
		if(StringUtils.isBlank(fileName)
				|| StringUtils.isBlank(fileName)
				||  input==null){
			throw new IllegalArgumentException("参数fileType、input、fileName均不能为空，" +
					"fileType:"+fileType+";input:" + input+ ";fileName:"+fileName);
		}
		String pdfPath = FileUtils.absoluteFileTypeDir(FileType.pdf)+"/"+fileName+".pdf";
		File file = new File(pdfPath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdir();
		}
		FileOutputStream out = new FileOutputStream(file);
		boolean convertPdfOK = convertOffice2PDF(fileType, input, out);  //转换为pdf并保存到特定的路径下
		String outPath = FileUtils.absoluteFileTypeDir(FileType.swf)+"/"+fileName+".swf";
		if(convertPdfOK){
			convertPdf2SWF(pdfPath,outPath);
		}
		return FileUtils.relativeFilePath(FileType.swf, fileName);
	}
	
	/**
	 * 转换文件为pdf格式的
	 * 
	 * @param fileType
	 *            文件类型
	 * @param input
	 *            文件输入流
	 * @param out
	 *            文件输出流
	 * @throws IOException 
	 */
	public static boolean convertOffice2PDF(FileType fileType, InputStream input,
			OutputStream out) throws IOException{
		if (!FileUtils.checkOffice(fileType.name())) {
			throw new IllegalArgumentException("不支持" + fileType + "转换为pdf！");
		}
		DocumentFormat inputFormat = documentFormat
				.getFormatByFileExtension(fileType.name());
		DocumentFormat pdf = documentFormat.getFormatByFileExtension("pdf");
		DocumentConverter converter = new OpenOfficeDocumentConverter(
				connection);
		converter.convert(input, inputFormat, out, pdf);
		input.close();
		out.close();
		closeConnection();
		return true;
	}

	/**
	 * 将pdf转换为swf，并保存到特定的路径中，返回值是swf文件的绝对路径
	 * @param input  office文档输入流
	 * @param fileName  文件名（ 不带后缀）
	 * @return
	 * @throws Exception
	 */
	public static String convertPdf2SWF(InputStream input, String fileName) throws Exception {  
		if(input==null || StringUtils.isBlank(fileName)){
			throw new IllegalArgumentException("参数input和fileName均不能为空，input:" + input+ ";fileName:"+fileName);
		}
		//先将输入流写到指定地址
		String inputPath = FileUtils.absoluteFileTypeDir(FileType.pdf)+"/"+fileName+".pdf";
		File file = new File(inputPath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdir();
		}
		byte[] buffer = new byte[1024*8];
		int i=-1;
		FileOutputStream out = new FileOutputStream(file);
		while((i=input.read(buffer))!=-1){
			out.write(buffer, 0, i);
		}
		//转换pdf为swf
		String outPath = FileUtils.absoluteFileTypeDir(FileType.swf)+"/"+fileName+".swf";;
		convertPdf2SWF(inputPath, outPath);
		input.close();
		out.close();
		return FileUtils.relativeFilePath(FileType.swf, fileName);
	}
	/**
	 * 转换pdf文件为SWF
	 * @param sourceFile
	 * @param destFile
	 * @return
	 * @throws Exception
	 */
	public static boolean convertPdf2SWF(String sourceFile, String destFile) throws Exception {
		  
        // 目标路径不存在则建立目标路径  
        File dest = new File(destFile);  
        if (!dest.getParentFile().exists())  
            dest.getParentFile().mkdirs();  
  
        // 源文件不存在则返回 -1  
        File source = new File(sourceFile);  
        if (!source.exists())  
            return false;  
  
        /* 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'  
     	 *调用pdf2swf命令进行转换swfextract -i - sourceFilePath.pdf -o destFilePath.swf  
         */
        String command =  PDF2SWF_PATH + " -i " + sourceFile + " -o "  
                + destFile+" -s flashversion=9";  
        Process pro = Runtime.getRuntime().exec(command);
        new PrintStream(pro.getInputStream()).start();
        pro.waitFor();
        boolean ok = processOk(pro);
        pro.destroy();
        return ok;
    }  
	
	/**
	 * 转换视频为flv格式
	 * @param sourceFile
	 * @param destFile
	 * @return
	 * @throws Exception
	 */
	public static boolean convert2Flv(String sourceFile, String destFile) throws Exception{
		 // 目标路径不存在则建立目标路径  
       File dest = new File(destFile);  
       if (!dest.getParentFile().exists())  
           dest.getParentFile().mkdirs();  
 
       // 源文件不存在则返回 -1  
       File source = new File(sourceFile);  
       if (!source.exists())  
           return false;  
 
       List<String> command = new ArrayList<String>();
       command.add(FFMPEG_PATH);command.add("-i");
       command.add(sourceFile);command.add("-ab");
       command.add("56");command.add("-ar");
       command.add("22050");command.add("-qscale");
       command.add("8");command.add("-r"); command.add("15");
       command.add("-s");command.add("600x500");
       command.add(destFile);
       Process pro = new ProcessBuilder(command).redirectErrorStream(true).start();  
       new PrintStream(pro.getInputStream()).start();
       pro.waitFor();
       boolean ok = processOk(pro);
       pro.destroy();
       return ok;
	}
	
	
	/**
	 * 设置图片大小
	 * @param in
	 * @param toWidth
	 * @param toHeight
	 * @param fileType
	 * @param fileName
	 * @return
	 */
	public static boolean resizeImageAndSave(File in, int toWidth,
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
			File file = new File(FileUtils.absoluteFileTypeDir(fileType)+"/" + fileName+"."+fileType.name());
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdir();
			}
			ImageIO.write(newImage, fileType.name(), file);
			im.flush();
			return true;
		} catch (IOException e) {
			log.error("压缩图片失败", e);
			return false;
		}
	}
	
	/**
	 * cmd是否至执行成功
	 * @param pro
	 * @return
	 */
	public static boolean processOk(final Process pro){  
            boolean ok=true;
            if(pro.getErrorStream()!=null){
            	ok=false;
            }
            return ok;
    }  
      
	/**
	 * 关闭连接
	 */
	public static void closeConnection() {
		if (connection != null && connection.isConnected()) {
			connection.disconnect();
		}
	}

	public static void main(String[] args) throws Exception {
//		convertOffice2SWF(FileType.doc, new FileInputStream("E://杜义松简历.doc"), "杜义松简历");
//		convertOffice2PDF(FileType.doc, new FileInputStream("E:\\software\\apache-tomcat-6.0.41\\webapps\\RESOURCE\\doc\\1412821485265.doc"),
//				new FileOutputStream("E:\\software\\apache-tomcat-6.0.41\\webapps\\RESOURCE\\doc\\1412821485265.pdf"));
		resizeImageAndSave(new File("E://1111.jpg"), 173, 130, FileType.jpg, "ddddd");
//		convertOffice2PDF(FileType.doc, new FileInputStream("E:\\software\\apache-tomcat-6.0.41\\webapps\\RESOURCE\\doc\\1412821485265.doc"),
//				new FileOutputStream("E:\\software\\apache-tomcat-6.0.41\\webapps\\RESOURCE\\doc\\1412821485265.pdf"));
//		resizeImageAndSave(new FileInputStream("E://1111.jpg"), 173, 130, FileType.jpg, "ddddd");
		convert2Flv("G:/fff.f4v", "G:/111.flv");
	}
}


/**
 * 读出子进程的输入流，避免阻塞父进程
 * @author duyisong
 *	@date  2014-10-10
 */
final class PrintStream extends Thread{
	InputStream in = null;
	public PrintStream(InputStream is){
		in = is;
	} 
	public void run() {
		try{
			while(this != null) {
				int _ch = in.read();
				if(_ch != -1){
					System.out.print((char)_ch); 
				}
				else break;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
