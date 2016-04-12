package com.Nepian.HomeCmd;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Listener.Player.PlayerRespawnListener;

public class ListenerManager {
	private static JavaPlugin plugin;

	public static void load(JavaPlugin plugin) {
		ListenerManager.plugin = plugin;

		register(new PlayerRespawnListener());
	}

	private static void register(Listener listener) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}
}
