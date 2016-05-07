package com.Nepian.HomeCmd;

import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.HomeCmd.Configuration.Config;
import com.Nepian.HomeCmd.Configuration.SerializationClassRegister;
import com.nepian.myhomes.Myhomes;
import com.nepian.npcore.util.CommandUtil;

public class Main extends JavaPlugin {
	public static JavaPlugin plugin;
	public Myhomes myhomes;
	
	@Override
	public void onEnable() {
		plugin = this;
		myhomes = Myhomes.getPlugin();

		Messenger.load(plugin);
		Config.load(FileManager.FILE_CONFIG);
		SQLiteManager.load(FileManager.FILE_SQLITE);
		SerializationClassRegister.load();
		CommandUtil.registerCommand(plugin, "convert",
				new ConvertToMyhomesCommand(myhomes.getMessenger(), myhomes));
//		CommandManager.load(plugin);
//		PlayerdataManager.load(FileManager.FOLDER_USERS);
//		ListenerManager.load(plugin);
	}

	@Override
	public void onDisable() {
//		PlayerdataManager.save(FileManager.FOLDER_USERS);
		SQLiteManager.close();
		Config.save();
	}
}
