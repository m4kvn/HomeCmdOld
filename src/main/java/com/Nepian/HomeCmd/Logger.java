package com.Nepian.HomeCmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Logger {
	private static JavaPlugin plugin;
	
	public static void load(JavaPlugin plugin) {
		Logger.plugin = plugin;
	}
	
	public static void log(String str) {
		String msg = ChatColor.translateAlternateColorCodes(
				'&', "&3[&d" + plugin.getName() + "&3]&r " + str);
		
		Bukkit.getServer().getConsoleSender().sendMessage(msg);
	}
	
	public static void debug(String str) {
		Logger.log("&7[&eDEBUG&7]&r " + str);
	}
}
