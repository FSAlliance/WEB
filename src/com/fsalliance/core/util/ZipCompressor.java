package com.fsalliance.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipCompressor {

	private static final Logger log = LoggerFactory.getLogger(CreateJSONFile.class);
	static final int BUFFER = 8192;
	private File zipFile;

	public ZipCompressor(String pathName) {
		zipFile = new File(pathName);
	}

	/**
	 * 方法名称：compress
	 * 方法描述： 压缩 
	 * 参          数：srcPathName
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	public void compress(String srcPathName) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
					new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compress(file, out, basedir);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 方法名称：compress
	 * 方法描述： 判断是目录还是文件 
	 * 参          数：file，out，basedir
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	private void compress(File file, ZipOutputStream out, String basedir) {
		if (file.isDirectory()) {
			log.info("压缩：" + basedir + file.getName());
			this.compressDirectory(file, out, basedir);
		} else {
			log.info("压缩：" + basedir + file.getName());
			this.compressFile(file, out, basedir);
		}
	}

	/**
	 * 方法名称：compressDirectory
	 * 方法描述： 压缩一个目录 
	 * 参          数：dir，out，basedir
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists()){
			return;
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, "");
		}
	}

	/**
	 * 方法名称：compressFile
	 * 方法描述： 压缩一个文件
	 * 参        数：file，out，basedir
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
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
	 * 方法名称：zipJSON
	 * 方法描述： 压缩文件夹的子文件
	 * 参        数：file，out，basedir
	 * 返  回  值 ：
	 * 作者日期：chenanhai   2014年7月31日11:18:55
	 * 创  建  人 ：chenanhai   2014年7月31日11:18:55
	 * 其          他：
	 */
	public static String zipJSON(String savePath,String getFilePath) {
		try {
			// 要被压缩的文件夹
			//getFilePath = "D:/Tomcat 6.0/tem";
			File file = new File(getFilePath);
			//savePath = "d:/Tomcat 6.0/";
			File zipFile = new File(savePath+CLS_FSAlliance_ConfigTool.getValue("zipName"));

			InputStream input = null;
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(
					zipFile));
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; ++i) {

					input = new FileInputStream(files[i]);

					zipOut.putNextEntry(new ZipEntry(files[i].getName()));
					int temp = 0;
					while ((temp = input.read()) != -1) {
						zipOut.write(temp);
					}
					input.close();

				}
				zipOut.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

}
