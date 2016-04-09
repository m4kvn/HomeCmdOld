package com.Nepian.HomeCmd;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Command.HomeCommand;

public class CommandManager {
	public static void load(JavaPlugin plugin) {
		registerCommand(plugin, "home", new HomeCommand());
	}

	public static void registerCommand(JavaPlugin plugin, String name, CommandExecutor command) {
		plugin.getCommand(name).setExecutor(command);
	}
}
