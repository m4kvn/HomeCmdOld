package com.Nepian.HomeCmd;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

public class MySQLite {
	private static Connection connection;
	private static Statement statement;
	
	/**
	 * データベースをロードする
	 * @param file
	 */
	public static void load(File file) {
		
		if (isConnected(connection)) {
			Logger.debug("SQLite is already connected");
			return;
		}
		
		if (loadClass("org.sqlite.JDBC") == null) {
			Logger.debug("Class \"org.sqlite.JDBC\" is null");
			return;
		}
		
		connection = getConnection(file);
		
		if (connection == null) {
			Logger.debug("Connection is null");
			return;
		}
		
		statement = createStatement(connection);
		
		if (statement == null) {
			Logger.debug("Statement is null");
			return;
		}
		
		createTable();
	}
	
	/**
	 * データベースとのコネクションを切断する
	 */
	public static void close() {
		close(statement);
		close(connection);
	}
	
	/**
	 * プレイヤーのホームデータをデータベースに追加する
	 * @param offlinePlayer
	 * @param homeLocation
	 * @param homeName
	 */
	public static void insert(OfflinePlayer offlinePlayer, Location homeLocation, String homeName) {
		String uuidStr = offlinePlayer.getUniqueId().toString();
		String playerName = offlinePlayer.getName();
		String worldUidStr = homeLocation.getWorld().getUID().toString();
		double x = homeLocation.getX();
		double y = homeLocation.getY();
		double z = homeLocation.getZ();
		float yaw = homeLocation.getYaw();
		float pitch = homeLocation.getPitch();
		
		StringBuilder token = new StringBuilder("");
		
		token.append("insert into locations values(");
		token.append("'" + uuidStr + "'").append(", ");
		token.append("'" + playerName + "'").append(", ");
		token.append("'" + homeName + "'").append(", ");
		token.append("'" + worldUidStr + "'").append(", ");
		token.append(x).append(", ");
		token.append(y).append(", ");
		token.append(z).append(", ");
		token.append(yaw).append(", ");
		token.append(pitch);
		token.append(")");
		
		Logger.debug("INSERT TOKEN : " + token.toString());
		
		try {
			if (statement == null) {
				Logger.debug("This statement can't execute update");
				return;
			}
			statement.executeUpdate(token.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getResultSet(OfflinePlayer player) {
		String playerUidStr = player.getUniqueId().toString();
		String token =
				"select * from locations where player_uuid = '"  + playerUidStr + "'";
		
		try {
			ResultSet resultSet = statement.executeQuery(token.toString());
			Logger.debug("ResultSet was getted");
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static boolean isConnected(Connection connection) {
		
		if (connection == null) {
			return false;
		}
		
		try {
			if (connection.isClosed()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Logger.debug("SQLite was connected");
		
		return true;
	}
	
	private static Class<?> loadClass(String str) {
		try {
			Class<?> clazz = Class.forName(str);
			Logger.debug("Class \"org.sqlite.JDBC\" was load");
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Connection getConnection(File file) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file);
			Logger.debug("Connection was getted");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Statement createStatement(Connection connection) {
		try {
			Statement statement =  connection.createStatement();
			Logger.debug("Statement was created");
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void createTable() {
		String locations =
				"create table if not exists locations (player_uuid, player_name, home_name, world_uuid, x, y, z, yaw, pitch)";
		try {
			statement.executeUpdate(locations);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
