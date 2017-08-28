package com.trafalcraft.mobArena.controllers;

import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;

import com.google.common.collect.Maps;
import com.trafalcraft.mobArena.models.MAPlayer;
import com.trafalcraft.mobArena.util.FileControler;

public class MAPlayerControll {
private final static Map<String, MAPlayer> inGamePlayers = Maps.newHashMap();
	
	public static void addPlayer(String name, String arene){
	
		if(checkJoin(name, arene) == true){
			
			MAPlayer joueur = new MAPlayer(arene, "in-game");
			inGamePlayers.put(name, joueur);
			ArenaControll.getArena(arene).addPlayer(Bukkit.getPlayer(name));
			
			Location loc = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")),FileControler.getArena(arene).getDouble("lobby.x"),FileControler.getArena(arene).getDouble("lobby.y"),FileControler.getArena(arene).getDouble("lobby.z"),(float)FileControler.getArena(arene).getDouble("lobby.yaw"),(float)FileControler.getArena(arene).getDouble("lobby.pitch"));
			Bukkit.getPlayer(name).teleport(loc);
			Bukkit.getPlayer(name).setHealth(20);
			Bukkit.getPlayer(name).setFoodLevel(20);
			Bukkit.getPlayer(name).setSaturation(20);
			Bukkit.getPlayer(name).getInventory().clear();
			Bukkit.getPlayer(name).setGameMode(GameMode.SURVIVAL);
			
			//TODO boucle envoie /msg name a join (x/10)
			
		}
						
	}
	
	public static boolean checkJoin(String name, String arene){
		if(!inGamePlayers.containsKey(name)){
			if(ArenaControll.contains(arene)){
				if(ArenaControll.getArena(arene).getStatus().equalsIgnoreCase("lobby")){
					if(ArenaControll.getArena(arene).getPlayerList().size() < FileControler.getArena(arene).getInt("maxplayer")){
						if(ArenaControll.getArena(arene).getLobbyTime() != 0 && ArenaControll.getArena(arene).getLobbyTime() < 3){
							return true;
						}else{
							return false;
							//msg partie en cour
						}
					}else{
						return false;
						//msg partie pleine
					}
				}else{
					return false;
					//msg partie en cour
				}
			}else{
				return false;
				//msg arene exite pas
			}
		}else{
			return false;
		}
	}
	
	public static void removePlayer(String name){
			inGamePlayers.remove(name);
	}
	
	public static MAPlayer getJoueur(String s){
		return inGamePlayers.get(s);
	}
	
	public static boolean contains(String s){
		if(inGamePlayers.containsKey(s)){
			return true;
		}else{
			return false;
		}
	}
	
	public static Set<String> getList(){
		return inGamePlayers.keySet();
	}
}
