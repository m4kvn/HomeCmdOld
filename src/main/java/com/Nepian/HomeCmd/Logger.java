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
				'&', "&d" + plugin.getName() + ":&r " + str);
		
		Bukkit.getServer().getConsoleSender().sendMessage(msg);
	}
	
	public static void debug(String str) {
		Logger.log("&eDEBUG:&r " + str);
	}
	
	public static void success(String str) {
		debug("&9SUCCESS:&r " + str);
	}
	
	public static void failed(String str) {
		debug("&4FAILED:&r " + str);
	}
}
