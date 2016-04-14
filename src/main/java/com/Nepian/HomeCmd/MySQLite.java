package com.Nepian.HomeCmd;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLite {
	private static Connection connection;
	private static Statement statement;
	
	public static void load(File file) {
		
		if (!isConnected(connection)) {
			return;
		}
		
		if (loadClass("org.sqlite.JDBC") == null) {
			return;
		}
		
		connection = getConnection(file);
		
		if (connection == null) {
			return;
		}
		
		statement = createStatement(connection);
		
		if (statement == null) {
			return;
		}
		
		createTable();
	}
	
	public static void close() {
		close(statement);
		close(connection);
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
		
		return true;
	}
	
	private static Class<?> loadClass(String str) {
		try {
			return Class.forName(str);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Connection getConnection(File file) {
		try {
			return DriverManager.getConnection("jdbc:sqlite:" + file);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
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
		try {
			statement.executeUpdate("create table if not exists users (name, uuid, home)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
