package com.Nepian.HomeCmd.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nepian.HomeCmd.HomeManager;

public class SethomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;
		
		HomeManager.putHome(player.getUniqueId(), player.getLocation());
		
		player.sendMessage("ホームを設定しました");
		
		return true;
	}

}
