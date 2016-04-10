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
	private NamedHomes namedHomes;

	public Playerdata(UUID uuid) {
		this(uuid, null);
	}

	public Playerdata(UUID uuid, Location defaultHome) {
		this(uuid, defaultHome, new NamedHomes());
	}

	public Playerdata(UUID uuid, Location defaultHome, NamedHomes namedHomes) {
		this.uuid = uuid;
		this.defaultHome = defaultHome;
		this.player = PlayerUtil.getOfflinePlayer(this.uuid);
		this.namedHomes = namedHomes;
	}

	public UUID getUid() {
		return uuid;
	}

	public Location getDefaultHome() {
		return defaultHome;
	}

	public void setDefaultHome(Location location) {
		this.defaultHome = location;
	}

	public NamedHomes getNamedHomes() {
		return namedHomes;
	}

	public boolean hasDefaultHome() {
		return defaultHome != null;
	}

	public Location getNamedHomeLocation(String homename) {
		return namedHomes.getNamedHomeLocation(homename);
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> data = New.newMap();

		data.put("namedhomes", namedHomes);
		data.put("player", player.getName());
		data.put("default", defaultHome);
		data.put("uuid", uuid.toString());

		return data;
	}

	public static Playerdata deserialize(Map<String, Object> data) {
		UUID uuid = UUID.fromString(data.get("uuid").toString());
		Location location = (Location) data.get("default");
		NamedHomes namedHomes = (NamedHomes) data.get("namedhomes");

		return new Playerdata(uuid, location, namedHomes);
	}
}
