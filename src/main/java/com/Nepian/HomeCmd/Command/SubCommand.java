package com.Nepian.HomeCmd.Command;

import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
	private String name;
	private String permission;
	private String[] aliases;

	public SubCommand(String name, String... aliases) {
		this.name = name;
		this.aliases = aliases;
	}

	public SubCommand(String name) {
		this(name,new String[0]);
	}

	/**
	 * コマンドを実行する
	 * @param sender -> コマンド実行者
	 * @param label -> 使用したコマンド名
	 * @param args -> コマンドの引数
	 * @throws CommandException
	 */
	public abstract void execute(CommandSender sender,
			String label, String[] args) throws CommandException;

	/**
	 * コマンドの送信者が権限を所持しているか判定する
	 * @param sender -> コマンドの送信者
	 * @return (非所持)-> false (所持)-> true
	 */
	public final boolean hasPermission(CommandSender sender) {
		if (permission == null) return true;
		return sender.hasPermission(permission);
	}

	/**
	 * 指定した文字列がコマンドとして適正か判断する
	 * @param name -> コマンド名
	 * @return (適正)-> true (不適正)-> false
	 */
	public final boolean isValidTrigger(String name) {
		if (this.name.equalsIgnoreCase(name)) {
			return true;
		}

		if (aliases != null) {
			for (String alias : aliases) {
				if (alias.equalsIgnoreCase(name)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * コマンドで可能な引数の文字列を取得する
	 * @return
	 */
	public abstract String getPossibleArguments();

	/**
	 * コマンドで最低限必要な引数の長さを取得する
	 * @return
	 */
	public abstract int getMinimumArguments();

	/**
	 * コマンドのチュートリアルメッセージを取得する
	 * @return
	 */
	public abstract List<String> getTutorial();

	/**
	 * コマンドのタイプを取得する
	 * @return
	 */
	public abstract SubCommandType getType();

	/**
	 * コマンドのタイプ
	 */
	public enum SubCommandType {
		GENERIC, HIDDEN
	}

	public String getName() {
		return name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
