package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nepian.HomeCmd.HomeManager;
import com.Nepian.HomeCmd.Command.SubCommand;

public class SetCommand extends SubCommand {

	public SetCommand() {
		super("set");
		setPermission("homecmd.set");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {

		if (!(sender instanceof Player)) {
			return;
		}

		Player player = (Player) sender;

		HomeManager.putHome(player.getUniqueId(), player.getLocation());

		player.sendMessage("ホームを設定しました");
	}

	@Override
	public String getPossibleArguments() {
		return null;
	}

	@Override
	public int getMinimumArguments() {
		return 0;
	}

	@Override
	public List<String> getTutorial() {
		return null;
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.GENERIC;
	}

}
