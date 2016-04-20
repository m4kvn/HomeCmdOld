package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nepian.HomeCmd.Messenger;
import com.Nepian.HomeCmd.SQLiteManager;
import com.Nepian.HomeCmd.Command.SubCommand;
import com.Nepian.HomeCmd.Configuration.Properties;

public class SetCommand extends SubCommand {

	public SetCommand() {
		super("set", "s");
		setPermission("homecmd.set");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {

		if (!(sender instanceof Player)) {
			return;
		}

		String def = Properties.DEFAULT_HOME_NAME;
		Player player = (Player) sender;
		Location location = player.getLocation();

		if (args.length == 0) {
			SQLiteManager.insert(player, def, location);
			Messenger.sendSuccess(player, "デフォルトのホームを設定しました");

		} else if (args.length == 1) {
			String homename = args[0];

			if (homename.contains("-")) {
				Messenger.sendFailed(player, "ホーム名に\"-\"は使用できません");
				return;
			}

			SQLiteManager.insert(player, homename, location);
			Messenger.sendSuccess(player, "ホーム<" + homename + ">を設定しました");
		}
	}

	@Override
	public String getPossibleArguments() {
		return "<ホームの名前>";
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
