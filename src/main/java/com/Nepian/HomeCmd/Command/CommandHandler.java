package com.Nepian.HomeCmd.Command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Nepian.HomeCmd.Util.New;

public class CommandHandler implements CommandExecutor {
	private SubCommand mainCommand;
	private List<SubCommand> subCommands;

	public CommandHandler() {
		subCommands = New.newList();
	}

	@Override
	public boolean onCommand(CommandSender sender,
			Command command, String label, String[] args) {

		if (args.length > 0 && args[0].startsWith("-")) {
			for (SubCommand subCommand : subCommands) {
				if (!subCommand.isValidTrigger(args[0].replace("-", ""))) {
					continue;
				}

				if (!subCommand.hasPermission(sender)) {
					sender.sendMessage("You don't have permission!");
					return true;
				}

				if (subCommand.getMinimumArguments() <= args.length - 1) {
					try {
						subCommand.execute(sender, label, Arrays.copyOfRange(args, 1, args.length));
					} catch (CommandException e) {
						sender.sendMessage(e.getMessage());
					}
				} else {
					StringBuilder usage = new StringBuilder("Usage: /");
					usage.append(label).append(" ");
					usage.append(subCommand.getName()).append(" ");
					usage.append(subCommand.getPossibleArguments());
					sender.sendMessage(usage.toString());
				}
				return true;
			}
		}

		if (mainCommand.isValidTrigger(label)) {
			if (!mainCommand.hasPermission(sender)) {
				sender.sendMessage("You don't have permission!");
				return true;
			}

			try {
				mainCommand.execute(sender, label, args);
			} catch (CommandException e) {
				sender.sendMessage(e.getMessage());
			}
			return true;
		}

		sender.sendMessage("Unknown sub-command. Type \"/" + label + " help\" for a list of commands.");

		return true;
	}

	public void registerSubCommand(SubCommand command) {
		subCommands.add(command);
	}

	public void registerMainCommand(SubCommand command) {
		mainCommand = command;
	}
}
