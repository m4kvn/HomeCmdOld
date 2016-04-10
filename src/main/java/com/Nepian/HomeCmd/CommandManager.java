package com.Nepian.HomeCmd;

import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Command.CommandHandler;
import com.Nepian.HomeCmd.Command.Sub.HomeCommand;
import com.Nepian.HomeCmd.Command.Sub.SetCommand;
import com.Nepian.HomeCmd.Util.Utility;

public class CommandManager {
	private static CommandHandler command;

	public static void load(JavaPlugin plugin) {
		command = new CommandHandler();
		command.registerMainCommand(new HomeCommand());
		command.registerSubCommand(new SetCommand());

		Utility.registerCommand(plugin, "home", command);
	}
}
