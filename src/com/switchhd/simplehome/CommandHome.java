package com.switchhd.simplehome;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHome implements CommandExecutor{
	
	// create constants that define the cooldown
	private final TimeUnit timeUnit = TimeUnit.SECONDS;
	private final long teleportCooldown = 60l;
	
	// create a data structure that associates players with a Date object.
	// I'll use this Date to remember the last time this command was run.
	public Map<UUID, Date> PlayerHomeCooldown = new HashMap<UUID, Date>(); 
	
	//this is the function that will run when somebody executes this command
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//throw an error and return if the command sender was not a player
		if (sender instanceof Player == false) return false;
		
		// convert the sender to a usable player object
		Player player = (Player) sender;
		UUID pUUID = player.getUniqueId();
		
		// get the players' home location
		Location homeLocation = player.getBedSpawnLocation();
		
		// if the retrieved home location is not valid, notify the user and return.
		if(homeLocation == null) {
			player.sendMessage("Your home is missing or invalid");
			return true;
		}
		
		// collect the current time
		Date t = new Date(System.currentTimeMillis());
		
		// next I want to know if the player has ran the command before.
		// if PlayerHomeCooldown contains the player then the player has run the command before
		if (PlayerHomeCooldown.containsKey(pUUID)) {
			
			// get the change in time between now and the last time this command was run
			long dt = t.getTime() - PlayerHomeCooldown.get(pUUID).getTime();
			// and convert it from MILLISECONDS to SECONDS
			long diffrence = timeUnit.convert(dt, TimeUnit.MILLISECONDS);
			
			// check if the change in time is less than our cooldown time
			if (diffrence < teleportCooldown) {
				//if so...
				
				//calculate the time left and report that to the user and return
				long timeLeft = teleportCooldown - diffrence;
				player.sendMessage("You must wait " + timeLeft + " seconds before you can teleport again.");
				return true;
			}

		}
		
		// since the players' home location is valid
		// OR the player has not run this command before 
		// OR the change in time is greater than our cooldown time...
		
		// Record the time
		PlayerHomeCooldown.put(pUUID, t);
		
		// notify the user and teleport them.
		player.sendMessage("Teleporting Home");
		player.teleport(homeLocation);
		
		return true;
	}
	
}
