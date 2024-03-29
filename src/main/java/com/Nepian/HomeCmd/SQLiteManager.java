package com.Nepian.HomeCmd;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

import com.Nepian.HomeCmd.Util.New;
import com.Nepian.HomeCmd.Util.PlayerUtil;
import com.Nepian.HomeCmd.Util.SQLite;

public class SQLiteManager {
	private static String tableName = "locations";
	private static SQLite data;
	
	public static void load(File file) {
		data = new SQLite(file);
		createTable();
	}
	
	/**
	 * テーブルを作成する
	 */
	private static void createTable() {
		String token = "create table if not exists " + tableName + " ("
				+ "player_uuid not null, "
				+ "player_name not null, "
				+ "home_name not null, "
				+ "world_uuid not null, "
				+ "x not null, "
				+ "y not null, "
				+ "z not null, "
				+ "yaw not null, "
				+ "pitch not null"
				+ ")";
		if (!data.executeUpdate(token)) {
			Messenger.error("テーブル (&6" + tableName + "&r) を作成できませんでした");
		}
		Messenger.success("テーブル (&6" + tableName + "&r) を作成しました");
	}
	
	/**
	 * ホームデータを追加する
	 * @param offlinePlayer
	 * @param homeName
	 * @param home
	 */
	public static void insert(OfflinePlayer offlinePlayer, String homeName, Location home) {
		String playerName = offlinePlayer.getName();
		
		if (has(offlinePlayer, homeName)) {
			Messenger.debug("このホームは既に登録されています (&6" + playerName
					+ "&r, &6" + homeName + "&r)");
			update(offlinePlayer, homeName, home);
			return;
		}
		
		String playerUidStr = offlinePlayer.getUniqueId().toString();
		String worldUidStr = home.getWorld().getUID().toString();
		double x = home.getX();
		double y = home.getY();
		double z = home.getZ();
		float pitch = home.getPitch();
		float yaw = home.getYaw();
		
		StringBuilder token = new StringBuilder("");
		
		token.append("insert into ").append(tableName).append(" values(");
		token.append("'" + playerUidStr + "'").append(", ");
		token.append("'" + playerName + "'").append(", ");
		token.append("'" + homeName + "'").append(", ");
		token.append("'" + worldUidStr + "'").append(", ");
		token.append(x).append(", ");
		token.append(y).append(", ");
		token.append(z).append(", ");
		token.append(yaw).append(", ");
		token.append(pitch);
		token.append(")");
		
		if (!data.executeUpdate(token.toString())) {
			Messenger.error("ホームを追加できませんでした (&6" + playerName
					+ "&r, &6" + homeName + "&r)");
			return;
		}
		
		Messenger.success("ホームを追加しました (&6" + playerName
				+ "&r, &6" + homeName + "&r)");
	}
	
	public static boolean delete(OfflinePlayer player, String name) {
		String uuid = player.getUniqueId().toString();
		String playerName = player.getName();
		
		if (!has(player, name)) {
			Messenger.failed("ホームが存在しないため削除に失敗しました (&6" + playerName
					+ "&r, &6" + name + "&r)");
			return false;
		}
		
		StringBuilder token = new StringBuilder("");
		
		token.append("delete from ").append(tableName);
		token.append(" where ");
		token.append("player_uuid = ").append("'" + uuid + "'");
		token.append(" and ");
		token.append("home_name = ").append("'" + name + "'");
		
		if (!data.executeUpdate(token.toString())) {
			Messenger.error("ホームを削除できませんでした (&6" + playerName
					+ "&r, &6" + name + "&r)");
			return false;
		} else {
			Messenger.success("ホームを削除しました (&6" + playerName
					+ "&r, &6" + name + "&r)");
			return true;
		}
	}
	
	/**
	 * データベースから切断する
	 */
	public static void close() {
		if (!data.close()) {
			Messenger.error("データベースファイル (&6" + data.getFile().getName()
					+ "&r) からの切断に失敗しました");
		}
		Messenger.success("データベースファイル (&6" + data.getFile().getName()
				+ "&r) から切断しました");
	}
	
	/**
	 * ホームが既に登録されているか確認する
	 * @param offlinePlayer
	 * @param homeName
	 * @return
	 */
	public static boolean has(OfflinePlayer offlinePlayer, String homeName) {
		String playerUidStr = offlinePlayer.getUniqueId().toString();
		StringBuilder token = new StringBuilder("");
		
		token.append("select player_uuid, home_name from ");
		token.append(tableName).append(" where ");
		token.append("player_uuid = ").append("'" + playerUidStr + "'").append(" and ");
		token.append("home_name = ").append("'" + homeName + "'");
		
		ResultSet rs = data.executeQuery(token.toString());
		
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * ホームデータを更新する
	 * @param offlinePlayer
	 * @param homeName
	 * @param home
	 */
	public static void update(OfflinePlayer offlinePlayer, String homeName, Location home) {
		String playerUidStr = offlinePlayer.getUniqueId().toString();
		String playerName = offlinePlayer.getName();
		String worldUidStr = home.getWorld().getUID().toString();
		double x = home.getX();
		double y = home.getY();
		double z = home.getZ();
		float pitch = home.getPitch();
		float yaw = home.getYaw();
		
		StringBuilder token = new StringBuilder("");
		
		token.append("update ").append(tableName).append(" set ");
		token.append("player_name = ").append("'" + playerName + "'").append(", ");
		token.append("world_uuid = ").append("'" + worldUidStr + "'").append(", ");
		token.append("x = ").append(x).append(", ");
		token.append("y = ").append(y).append(", ");
		token.append("z = ").append(z).append(", ");
		token.append("pitch = ").append(pitch).append(", ");
		token.append("yaw = ").append(yaw).append(" ");
		token.append("where ");
		token.append("player_uuid = ").append("'" + playerUidStr + "'").append(" and ");
		token.append("home_name = ").append("'" + homeName + "'");
		
		if (!data.executeUpdate(token.toString())) {
			Messenger.error("ホームを更新することができませんでした (&6" + playerName
					+ "&r, &6" + homeName + "&r)");
			return;
		}
		
		Messenger.success("ホームを更新しました (&6" + playerName
				+ "&r, &6" + homeName + "&r)");
	}
	
	/**
	 * ホームを取得する
	 * @param player
	 * @param homeName
	 * @return
	 */
	public static Location getHome(OfflinePlayer player, String homeName) {
		String playerUidStr = player.getUniqueId().toString();
		StringBuilder token = new StringBuilder("");
		
		token.append("select * from ");
		token.append(tableName).append(" where ");
		token.append("player_uuid = ").append("'" + playerUidStr + "'").append(" and ");
		token.append("home_name = ").append("'" + homeName + "'");
		
		ResultSet rs = data.executeQuery(token.toString());
		
		try {
			if (rs.next()) {
				UUID worldUid = UUID.fromString(rs.getString("world_uuid"));
				double x = rs.getDouble("x");
				double y = rs.getDouble("y");
				double z = rs.getDouble("z");
				float pitch = rs.getFloat("pitch");
				float yaw = rs.getFloat("yaw");
				World world = Bukkit.getWorld(worldUid);
				
				return new Location(world, x, y, z, yaw, pitch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * ホームデータ一覧を表示する
	 * @param player
	 * @return
	 */
	public static List<String> getHomeList(OfflinePlayer player) {
		String playerUidStr = player.getUniqueId().toString();
		StringBuilder token = new StringBuilder("");
		
		token.append("select home_name from ").append(tableName);
		token.append(" where ");
		token.append("player_uuid = ").append("'" + playerUidStr + "'");
		
		ResultSet rs = data.executeQuery(token.toString());
		List<String> list = New.newList();
		
		try {
			while (rs.next()) {
				list.add(rs.getString("home_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 全プレイヤーのホームデータ一覧を取得する
	 * @return
	 */
	public static Map<String, List<String>> getDatas() {
		String token = "select distinct player_name, player_uuid from " + tableName;
		ResultSet rs = data.executeQuery(token);
		Map<String, List<String>> map = New.newMap();
		Set<String> names = New.newSet();
		
		try {
			while (rs.next()) {
				names.add(rs.getString("player_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (String name : names) {
			OfflinePlayer player = PlayerUtil.getOfflinePlayer(name);
			List<String> list = getHomeList(player);
			map.put(name, list);
		}
		
		return map;
	}
}
