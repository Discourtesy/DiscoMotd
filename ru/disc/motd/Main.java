package ru.disc.motd;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class Main extends Plugin implements Listener {

	public static Plugin plugin;
	
	public static String motd_1;
	public static String motd_2;
	public static String protocol_name;
	
	
	@Override
	public void onEnable() {
		plugin = this;
		
		YamlGenerator yg = new YamlGenerator();
		yg.saveDefaultConfig();
		loadYaml();
		BungeeCord.getInstance().getPluginManager().registerListener(this, this);
	}
	
	public static void loadYaml() {
		motd_1 = YamlGenerator.config.getString("Motd_1");
		motd_2 = YamlGenerator.config.getString("Motd_2");
		protocol_name = YamlGenerator.config.getString("Protocol_Name");
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPing(ProxyPingEvent e) {
		String Motd_1 = ChatColor.translateAlternateColorCodes('&', YamlGenerator.config.getString("Motd_1"));
		String Motd_2 = ChatColor.translateAlternateColorCodes('&', YamlGenerator.config.getString("Motd_2"));
		String Protocol_Name = ChatColor.translateAlternateColorCodes('&', YamlGenerator.config.getString("Protocol_Name"));
		ServerPing ping = e.getResponse();
		ServerPing.Players players = ping.getPlayers();
		ServerPing.Protocol version = ping.getVersion();
		version.setName(Protocol_Name);
		version.setProtocol(47);
		ping.setPlayers(players);
		ping.setVersion(version);
		
		ping.setDescription(Motd_1 + ChatColor.RESET + "\n" + 
				            Motd_2);
		
		e.setResponse(ping);
	}
}
