package com.well.sanwei.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 需要安装OpenOffice软件
 * 开启OpenOffice服务命令：soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
 * 需要安装pdf2swf到c盘，其安装地址为：C://Program Files/SWFTools/pdf2swf.exe
 * @author duyisong
 *
 */
public class FileConvertor1 {
	// connect to an OpenOffice.org instance running on port 8100
	private static OpenOfficeConnection connection = 
			new SocketOpenOfficeConnection(8100);
	private static DefaultDocumentFormatRegistry documentFormat = 
			new DefaultDocumentFormatRegistry();
	/**
	 * pdf2swf的安装地址
	 */
	private static final String PDF2SWF_PATH="C://Program Files/SWFTools/pdf2swf.exe";
	private static final String FILE_DIR="C://file/";

	/**
	 * 将office转换为swf，返回值是swf文件的路径
	 * @param fileType  被转换的文件类型
	 * @param input  输入流
	 * @param destination  目的文件名（不加后缀）
	 * @throws Exception
	 */
	public static String convertOffice2SWF(String fileType,InputStream input,String outFileName) 
			throws Exception{
		if(StringUtils.isBlank(outFileName)){
			return "";
		}
		File file = new File(FILE_DIR+outFileName+".pdf");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdir();
		}
		FileOutputStream out = new FileOutputStream(file);
		boolean convertPdfOK = convertOffice2PDF("doc", input, out);
		String outPath = FILE_DIR+outFileName+".swf";
		if(convertPdfOK){
			String inputPath = FILE_DIR+outFileName+".pdf";
			convertPdf2SWF(inputPath,FILE_DIR+outFileName+".swf");
		}
		out.close();
		return outPath;
	}
	/**
	 * 转换文件为pdf格式的
	 * 
	 * @param fileType
	 *            文件类型
	 * @param input
	 *            文件输入流
	 * @param output
	 *            文件输出流
	 * @throws IOException 
	 */
	public static boolean convertOffice2PDF(String fileType, InputStream input,
			OutputStream output) throws IOException {
		if (!FileUtils.checkOffice(fileType)) {
			throw new IllegalArgumentException("不支持" + fileType + "转换为pdf！");
		}
		DocumentFormat inputFormat = documentFormat
				.getFormatByFileExtension(fileType);
		DocumentFormat pdf = documentFormat.getFormatByFileExtension("pdf");
		DocumentConverter converter = new OpenOfficeDocumentConverter(
				connection);
		converter.convert(input, inputFormat, output, pdf);
		input.close();
		return true;
	}

	/**
	 * 将pdf转换为swf，返回值是swf文件的路径
	 * @param input
	 * @param outFileName
	 * @return
	 * @throws Exception
	 */
	public static String convertPdf2SWF(InputStream input, String outFileName) throws Exception {  
		if(input==null || StringUtils.isBlank(outFileName)){
			return "";
		}
		//先将输入流写到指定地址
		byte[] buffer = new byte[1024*8];
		int i;
		File file = new File(FILE_DIR+outFileName+".pdf");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdir();
		}
		FileOutputStream out = new FileOutputStream(file);
		while((i=input.read(buffer))!=-1){
			out.write(buffer, 0, i);
		}
		//转换pdf为swf
		String inputPath = FILE_DIR+outFileName+".pdf";
		String outPath = FILE_DIR+outFileName+".swf";
		convertPdf2SWF(inputPath, outPath);
		out.close();
		return outPath;
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
        dealWith(pro);
        pro.waitFor();
        return true;
    }  
	
	private static void dealWith(final Process pro){  
        // 下面是处理堵塞的情况  
        try {  
            new Thread(){  
                public void run(){  
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(pro.getInputStream()));  
                    String text;  
                    try {  
                        while ( (text = br1.readLine()) != null) {  
                            System.out.println(text);  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }.start();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        try {  
            new Thread(){  
                public void run(){  
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(pro.getErrorStream()));//这定不要忘记处理出理时产生的信息，不然会堵塞不前的  
                    String text;  
                    try {  
                        while( (text = br2.readLine()) != null){  
                            System.err.println(text);  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }.start();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
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
		convertOffice2SWF("doc", new FileInputStream("E://杜义松简历.doc"), "E://杜义松简历");
	}
}
