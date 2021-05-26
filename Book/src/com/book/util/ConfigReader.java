package com.book.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	// 读取配置文件的方法
	// fileName：配置文件的路径和文件名
	// 将读取的配置信息存入 Properties 对象中返回
	public static Properties read(String fileName) {
		Properties ps = new Properties();
		// 此方式可以很方便的获得项目中 src 文件夹下的文件的输入流
		try (InputStream in = ConfigReader.class.getResourceAsStream("/" + fileName)) {
			ps.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

}
