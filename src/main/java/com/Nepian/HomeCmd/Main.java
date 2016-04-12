package com.Nepian.HomeCmd;

import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Database.SQLite;

public class Main extends JavaPlugin {
	public static JavaPlugin plugin;
	public static SQLite sqlite;

	@Override
	public void onEnable() {
		plugin = this;
		sqlite = new SQLite(plugin);

		SerializationClassRegister.load();
		CommandManager.load(plugin);
		PlayerdataManager.load(FileManager.FOLDER_USERS);
		ListenerManager.load(plugin);

		sqlite.load();
	}

	@Override
	public void onDisable() {
		PlayerdataManager.save(FileManager.FOLDER_USERS);
	}
}
