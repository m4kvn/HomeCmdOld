package com.Nepian.HomeCmd;

import org.bukkit.configuration.serialization.ConfigurationSerialization;

import com.Nepian.HomeCmd.Data.Playerdata;

public class ConfigurationSerializationClassRegister {

	public static void load() {
		ConfigurationSerialization.registerClass(Playerdata.class);
	}
}
