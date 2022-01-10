package com.gmail.akashirt53072.dicesystem;

import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		
		
		this.getCommand("dice").setExecutor(new DiceCommand(this));
		
	}
}
