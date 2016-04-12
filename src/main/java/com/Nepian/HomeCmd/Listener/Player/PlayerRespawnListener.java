package com.Nepian.HomeCmd.Listener.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.Nepian.HomeCmd.PlayerdataManager;
import com.Nepian.HomeCmd.Data.Playerdata;

public class PlayerRespawnListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerRespawn(PlayerRespawnEvent event) {

		Player player = event.getPlayer();
		Playerdata playerdata = PlayerdataManager.getPlayerdata(player.getUniqueId());

		if (!playerdata.hasDefaultHome()) {
			return;
		}

		event.setRespawnLocation(playerdata.getDefaultHome());
	}
}
