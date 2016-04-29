package com.Nepian.HomeCmd.Configuration;

import java.io.File;

import com.Nepian.HomeCmd.Messenger;
import com.Nepian.HomeCmd.Util.ConfigBase;

public class Config {
	private static ConfigBase config;
	
	/**
	 * コンフィグで使うキー
	 */
	public static class Key {
		public static String DEBUG_MESSAGE = "DEBUG_MESSAGE";
	}

	/**
	 * 設定ファイルを読み込む
	 * @param file
	 */
	public static void load(File file) {
		config = new ConfigBase(file);
		
		config.put(Key.DEBUG_MESSAGE, false);
		
		if (config.read()) {
			Messenger.success("設定ファイルを読み込みました");
		} else {
			Messenger.error("設定ファイルの読み込みが失敗しました");
		}
	}
	
	/**
	 * 設定ファイルを保存する
	 */
	public static void save() {
		if (!config.write()) {
			Messenger.error("設定ファイルへの書き込みが失敗しました");
		} else {
			Messenger.success("設定ファイルへ設定を書き込みました");
		}
	}
	
	public static Object get(String key) {
		return config.get(key);
	}
	
	public static boolean getBool(String key) {
		return (boolean) get(key);
	}
}
