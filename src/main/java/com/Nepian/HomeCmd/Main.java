package com.Nepian.HomeCmd;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static JavaPlugin plugin;

	@Override
	public void onEnable() {
		plugin = this;
		SerializationClassRegister.load();
		CommandManager.load(plugin);
		PlayerdataManager.load(FileManager.FOLDER_USERS);
	}

	@Override
	public void onDisable() {
		PlayerdataManager.save(FileManager.FOLDER_USERS);
	}
}
