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
		UUID uuid = player.getUniqueId();

		if (!PlayerdataManager.hasPlayerdata(uuid)) {
			player.sendMessage("ホームが設定されていません");
			return;
		}

		Playerdata playerdata = PlayerdataManager.getPlayerdata(uuid);
		Location location = playerdata.getDefaultHome();

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
