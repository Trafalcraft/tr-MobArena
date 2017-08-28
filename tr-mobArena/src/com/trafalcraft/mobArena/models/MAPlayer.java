package com.trafalcraft.mobArena.models;

import org.bukkit.Bukkit;

import com.trafalcraft.mobArena.Main;

public class MAPlayer {
	String arene;
	int kill;
	String status;
	int timeInLife = 0;
	int playerTask;
	
	public MAPlayer(String arena, String status){
		this.arene = arena;
		this.kill = 0;
		this.status = status;
		
	}
	
	public void startPlayerTimer(){
		this.playerTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
				
				timeInLife = timeInLife + 1;
			 }
        }, 0, 20);
	}
	
	public void stopPlayerTimer(){
		Bukkit.getServer().getScheduler().cancelTask(this.playerTask);
	}
	
	public String getArena(){
		return this.arene;
	}
	
	public int getKill(){
		return this.kill;
	}
	
	public void setKill(int i){
		this.kill = i;
	}
	
	public void setStatus(String s){
		if(s.equalsIgnoreCase("in-game") || s.equalsIgnoreCase("spec")){
			this.status = s;
		}
	}
	
	public String getStatus(){
		return this.status;
	}
}
