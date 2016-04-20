package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.Nepian.HomeCmd.Messenger;
import com.Nepian.HomeCmd.PlayerdataManager;
import com.Nepian.HomeCmd.SQLiteManager;
import com.Nepian.HomeCmd.Command.SubCommand;
import com.Nepian.HomeCmd.Configuration.Properties;
import com.Nepian.HomeCmd.Data.NamedHomes;
import com.Nepian.HomeCmd.Data.Playerdata;
import com.Nepian.HomeCmd.Util.PlayerUtil;

public class ConvertCommand extends SubCommand {
	
	public ConvertCommand() {
		super("convert");
		setPermission("homecmd.convert");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {

		if (!(sender instanceof ConsoleCommandSender)) {
			return;
		}
		
		boolean overwrite = true;
		
		if (args.length == 0) {
			overwrite = false;
		}
		
		String def = Properties.DEFAULT_HOME_NAME;
		Map<UUID, Playerdata> map = PlayerdataManager.getPlayerdatas();
		
		for (UUID uuid : map.keySet()) {
			Playerdata data = map.get(uuid);
			OfflinePlayer player = PlayerUtil.getOfflinePlayer(uuid);
			
			if (overwrite || (!overwrite && !SQLiteManager.has(player, def))) {
				SQLiteManager.insert(player, def, data.getDefaultHome());
				Messenger.log("Insert data: PlayerName: " + player.getName() + " HomeName: " + def);
			}
			
			NamedHomes namedHomes = data.getNamedHomes();
			Map<String, Location> homes = namedHomes.getNamedHomeLocations();
			
			for (String name : homes.keySet()) {
				Location home = homes.get(name);
				
				if (overwrite || (!overwrite && !SQLiteManager.has(player, name))) {
					SQLiteManager.insert(player, name, home);
					Messenger.log("Insert data: PlayerName: " + player.getName() + " HomeName: " + name);
				}
			}
		}
	}

	@Override
	public String getPossibleArguments() {
		return "<(overwrite)>";
	}

	@Override
	public int getMinimumArguments() {
		return 0;
	}

	@Override
	public List<String> getTutorial() {
		return null;
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.HIDDEN;
	}

}
