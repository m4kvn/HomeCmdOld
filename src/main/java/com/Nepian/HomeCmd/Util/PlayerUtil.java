package com.Nepian.HomeCmd.Util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class PlayerUtil {

	public static String getName(UUID uuid) {
		return getOfflinePlayer(uuid).getName();
	}

	public static OfflinePlayer getOfflinePlayer(UUID uuid) {
		return Bukkit.getServer().getOfflinePlayer(uuid);
	}
	
	public static OfflinePlayer getOfflinePlayer(String name) {
		for (OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}
}
