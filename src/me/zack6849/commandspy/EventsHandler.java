package me.zack6849.commandspy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventsHandler implements Listener {
	CommandSpy plugin;
	public EventsHandler(CommandSpy CommandSpy) {
		plugin = CommandSpy;
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		Player ep = e.getPlayer();
		int x = ep.getLocation().getBlockX();
		int y = ep.getLocation().getBlockY();
		int z = ep.getLocation().getBlockZ();
		for(Player p : Bukkit.getOnlinePlayers()){
			if(plugin.users.contains(p.getName())){			
				String msg = String.format("[CS] %s used command %s at (%s,%s,%s) in world '%s'", ep.getName(), e.getMessage(),x,y,z,ep.getWorld().getName());
				p.sendMessage(msg);
			}
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		if(plugin.users.contains(e.getPlayer().getName())){
			plugin.users.remove(e.getPlayer().getName());
		}
	}
	@EventHandler
	public void onKick(PlayerKickEvent e){
		if(plugin.users.contains(e.getPlayer().getName())){
			plugin.users.remove(e.getPlayer().getName());
		}
	}
}
