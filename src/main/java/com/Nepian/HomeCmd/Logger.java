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
	
	public static void debug(Object obj) {
		Logger.log("&eDEBUG:&r " + obj);
	}
	
	public static void success(Object obj) {
		debug("&9SUCCESS:&r " + obj);
	}
	
	public static void failed(Object obj) {
		debug("&4FAILED:&r " + obj);
	}
}
