package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.Nepian.HomeCmd.MySQLite;
import com.Nepian.HomeCmd.PlayerdataManager;
import com.Nepian.HomeCmd.Command.SubCommand;
import com.Nepian.HomeCmd.Data.Playerdata;
import com.Nepian.HomeCmd.Util.PlayerUtil;

public class AddCommand extends SubCommand {
	
	public AddCommand() {
		super("add");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {

		if (!(sender instanceof ConsoleCommandSender)) {
			return;
		}
		
		String name = args[0];
		OfflinePlayer player = PlayerUtil.getOfflinePlayer(name);
		UUID uuid = player.getUniqueId();
		Playerdata playerdata = PlayerdataManager.getPlayerdata(uuid);
		
		MySQLite.insert(name, uuid, playerdata.getDefaultHome());
	}

	@Override
	public String getPossibleArguments() {
		return "<player_name>";
	}

	@Override
	public int getMinimumArguments() {
		return 1;
	}

	@Override
	public List<String> getTutorial() {
		return null;
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.HIDDEN;
	}

}
