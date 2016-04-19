package com.Nepian.HomeCmd.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Sender {
	protected final JavaPlugin plugin;
	protected final String prefix;

	public Sender(JavaPlugin plugin) {
		this.plugin = plugin;
		this.prefix = "&d" + plugin.getName() + ":&r ";
	}
	
	public void log(Object obj) {
		send(Bukkit.getServer().getConsoleSender(), obj);
	}
	
	public void send(CommandSender sender, Object obj) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + obj));
	}
}
