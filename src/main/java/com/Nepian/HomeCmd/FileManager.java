package com.Nepian.HomeCmd;

import static com.Nepian.HomeCmd.FileLoader.FileType.*;

import java.io.File;

import com.Nepian.HomeCmd.FileLoader.FileType;

public class FileManager {
	public static final File FOLDER_MAIN = Main.plugin.getDataFolder();
	public static final File FILE_HOME_DATA = load("home_data.yml", FILE);
	
	private static File load(String fileName, FileType type) {
		return FileLoader.load(FOLDER_MAIN, fileName, type);
	}
}