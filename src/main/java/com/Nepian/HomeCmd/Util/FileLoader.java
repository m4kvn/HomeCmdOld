package com.Nepian.HomeCmd.Util;

import java.io.File;

public class FileLoader {

	public enum FileType { FILE, FOLDER }
	
	public static File load(File folder, String fileName, FileType type) {
		File file = new File(folder, fileName);
		
		if (!file.exists()) {
			try {
				if (file.getParent() != null) {
					file.getParentFile().mkdirs();
				}
				
				switch (type) {
				case FILE:
					file.createNewFile();
					break;
				case FOLDER:
					file.mkdir();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return file;
	}
}
