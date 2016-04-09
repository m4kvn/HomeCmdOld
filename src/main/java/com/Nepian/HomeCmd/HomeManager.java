package com.Nepian.HomeCmd;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Util.LocationUtil;

public class HomeManager {
	private JavaPlugin plugin;
	private Map<UUID, Location> homeDatas;
	
	public HomeManager(JavaPlugin plugin) {
		this.plugin = plugin;
		this.homeDatas = new HashMap<UUID, Location>();
	}
	
	public void load(File file) {
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		for (String uuidStr : data.getKeys(false)) {
			Location location = LocationUtil.toLocation(plugin, data.getString(uuidStr));
			homeDatas.put(UUID.fromString(uuidStr), location);
		}
	}
	
	public void save(File file) {
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		for (UUID uuid : homeDatas.keySet()) {
			String locationStr = LocationUtil.toString(homeDatas.get(uuid));
			data.set(uuid.toString(), locationStr);
		}
	}
	
	public void putHome(UUID uuid, Location location) {
		homeDatas.put(uuid, location);
	}
	
	public Location getHome(UUID uuid) {
		return homeDatas.get(uuid);
	}
}
