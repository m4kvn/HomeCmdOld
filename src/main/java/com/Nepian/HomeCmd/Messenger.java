package com.Nepian.HomeCmd;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Configuration.Config;
import com.Nepian.HomeCmd.Util.Sender;

public class Messenger {
	private static Sender sender;
	
	public static void load(JavaPlugin plugin) {
		sender = new Sender(plugin);
	}
	
	public static void log(Object obj) {
		sender.log(obj);
	}
	
	public static void debug(Object obj) {
		if (Config.getBool(Config.Key.DEBUG_MESSAGE)) {
			log("&eDEBUG:&r " + obj);
		}
	}
	
	public static void success(Object obj) {
		debug("&9SUCCESS:&r " + obj);
	}
	
	public static void failed(Object obj) {
		debug("&4FAILED:&r " + obj);
	}
	
	public static void error(Object obj) {
		log("&4ERROR:&r " + obj);
	}
	
	public static void send(CommandSender sender, Object obj) {
		Messenger.sender.send(sender, obj);
	}
	
	public static void sendFailed(CommandSender sender, Object obj) {
		send(sender, "&4FAILED:&r " + obj);
	}
	
	public static void sendSuccess(CommandSender sender, Object obj) {
		send(sender, "&9SUCCESS:&r " + obj);
	}
}
