package com.Nepian.HomeCmd;

import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Configuration.SerializationClassRegister;

public class Main extends JavaPlugin {
	public static JavaPlugin plugin;

	@Override
	public void onEnable() {
		plugin = this;

		Messenger.load(plugin);
		SQLiteManager.load(FileManager.FILE_SQLITE);
		SerializationClassRegister.load();
		CommandManager.load(plugin);
		PlayerdataManager.load(FileManager.FOLDER_USERS);
		ListenerManager.load(plugin);
	}

	@Override
	public void onDisable() {
		PlayerdataManager.save(FileManager.FOLDER_USERS);
		SQLiteManager.close();
	}
}
