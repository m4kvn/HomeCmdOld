package com.Nepian.HomeCmd.Listener.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.Nepian.HomeCmd.SQLiteManager;

public class PlayerRespawnListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();

		if (!SQLiteManager.has(player, "-default")) {
			return;
		}

		event.setRespawnLocation(SQLiteManager.getHome(player, "-default"));
	}
}
