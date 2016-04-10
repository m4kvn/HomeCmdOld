package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;
import java.util.Set;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nepian.HomeCmd.PlayerdataManager;
import com.Nepian.HomeCmd.Command.SubCommand;
import com.Nepian.HomeCmd.Data.Playerdata;

public class ListCommand extends SubCommand {

	public ListCommand() {
		super("list", "l");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {

		if (!(sender instanceof Player)) {
			return;
		}

		Player player = (Player) sender;
		Playerdata playerdata = PlayerdataManager.getPlayerdata(player.getUniqueId());
		Set<String> homes = playerdata.getNamedHomes().getNamedHomeLocations().keySet();

		player.sendMessage("NamedHomes: " + homes.toString());
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
