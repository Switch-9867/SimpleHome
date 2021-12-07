/**
 * 
 */
package com.switchhd.simplehome;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author switchhd
 *
 */
public class SpigotPlugin extends JavaPlugin {
	
    @Override
    public void onEnable() {
    	this.getCommand("home").setExecutor(new CommandHome());
    	//Plugin.RegisterCommands(this);
    }

    @Override
    public void onDisable() {

    }
    
    
}
