package com.Nepian.HomeCmd.Command.Sub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.Nepian.HomeCmd.Logger;
import com.Nepian.HomeCmd.MySQLite;
import com.Nepian.HomeCmd.Command.SubCommand;
import com.Nepian.HomeCmd.Util.PlayerUtil;

public class ShowCommand extends SubCommand {
	
	public ShowCommand() {
		super("show");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		
		if (!(sender instanceof ConsoleCommandSender)) {
			Logger.log("This command is only ConsoleCommandSender");
			return;
		}
		
		OfflinePlayer player = PlayerUtil.getOfflinePlayer(args[0]);
		ResultSet rs = MySQLite.getResultSet(player);
		
		if (rs == null) {
			Logger.log("Player not found");
			return;
		}
		
		StringBuilder msg = new StringBuilder("Database Data\n");
		
		try {
			while (rs.next()) {
				msg.append("Player Name: ").append(rs.getString("player_name")).append("\n");
				msg.append("Player UUID: ").append(rs.getString("player_uuid")).append("\n");
				msg.append("Player Home: ").append(rs.getString("home_name")).append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		Logger.log(msg.toString());
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
