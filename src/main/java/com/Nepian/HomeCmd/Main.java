package com.Nepian.HomeCmd;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public FileManager fileManager;
	public CommandManager commandManager;
	public HomeManager homeManager;

	@Override
	public void onEnable() {
		fileManager = new FileManager(this);
		commandManager = new CommandManager(this);
		homeManager = new HomeManager(this);
		
		load();
	}

	@Override
	public void onDisable() {
		save();
	}
	
	public void load() {
		fileManager.load(this.getDataFolder());
		homeManager.load(fileManager.FILE_HOME_DATA);
	}
	
	public void save() {
		homeManager.save(fileManager.FILE_HOME_DATA);
	}
}
