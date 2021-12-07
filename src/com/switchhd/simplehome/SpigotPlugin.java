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
    	Plugin.RegisterCommands(this);
    }

    @Override
    public void onDisable() {

    }
    
    
}
