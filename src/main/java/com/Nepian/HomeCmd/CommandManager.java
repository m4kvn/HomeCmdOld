package com.Nepian.HomeCmd;

import org.bukkit.command.CommandExecutor;

public class CommandManager {
	private static Main plugin = Main.getPlugin();

	public static void load() {
	}

	public static void registerCommand(String name, CommandExecutor command) {
		plugin.getCommand(name).setExecutor(command);
	}
}
