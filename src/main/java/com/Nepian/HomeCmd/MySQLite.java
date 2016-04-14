package com.Nepian.HomeCmd;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.bukkit.Location;

public class MySQLite {
	private static Connection connection;
	private static Statement statement;
	
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
	
	public static void close() {
		close(statement);
		close(connection);
	}
	
	public static void insert(String name, UUID uuid, Location location) {
		String uuidStr = uuid.toString();
		String worldUidStr = location.getWorld().getUID().toString();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		
		StringBuilder token = new StringBuilder("");
		
		token.append("insert into locations values(");
		token.append("'" + uuidStr + "'").append(", ");
		token.append("'" + name + "'").append(", ");
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
		String locations = "create table if not exists locations (uuid, name, world, x, y, z, yaw, pitch)";
		try {
			statement.executeUpdate(locations);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
