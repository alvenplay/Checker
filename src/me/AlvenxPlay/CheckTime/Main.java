package me.AlvenxPlay.CheckTime;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public void onEnable(){
		getCommand("check").setExecutor(new CheckCommand());
	}
}
