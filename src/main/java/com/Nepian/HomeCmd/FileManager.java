package com.Nepian.HomeCmd;

import static com.Nepian.HomeCmd.Configuration.Properties.*;
import static com.Nepian.HomeCmd.Util.FileLoader.FileType.*;

import java.io.File;

import com.Nepian.HomeCmd.Util.FileLoader;

public class FileManager extends FileLoader {
	public static final File FOLDER_MAIN = Main.plugin.getDataFolder();
	public static final File FOLDER_USERS = load(FOLDER_MAIN, USER_DATA_FOLDER_NAME, FOLDER);
	public static final File FILE_SQLITE = load(FOLDER_MAIN, SQLITE_FILE_NAME, FILE);
	public static final File FILE_CONFIG = load(FOLDER_MAIN, CONFIG_FILE_NAME, FILE);
}