package com.Nepian.HomeCmd;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.FileLoader.FileType;

public class FileManager {
	public File FOLDER_MAIN;
	public File FILE_HOME_DATA;
	
	public FileManager(JavaPlugin plugin) {
	}

	public void load(File dataFolder) {
		FOLDER_MAIN = dataFolder;
		FILE_HOME_DATA = FileLoader.load(FOLDER_MAIN, "home_data", FileType.FILE);
	}
}
