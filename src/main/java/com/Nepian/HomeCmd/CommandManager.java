package com.Nepian.HomeCmd;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {
	private JavaPlugin plugin;

	public CommandManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void load() {
	}

	public void registerCommand(String name, CommandExecutor command) {
		plugin.getCommand(name).setExecutor(command);
	}
}
