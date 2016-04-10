package com.Nepian.HomeCmd.Util;

import java.io.File;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Utility {

	public static YamlConfiguration getYml(File file) {
		return YamlConfiguration.loadConfiguration(file);
	}

	public static void registerCommand(JavaPlugin plugin, String name, CommandExecutor command) {
		plugin.getCommand(name).setExecutor(command);
	}
}
