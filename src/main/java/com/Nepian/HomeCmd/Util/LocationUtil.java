package com.Nepian.HomeCmd.Util;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class LocationUtil {
	private static final String SPLIT = "__";
	
	/**
	 * 文字列からロケーションデータを作成する
	 * @param string
	 * @return
	 */
	public static Location toLocation(JavaPlugin plugin, String string) {
		String[] data = string.split(SPLIT);
		
		UUID uuid = UUID.fromString(data[0]);
		World world = plugin.getServer().getWorld(uuid);
		double x = Double.valueOf(data[1]);
		double y = Double.valueOf(data[2]);
		double z = Double.valueOf(data[3]);
		float yaw = Float.valueOf(data[4]);
		float pitch = Float.valueOf(data[5]);
		
		return new Location(world, x, y, z, yaw, pitch);
	}
	
	/**
	 * ロケーションデータを文字列に変換する
	 * @param location
	 * @return
	 */
	public static String toString(Location location) {
		StringBuilder data = new StringBuilder("");
		
		World world = location.getWorld();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		
		data.append(world.getUID().toString()).append(SPLIT);
		data.append(x).append(SPLIT);
		data.append(y).append(SPLIT);
		data.append(z).append(SPLIT);
		data.append(yaw).append(SPLIT);
		data.append(pitch);
		
		return data.toString();
	}
}
