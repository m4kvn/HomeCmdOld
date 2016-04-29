package com.Nepian.HomeCmd.Command.Sub;

import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Nepian.HomeCmd.Messenger;
import com.Nepian.HomeCmd.SQLiteManager;
import com.Nepian.HomeCmd.Command.SubCommand;
import com.Nepian.HomeCmd.Configuration.Properties;

public class DeleteCommand extends SubCommand {

	public DeleteCommand() {
		super("delete", "d", "del");
		setPermission("homecmd.delete");
	}
	
	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {

		if (!(sender instanceof Player)) {
			return;
		}
		
		Player player = (Player) sender;
		String name = (args.length == 0) ? Properties.DEFAULT_HOME_NAME : args[0];
		
		if (SQLiteManager.delete(player, name)) {
			Messenger.sendSuccess(sender, "ホーム (&6" + name + "&r) を削除しました");
		} else {
			Messenger.sendFailed(sender, "ホーム (&6" + name + "&r) の削除に失敗しました");
		}
		
	}

	@Override
	public String getPossibleArguments() {
		return "<ホーム>";
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
		return SubCommandType.GENERIC;
	}

}
