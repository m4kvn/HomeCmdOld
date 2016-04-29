package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.Nepian.HomeCmd.Messenger;
import com.Nepian.HomeCmd.SQLiteManager;
import com.Nepian.HomeCmd.Command.SubCommand;

public class ShowCommand extends SubCommand {
	
	public ShowCommand() {
		super("show");
		setPermission("homecmd.show");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		
		if (!(sender instanceof ConsoleCommandSender)) {
			Messenger.sendFailed(sender, "このコマンドはコンソールからのみ使用できます");
			return;
		}
		
		Map<String, List<String>> datas = SQLiteManager.getDatas();
		
		for (String name : datas.keySet()) {
			List<String> list = datas.get(name);
			Messenger.log(name + ": " + list);
		}
	}

	@Override
	public String getPossibleArguments() {
		return null;
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
