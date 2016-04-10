package com.Nepian.HomeCmd;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import com.Nepian.HomeCmd.Data.NamedHomes;
import com.Nepian.HomeCmd.Data.Playerdata;

public class SerializationClassRegister {

	public static void load() {
		register(Playerdata.class);
		register(NamedHomes.class);
	}

	private static void  register(Class<? extends ConfigurationSerializable> clazz) {
		ConfigurationSerialization.registerClass(clazz);
	}
}
