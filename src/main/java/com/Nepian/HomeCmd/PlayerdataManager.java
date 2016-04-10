package com.Nepian.HomeCmd;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

import com.Nepian.HomeCmd.Data.Playerdata;
import com.Nepian.HomeCmd.Util.New;
import com.Nepian.HomeCmd.Util.Utility;

public class PlayerdataManager {
	private static Map<UUID, Playerdata> playerdatas;

	public static void load(File folder) {
		playerdatas = New.newMap();

		for (File file : folder.listFiles()) {
			YamlConfiguration data = Utility.getYml(file);
			Playerdata playerdata = (Playerdata) data.get("playerdata");

			if (playerdata == null) {
				System.out.println(file.getName() + "のデータを取得できませんでした");
				continue;
			}

			playerdatas.put(playerdata.getUid(), playerdata);
		}
	}

	public static void save(File folder) {
		for (Playerdata playerdata : playerdatas.values()) {
			File file = new File(folder, playerdata.getUid().toString() + ".yml");
			YamlConfiguration data = Utility.getYml(file);

			data.set("playerdata", playerdata);

			try {
				data.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void putPlayerdata(UUID uuid, Playerdata playerdata) {
		playerdatas.put(uuid, playerdata);
	}

	public static Playerdata getPlayerdata(UUID uuid) {
		return playerdatas.get(uuid);
	}

	public static boolean hasPlayerdata(UUID uuid) {
		return playerdatas.containsKey(uuid);
	}
}
