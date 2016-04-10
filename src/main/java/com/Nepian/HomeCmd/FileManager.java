package com.Nepian.HomeCmd;

import static com.Nepian.HomeCmd.Configuration.Propertie.*;
import static com.Nepian.HomeCmd.FileLoader.FileType.*;

import java.io.File;

public class FileManager extends FileLoader {
	public static final File FOLDER_MAIN = Main.plugin.getDataFolder();
	public static final File FOLDER_USERS = load(FOLDER_MAIN, USER_DATA_FOLDER_NAME, FOLDER);
}