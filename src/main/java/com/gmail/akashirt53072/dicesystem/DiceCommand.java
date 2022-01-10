package com.gmail.akashirt53072.dicesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class DiceCommand implements CommandExecutor{
	
	private Main plugin;
	
	public DiceCommand(Main plugin) {
	    this.plugin = plugin;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
            	dice(player,6,1);
            	return true;
            }
            ArrayList<String> numberData = new ArrayList<String>();
            for(int i = 0;i < args[0].length();i ++) {
             	char c = args[0].charAt(i);
             	String ca = String.valueOf(c);
             	if(ca.matches("[0-9]")) {
             		numberData.add(ca);
             	}
            }
            if(numberData.isEmpty()) {
            	player.sendMessage(ChatColor.RED + args[0] + "には数字がありません");
            	return true;
            }
            int result = 0;
            int size = numberData.size();
            for(int i = 0;i < size;i ++) {
            	int m = (int)Math.pow(10, size - 1 - i);
            	 result += Integer.valueOf(numberData.get(i)) * m;
            }
            if(result < 1) {
            	player.sendMessage(ChatColor.RED + args[0] + "は0以下です");
            	return true;
            }
            if(args.length == 1) {
            	dice(player,result,1);
            	return true;
            }
            
            ArrayList<String> numberData1 = new ArrayList<String>();
            for(int i = 0;i < args[1].length();i ++) {
             	char c = args[1].charAt(i);
             	String ca = String.valueOf(c);
             	if(ca.matches("[0-9]")) {
             		numberData1.add(ca);
             	}
            }
            if(numberData1.isEmpty()) {
            	player.sendMessage(ChatColor.RED + args[1] + "には数字がありません");
            	return true;
            }
            int result1 = 0;
            int size1 = numberData1.size();
            for(int i = 0;i < size1;i ++) {
            	int m = (int)Math.pow(10, size1 - 1 - i);
            	 result1 += Integer.valueOf(numberData1.get(i)) * m;
            }
            if(result1 < 1) {
            	player.sendMessage(ChatColor.RED + args[1] + "は0以下です");
            	return true;
            }
            
            dice(player,result,result1);
            return true;
		}
        return false;
    }
	private void dice(Player player,int result,int result1) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		if(result1 > 100) {
			player.sendMessage(ChatColor.RED + "ダイスを振る回数が多すぎます");
			return;
		}
        for(int i = 0; i < result1; i++) {
        	int dice = new Random().nextInt(result) + 1;
        	results.add(dice);
        }
		List<Player> players = player.getWorld().getPlayers();
        for(Player p : players) {
        	if(player.getLocation().distance(p.getLocation()) > 50) {
        		continue;
        	}
        	p.sendMessage(player.getName() + "さんが" + result + "面のダイスを" + result1 + "回振りました");
        	String numbers = "";
        	int total = 0;
        	for(int i = 0;i < results.size();i++){
        		int number = results.get(i);
        		if(i == 0) {
        			numbers = numbers + number;
        		}else {
        			numbers = numbers + "と" + number;
        		}
        		total += number;
        	}
        	p.sendMessage(numbers + "が出ました");
        	p.sendMessage("合計は" + total + "です");
        }
	}
}
