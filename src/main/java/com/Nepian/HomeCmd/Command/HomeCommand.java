package com.Nepian.HomeCmd.Command;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nepian.HomeCmd.HomeManager;

public class HomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;
		UUID uuid = player.getUniqueId();
		
		if (!HomeManager.hasHome(uuid)) {
			player.sendMessage("ホームが設定されていません");
			return true;
		}
		
		Location location = HomeManager.getHome(uuid);
		
		player.teleport(location);

		return true;
	}

}
