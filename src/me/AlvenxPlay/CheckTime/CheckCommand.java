package me.AlvenxPlay.CheckTime;

import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CheckCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lab, String[] arg){
		if(!s.hasPermission("time.check")){
			s.sendMessage("§7[§6*§7] §cNie masz uprawnien do tej komendy.");
			return true;
		}
		if(arg.length != 1){
			s.sendMessage("§7[§6*§7] §cZla liczba argumentow.");
			return true;
		}
		//=======================================================================================================================================
		String pnick = arg[0];
		String world = Bukkit.getWorlds().get(0).getName();
		String name = null;
		
		try{
			if(Bukkit.getPlayer(pnick).toString().replace("CraftPlayer{name=", "").replace("}", "").equals(pnick))
				name = Bukkit.getPlayer(pnick).getUniqueId().toString().replace("CraftOfflinePlayer[UUID=", "").replace("]", "");
		} catch (NullPointerException e){
			name = Bukkit.getOfflinePlayer(pnick).toString().replace("CraftOfflinePlayer[UUID=", "").replace("]", "");
		}
		
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(world +"/stats/" +name +".json"));
			long age = (Long) jsonObject.get("stat.playOneMinute");
			double hours = age / 20 / 60 / 60;
			double minutes = age / 20 / 60;
			s.sendMessage("§8[§6*§8] §c" +pnick +"§7 spedzil na serwerze §c" +hours +"§7 godz. (§c" +minutes +"§7 min.).");
			return true;
		} catch (IOException | ParseException e) {
			s.sendMessage("§8[§6*§8] §c" +pnick +"§7 nie istnieje w bazie danych.");
			return true;
		}
		//=======================================================================================================================================
		}
}
