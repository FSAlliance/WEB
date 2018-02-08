package com.fsalliance.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CreateJSONFile {
	private static final Logger log = LoggerFactory.getLogger(CreateJSONFile.class);

	public static BufferedReader bufread;
	// 指定文件路径和名称

	private static String readStr = "";
	static final int BUFFER = 8192;
	/**
	 * 方法名称：creatFile
	 * 方法描述：创建文本文件.
	 * 参          数：filePath
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * throws IOException
	 * 其          他：
	 */
	public static void creatFile(String filePath) throws IOException {
		File filename = new File(filePath);
		if (!filename.exists()) {
			filename.createNewFile();
			log.info(filename + "已创建！");
			System.out.println(filename + "已创建！");
		}
	}
	/**
	 * 方法名称：createFold
	 * 方法描述：创建文件夹
	 * 参          数：filePath
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	public static void createFold(String filePath) {
		  File fileName = new File(filePath); 
		  if(!fileName.exists() && !fileName.isDirectory()){
			  fileName.mkdirs();    
			  log.info(fileName + "已创建！");
		  }
	}

	/**
	 * 方法名称：readFile
	 * 方法描述：读取文本文件.
	 * 参          数：filename
	 * 返  回  值 ：String
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	public static String readFile(File filename) {
		String read;
		FileReader fileread;
		try {
			fileread = new FileReader(filename);
			bufread = new BufferedReader(fileread);
			try {
				while ((read = bufread.readLine()) != null) {
					readStr = readStr + read + "\r\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//System.out.println("文件内容是:" + "\r\n" + readStr);
		return readStr;
	}

	/**
	 * 方法名称：write
	 * 方法描述：写文件.
	 * 参          数：filename，content
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */

	public static void write(String fileName, String content) {
		BufferedWriter bw = null;

		try {
			File filePath = new File(fileName);
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(new FileWriter(filePath));
			// 将内容写入文件中
			bw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
	}

	
	/**
	 * 方法名称：compressFile
	 * 方法描述：压缩一个文件
	 * 参          数：file，out，basedir
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	
	@SuppressWarnings("unused")
	private void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 方法名称：projectPath
	 * 方法描述：获得当前项目路径
	 * 参          数：
	 * 返  回  值 ：String
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	public static String projectPath(){
		String pathWIN =System.getProperty("user.dir");
		//获得WIN回退一层的目录
		String pathWINBack = pathWIN.substring(0,pathWIN.lastIndexOf("\\"));
		String pathJAVA =pathWINBack.replace("\\", "/");
		log.info("JAVA路径"+pathJAVA);
		return pathJAVA+"/tem";
		
	}
}
