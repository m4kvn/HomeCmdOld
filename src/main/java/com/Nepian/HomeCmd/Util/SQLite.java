package com.Nepian.HomeCmd.Util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
	private static String[] updateCmd = { "create", "insert", "update", "delete" };
	private static String[] queryCmd = { "select" };
	
	private File file;
	private Connection connection;
	private Statement statement;
	
	public SQLite(File file) {
		this.file = file;
		load();
	}
	
	private void load() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + file);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean close() {
		try {
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean executeUpdate(String token) {
		if (!checkToken(token, updateCmd)) {
			return false;
		}
		
		try {
			statement.executeUpdate(token);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet executeQuery(String token) {
		if (!checkToken(token, queryCmd)) {
			return null;
		}
		try {
			return statement.executeQuery(token);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean checkToken(String token, String[] cmds) {
		String cmd = token.split(" ")[0];
		for (String str : cmds) {
			if (str.equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	public File getFile() {
		return this.file;
	}
}
