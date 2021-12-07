package com.switchhd.simplehome;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin {
	
	public static void RegisterCommands(JavaPlugin jpl) {
		jpl.getCommand("home").setExecutor(new CommandHome());
    }
	
}
