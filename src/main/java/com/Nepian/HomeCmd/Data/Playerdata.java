package com.Nepian.HomeCmd.Data;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.Nepian.HomeCmd.Util.New;
import com.Nepian.HomeCmd.Util.PlayerUtil;

public class Playerdata implements ConfigurationSerializable {
	private OfflinePlayer player;
	private UUID uuid;
	private Location defaultHome;

	public Playerdata(UUID uuid, Location defaultHome) {
		this.uuid = uuid;
		this.defaultHome = defaultHome;
		this.player = PlayerUtil.getOfflinePlayer(this.uuid);
	}

	public UUID getUid() {
		return uuid;
	}

	public Location getDefaultHome() {
		return defaultHome;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> data = New.newMap();

		data.put("player", player.getName());
		data.put("uuid", uuid.toString());
		data.put("default", defaultHome);

		return data;
	}

	public static Playerdata deserialize(Map<String, Object> data) {
		UUID uuid = UUID.fromString(data.get("uuid").toString());
		Location location = (Location) data.get("default");

		return new Playerdata(uuid, location);
	}
}
