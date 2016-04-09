package com.Nepian.HomeCmd;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static JavaPlugin plugin;

	@Override
	public void onEnable() {
		plugin = this;
		CommandManager.load(plugin);
		HomeManager.load(plugin, FileManager.FILE_HOME_DATA);
	}

	@Override
	public void onDisable() {
		HomeManager.save(FileManager.FILE_HOME_DATA);
	}
}
