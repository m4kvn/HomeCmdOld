package com.Nepian.HomeCmd;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Command.CommandHandler;
import com.Nepian.HomeCmd.Command.Sub.HomeCommand;
import com.Nepian.HomeCmd.Command.Sub.SetCommand;

public class CommandManager {
	private static CommandHandler command;

	public static void load(JavaPlugin plugin) {
		command = new CommandHandler();
		command.registerMainCommand(new HomeCommand());
		command.registerSubCommand(new SetCommand());

		registerCommand(plugin, "home", command);
	}

	public static void registerCommand(JavaPlugin plugin, String name, CommandExecutor command) {
		plugin.getCommand(name).setExecutor(command);
	}
}
