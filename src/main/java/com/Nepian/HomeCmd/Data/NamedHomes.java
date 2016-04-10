package com.Nepian.HomeCmd.Data;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.Nepian.HomeCmd.Util.New;

public class NamedHomes implements ConfigurationSerializable {
	private Map<String, Location> namedHomeLocations;

	public NamedHomes() {
		this(new HashMap<String, Location>());
	}

	public NamedHomes(Map<String, Location> namedHomeLocations) {
		this.namedHomeLocations = namedHomeLocations;
	}

	public void putNamedHome(String name, Location location) {
		namedHomeLocations.put(name, location);
	}

	public Map<String, Location> getNamedHomeLocations() {
		return namedHomeLocations;
	}

	public Location getNamedHomeLocation(String homename) {
		return namedHomeLocations.get(homename);
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> data = New.newMap();

		for (String name : namedHomeLocations.keySet()) {
			data.put(name, namedHomeLocations.get(name));
		}

		return data;
	}

	public static NamedHomes deserialize(Map<String, Object> data) {
		Map<String, Location> namedHomeLocations = New.newMap();

		for (String name : data.keySet()) {
			if (!(name.equals("=="))) {
				namedHomeLocations.put(name, (Location) data.get(name));
			}
		}

		return new NamedHomes(namedHomeLocations);
	}
}
