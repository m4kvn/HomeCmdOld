package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nepian.HomeCmd.PlayerdataManager;
import com.Nepian.HomeCmd.Command.SubCommand;
import com.Nepian.HomeCmd.Data.Playerdata;

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

		Player player = (Player) sender;
		UUID uuid = player.getUniqueId();
		Location location = player.getLocation();
		Playerdata playerdata = PlayerdataManager.getPlayerdata(uuid);

		if (args.length == 0) {
			playerdata.setDefaultHome(location);
			player.sendMessage("デフォルトのホームを設定しました");

		} else if (args.length == 1) {
			String homename = args[0];

			if (homename.contains("-")) {
				player.sendMessage("ホーム名に\"-\"は使用できません");
				return;
			}

			playerdata.getNamedHomes().putNamedHome(homename, location);
			player.sendMessage("ホーム<" + homename + ">を設定しました");
		}

		PlayerdataManager.putPlayerdata(uuid, playerdata);
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
