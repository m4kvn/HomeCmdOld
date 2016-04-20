package com.Nepian.HomeCmd.Util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigBase {
	protected File configFile;
	protected Map<String, Object> configs;

	public ConfigBase(File configFile) {
		this.configFile = configFile;
		this.configs = New.newMap();
	}
	
	public Object put(String key, Object value) {
		return configs.put(key, value);
	}
	
	public Object get(String key) {
		return configs.get(key);
	}
	
	public boolean read() {
		YamlConfiguration data = Utility.getYml(configFile);
		
		for (String key : data.getKeys(false)) {
			put(key, data.get(key));
		}
		
		return true;
	}
	
	public boolean write() {
		YamlConfiguration data = Utility.getYml(configFile);
		
		for (String key : configs.keySet()) {
			data.set(key, get(key));
		}
		
		try {
			data.save(configFile);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
