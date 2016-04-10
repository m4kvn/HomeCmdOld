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
		super("set");
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
		Playerdata playerdata = new Playerdata(uuid, location);

		PlayerdataManager.putPlayerdata(uuid, playerdata);
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
