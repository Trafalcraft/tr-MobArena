package com.trafalcraft.mobArena.models;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import com.trafalcraft.mobArena.Main;
import com.trafalcraft.mobArena.MobStat;
import com.trafalcraft.mobArena.controllers.ArenaControll;
import com.trafalcraft.mobArena.models.mob.Boss;
import com.trafalcraft.mobArena.models.mob.Mob;
import com.trafalcraft.mobArena.models.mob.Swarm;
import com.trafalcraft.mobArena.util.FileControler;

public class Waves {

	private int nbrWave;
	private int uniteMob = 0;
	private int unitevalue = 0;
	private String arene;
	private int maxMob;
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private ArrayList<Mob> waitMob = new ArrayList<Mob>();
	private Boss b;
	private int timeWaves;
	private int taskWaves;
	
	public Waves(String a, int waveNumber){
		this.arene = a;
		this.nbrWave = waveNumber;
		this.maxMob = FileControler.getArena(a).getInt("maxmob");
		timerAvantWaves();
	}
	
	public void timerAvantWaves(){
		this.timeWaves = 15;
		this.taskWaves = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
                   		if(timeWaves == 0){
                   			mobSender();
                   		}
            	timeWaves = timeWaves-1;
            }
         }, 0, 20);
	}
	
	public void stopLobbyTimer(){
		Bukkit.getServer().getScheduler().cancelTask(this.taskWaves);
	}
	
	public void loadConfig(String arene){
		
		this.uniteMob = FileControler.getArena(this.arene).getInt("waves." + this.nbrWave + ".unitemob");
		
		ArrayList<String> mobType = new ArrayList<String>();
		
		for(String aname : FileControler.getArena(this.arene).getConfigurationSection("defautmob").getKeys(false)){
			if(!mobType.contains(aname)){
				mobType.add(aname);
			}
		}
		for(String aname : FileControler.getArena(this.arene).getConfigurationSection("waves." + this.nbrWave + ".mobspeciaux").getKeys(false)){
			if(!mobType.contains(aname)){
				mobType.add(aname);
			}
		}
		
		this.unitevalue = this.uniteMob/mobType.size();
		
		ArrayList<Mob> preMob = new ArrayList<Mob>();
		
		for(String type : mobType){
			int nbr = unitevalue/MobStat.valueOf(type).getValue();
			
			for(int i = 0; i < nbr ; i++){
				Mob mb = new Mob(MobStat.valueOf(type).getType(), MobStat.valueOf(type).getLife(), this.arene);
				preMob.add(mb);
			}
		}
		
		while(preMob.size() > 0){
			int randowm = (int) (Math.random() * preMob.size());
			waitMob.add(preMob.get(randowm));
			preMob.remove(randowm);
		}
	}
	
	public void removeWaitMob(Mob m){
		if(waitMob.contains(m)){
			waitMob.remove(m);
		}
	}
	
	public void removeMob(Mob m){
		if(mobs.contains(m)){
			mobs.remove(m);
		}
	}
	
	public Mob getWaitMob(){
		return waitMob.get(0);
	}
	
	public int getUniteMob(){
		return this.getUniteMob();
	}
	
	public int getNbrWave(){
		return nbrWave;
	}
	
	public String getArene(){
		return this.arene;
	}
	
	public void mobSender(){
		if(this.maxMob < mobs.size()){
			while(this.maxMob < mobs.size()){
				mobs.add(getWaitMob());
				//TODO trois spawn les plus proche des joueur chifre randowm entre un et trois attention si moin de trois spawn
				removeWaitMob(getWaitMob());
			}
		}
	}
	
	public void removeSection(Location loc, Location loc2){
		for(int i1 = 0; loc.getX() + i1 <= loc2.getX() ; i1++){
			for(int i2 = 0; loc.getY() + i2 <= loc2.getY() ; i2++){
				for(int i3 = 0; loc.getZ() + i3 <= loc2.getZ() ; i3++){
					
					Location loc3 = new Location(loc.getWorld(), loc.getX() + i1, loc.getY() + i2, loc.getZ() + i3);
					ArenaControll.getArena(getArene()).addBlock(loc3.getBlock());
					loc3.getBlock().setType(Material.AIR);
				}
			}
		}
	}
	
	public void upGradeStuf(){
		//TODO
	}
	
}
