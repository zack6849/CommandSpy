package me.zack6849.commandspy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandSpy extends JavaPlugin{
	public static List<String> users = new ArrayList<String>();
	Logger log;
	public void onEnable(){
		this.log = getLogger();
		getServer().getPluginManager().registerEvents(new EventsHandler(this),this);
	}
	public void onDisable(){
		users.clear();
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("cst")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("commandspy.toggle")){
					if(users.contains(p.getName())){
						users.remove(p.getName());
						p.sendMessage(ChatColor.YELLOW + "[CS] " + ChatColor.WHITE + "You" + ChatColor.RED + " disabled " + ChatColor.WHITE +"CommandSpy");
						return true;
					}else{
						users.add(p.getName());
						p.sendMessage(ChatColor.YELLOW + "[CS] " + ChatColor.WHITE + "You" + ChatColor.GREEN + " enabled " + ChatColor.WHITE +"CommandSpy");
						return true;
					}
				}else{
					p.sendMessage(ChatColor.RED + "Error: You don't have permission to do that!");
					return true;
				}
			}else{
				sender.sendMessage("sorry, you need to be a player to use this command");
				return true;
			}
		}
		return false;
	}
}
