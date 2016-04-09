package com.Nepian.HomeCmd.Util;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class Utility {

	public static YamlConfiguration getYml(File file) {
		return YamlConfiguration.loadConfiguration(file);
	}
}
