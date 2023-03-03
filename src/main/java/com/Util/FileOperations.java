package com.Util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class FileOperations {
	static Properties pro;

	public static Properties loadproperties() {
		if (pro == null) {
			try {
				pro = new Properties();
				File f = new File(System.getProperty("user.dir") + "/src/test/resources/PropertyFiles");
				File[] file = f.listFiles();
				for (int i = 0; i < file.length; i++) {
					pro.load(new FileInputStream(file[i]));
				}
			} catch (Exception e) {
				return null;
			}
		}
		return pro;
	}
	public static String DerivergetProperty(String prop)
	{		
		pro=loadproperties();
		return pro.getProperty(prop);
	}
}
