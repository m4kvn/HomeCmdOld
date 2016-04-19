package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nepian.HomeCmd.SQLiteManager;
import com.Nepian.HomeCmd.Command.SubCommand;

public class HomeCommand extends SubCommand {

	public HomeCommand() {
		super("home");
		setPermission("homecmd.home");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {

		if (!(sender instanceof Player)) {
			return;
		}

		Player player = (Player) sender;
		Location location = null;

		if (args.length == 0) {
			location = SQLiteManager.getHome(player, "-default");
			
			if (location == null) {
				player.sendMessage("デフォルトのホームが設定されていません");
				return;
			}
		} else if (args.length == 1) {
			String homeName = args[0];

			location = SQLiteManager.getHome(player, homeName);

			if (location == null) {
				player.sendMessage("ホーム<" + homeName + ">は設定されていません");
				return;
			}
		}

		player.teleport(location);
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
