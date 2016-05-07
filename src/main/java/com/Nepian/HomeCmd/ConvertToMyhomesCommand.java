package com.Nepian.HomeCmd;

import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.Nepian.HomeCmd.Configuration.Properties;
import com.nepian.myhomes.HomedataController;
import com.nepian.myhomes.Myhomes;
import com.nepian.npcore.util.Messenger;
import com.nepian.npcore.util.PlayerUtil;
import com.nepian.npcore.util.command.CommandSenderType;
import com.nepian.npcore.util.command.MainCommand;
import com.nepian.npcore.util.command.SubCommandType;

public class ConvertToMyhomesCommand extends MainCommand {
	private Myhomes myhomes;

	public ConvertToMyhomesCommand(Messenger messenger, Myhomes myhomes) {
		super(messenger, "convert");
		super.addCommandSenderType(CommandSenderType.CONSOLE);
		this.myhomes = myhomes;
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args)
			throws CommandException {
		HomedataController hc = myhomes.getHomedataController();

		for (String playerName : SQLiteManager.getDatas().keySet()) {
			OfflinePlayer player = PlayerUtil.getOfflinePlayer(playerName);
			
			for (String homeName : SQLiteManager.getHomeList(player)) {
				Location location = SQLiteManager.getHome(player, homeName);
				String name = homeName;
				
				if (name.equals(Properties.DEFAULT_HOME_NAME)) {
					name = "default";
				}
				
				String msg = "convert from HomeCmd to MyHomes : " + player.getName()
						+ ", " + name;
				
				try {
					if (hc.has(player, name)) {
						hc.updateHome(player.getUniqueId(), name, location);
					} else {
						hc.addHome(player.getUniqueId(), name, location);
					}
					myhomes.getMessenger().success(msg);
				} catch (SQLException e) {
					myhomes.getMessenger().error(msg);
					e.printStackTrace();
				}
			}
		}
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
	public String getDescription() {
		return null;
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.HIDDEN;
	}

}
