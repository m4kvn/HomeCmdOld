package com.Nepian.HomeCmd;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Util.LocationUtil;
import com.Nepian.HomeCmd.Util.Utility;

public class HomeManager {
	private static YamlConfiguration dataFile;
	private static Map<UUID, Location> homeDatas;
	
	public static void load(JavaPlugin plugin, File file) {
		homeDatas = new HashMap<UUID, Location>();
		dataFile = Utility.getYml(file);
		
		for (String uuidStr : dataFile.getKeys(false)) {
			String locationStr = dataFile.getString(uuidStr);
			Location location = LocationUtil.toLocation(plugin, locationStr);
			
			homeDatas.put(UUID.fromString(uuidStr), location);
		}
	}
	
	public static void save(File file) {
		for (UUID uuid : homeDatas.keySet()) {
			String locationStr = LocationUtil.toString(homeDatas.get(uuid));
			dataFile.set(uuid.toString(), locationStr);
		}
		
		try {
			dataFile.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void putHome(UUID uuid, Location location) {
		homeDatas.put(uuid, location);
	}
	
	public static Location getHome(UUID uuid) {
		return homeDatas.get(uuid);
	}
}
