package com.fsalliance.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class CLS_CreateMD5val {
	
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 方法名称：craeatMD5
	 * 方法描述：生成文件类型的MD5值
	 * 参          数：fileName
	 * 返  回  值 ：String
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	public static String craeatMD5(String fileName) throws Exception {

		
		File file = new File(fileName);
		FileInputStream fis = null;

		MessageDigest md = MessageDigest.getInstance("MD5");

		fis = new FileInputStream(file);
		byte[] buffer = new byte[2048];
		int length = -1;
		while ((length = fis.read(buffer)) != -1) {
			md.update(buffer, 0, length);
		}
		byte[] b = md.digest();
		fis.close();
		return byteToHexString(b);
		// 16位加密
		// return buf.toString().substring(8, 24);

	}

	/**
	 * 方法名：byteToHexString
	 * 把byte[]数组转换成十六进制字符串表示形式
	 * 
	 * @param tmp
	 *            要转换的byte[]
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00     
	 * @return 十六进制字符串表示形式
	 */
	private static String byteToHexString(byte[] tmp) {
		String s;
		// 用字节表示就是 16 个字节
		char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
		// 所以表示成 16 进制需要 32 个字符
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
			// 转换成 16 进制字符的转换
			byte byte0 = tmp[i]; // 取第 i 个字节
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
			// >>> 为逻辑右移，将符号位一起右移
			str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
		}
		s = new String(str); // 换后的结果转换为字符串
		return s.toUpperCase();
	}
}
