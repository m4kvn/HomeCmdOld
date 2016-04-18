package com.Nepian.HomeCmd;

import java.io.File;

import com.Nepian.HomeCmd.Util.SQLite;

public class SQLiteManager {
	private static String tableName = "locations";
	private static SQLite data;
	
	public static void load(File file) {
		data = new SQLite(file);
		createTable();
	}
	
	private static void createTable() {
		String token = "create table if not exists " + tableName + " ("
				+ "player_uuid, player_name, home_name, world_uuid, x, y, z, yaw, pitch)";
		if (!data.executeUpdate(token)) {
			Logger.failed("Could not create a table (&6" + tableName + "&r)");
		}
		Logger.success("Created a table (&6" + tableName + "&r)");
	}
//	
//	public static void insert(OfflinePlayer offlinePlayer, String homeName, Location home) {
//		String playerUidStr = offlinePlayer.getUniqueId().toString();
//		String playerName = offlinePlayer.getName();
//		
//		data.executeUpdate("insert into " + tableName + " values()");
//	}
	
	public static void close() {
		if (!data.close()) {
			Logger.failed("Could not close a SQLite file (&6" + data.getFile().getName() + "&r)");
		}
		Logger.success("Closed a SQLite file (&6" + data.getFile().getName() + "&r)");
	}
}
