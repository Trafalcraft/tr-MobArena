package com.trafalcraft.mobArena.models;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.inventivetalent.bossbar.BossBarAPI;
import org.inventivetalent.bossbar.BossBarAPI.Color;
import org.inventivetalent.bossbar.BossBarAPI.Property;
import org.inventivetalent.bossbar.BossBarAPI.Style;

import com.trafalcraft.mobArena.Main;
import com.trafalcraft.mobArena.controllers.MAPlayerControll;

import net.md_5.bungee.api.chat.TextComponent;

public class Arena {
	String name;
	String status;
	int lobbyTime;
	ArrayList<Player> playerList = new ArrayList<Player>();
	ArrayList<Block> blockBreack = new ArrayList<Block>();
	private int taskLobby;
	int wave;
	
	public Arena(String name){
		this.name = name;
	}
	
	public void addBlock(Block b){
		blockBreack.add(b);
	}
	
	public Block getBlock(){
		return blockBreack.get(0);
	}
	
	public void removeBlock(){
		blockBreack.remove(0);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setStatus(String s){
		this.status = s;
	}
	
	public void addPlayer(Player p){
		this.playerList.add(p);
		
		if(playerList.size() == 2){
			this.startLobbyTimer();
			
			for(Player pl : getPlayerList()){
				BossBarAPI.removeAllBars(pl);
				BossBarAPI.addBar(pl, new TextComponent("§b" + "pve"), Color.BLUE, Style.PROGRESS, 1.0f, 620, 1, Property.PLAY_MUSIC);
			}
		}else{
			if(getLobbyTime() != 0){
				
				BossBarAPI.removeAllBars(p);
				BossBarAPI.addBar(p, new TextComponent("§b" + "pve"), Color.BLUE, Style.PROGRESS, 1.0f);
			
			}else{
				
				BossBarAPI.removeAllBars(p);
				BossBarAPI.addBar(p, new TextComponent("§b" + "pve"), Color.BLUE, Style.PROGRESS, (float)1, 20*getLobbyTime(), 1, Property.PLAY_MUSIC);
				
			}
		}
	}
	
	public void removePlayer(Player p){
		this.playerList.remove(p);
		
		if(playerList.size() == 1){
			if(getLobbyTime() != 0){
				this.stopLobbyTimer();
				this.status = "lobby";
				
				for(Player pl : getPlayerList()){
					//msg stop lobby timer
					
					BossBarAPI.removeAllBars(pl);
					BossBarAPI.addBar(pl, new TextComponent("§b" + "pve"), Color.BLUE, Style.PROGRESS, 1.0f);
				}
				
			}
		}
	}
	
	public void setLobbyTime(int i){
		this.lobbyTime = i;
	}
	
	public int getLobbyTime(){
		return this.lobbyTime;
	}
	
	public ArrayList<Player> getPlayerList(){
		return this.playerList;
	}
	
	public void startLobbyTimer(){
		this.lobbyTime = 31;
		this.taskLobby = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
						//for(Player pl : getPlayerList()){
							//pl.setLevel(temps);
						//}
                   		if(lobbyTime == 30||lobbyTime == 20||lobbyTime == 10||(lobbyTime <= 5 && lobbyTime>0)){
                   			for(Player pl : getPlayerList()){
                   				//msg la partie commence dans
                   				pl.playSound(pl.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
                   				
                   			}
                   		
                   		}else if(lobbyTime <= 0){
                   			for(Player pl : getPlayerList()){
                   				//msg la partie commence
                   				pl.playSound(pl.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0F, 1.0F);
                   				
                   				BossBarAPI.removeAllBars(pl);
                   				BossBarAPI.addBar(pl, new TextComponent("§b" + "pve"), Color.BLUE, Style.PROGRESS, 1.0f);
                   				
                   				MAPlayerControll.getJoueur(pl.getName()).setStatus("in-game");
               					pl.setHealth(20);
               					pl.setSaturation(0);
               					pl.setFoodLevel(20);
               					for(PotionEffect effect : pl.getActivePotionEffects()){
               						pl.removePotionEffect(effect.getType());
               					}
                   			}
                   			
                   			stopLobbyTimer();
               				//TODO startGameTimer();
               				status = "in-game";
               				//TODO Tp.tpStart(getName());
               				taskLobby = 0;
               				//TODO score = new ScoreBoard(getName());
               				
                   		}
            	lobbyTime = lobbyTime-1;
            }
         }, 0, 20);
	}
	
	public void stopLobbyTimer(){
		Bukkit.getServer().getScheduler().cancelTask(this.taskLobby);
	}
	
	public void newWave(){
		
	}
}
