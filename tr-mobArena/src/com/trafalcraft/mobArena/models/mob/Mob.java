package com.trafalcraft.mobArena.models.mob;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;

import com.trafalcraft.mobArena.Main;
import com.trafalcraft.mobArena.controllers.ArenaControll;

public class Mob {
	private String arene;
	private Entity mob;
	private double vie;
	private int taskia;
	private EntityType type;
	
	public Mob(EntityType e, int l, String a){
		this.type = e;
		this.vie = l;
		this.arene = a;
	}
	
	public void spawn(Location loc){
		if(type == EntityType.ZOMBIE){
			this.mob = loc.getWorld().spawn(loc, Zombie.class);
		}else if(type == EntityType.SKELETON){
			this.mob = loc.getWorld().spawn(loc, Skeleton.class);
		}else if(type == EntityType.BLAZE){
			this.mob = loc.getWorld().spawn(loc, Blaze.class);
		}else if(type == EntityType.WITHER_SKULL){
			this.mob = loc.getWorld().spawn(loc, WitherSkull.class);
		}else if(type == EntityType.SPIDER){
			this.mob = loc.getWorld().spawn(loc, Spider.class);
		}else if(type == EntityType.CAVE_SPIDER){
			this.mob = loc.getWorld().spawn(loc, CaveSpider.class);
		}else if(type == EntityType.PIG_ZOMBIE){
			this.mob = loc.getWorld().spawn(loc, PigZombie.class);
		}else if(type == EntityType.ENDERMAN){
			this.mob = loc.getWorld().spawn(loc, Enderman.class);
		}else if(type == EntityType.POLAR_BEAR){
			this.mob = loc.getWorld().spawn(loc, PolarBear.class);
		}else if(type == EntityType.MAGMA_CUBE){
			this.mob = loc.getWorld().spawn(loc, MagmaCube.class);
		}else if(type == EntityType.ENDERMITE){
			this.mob = loc.getWorld().spawn(loc, Endermite.class);
		}else if(type == EntityType.SILVERFISH){
			this.mob = loc.getWorld().spawn(loc, Silverfish.class);
		}else if(type == EntityType.SLIME){
			this.mob = loc.getWorld().spawn(loc, Slime.class);
		}else if(type == EntityType.WITCH){
			this.mob = loc.getWorld().spawn(loc, Witch.class);
		}else if(type == EntityType.VEX){
			this.mob = loc.getWorld().spawn(loc, Vex.class);
		}else if(type == EntityType.VINDICATOR){
			this.mob = loc.getWorld().spawn(loc, Vindicator.class);
		}else if(type == EntityType.IRON_GOLEM){
			this.mob = loc.getWorld().spawn(loc, IronGolem.class);
		}else if(type == EntityType.SNOWMAN){
			this.mob = loc.getWorld().spawn(loc, Snowman.class);
		}
		
		((Creature) mob).setHealth(vie);
	}
	
	
	public void ia(){
		this.taskia = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
        
		public void run() {
					
			double distance = -1;
			Player p = null;
			
			for(Player pl : ArenaControll.getArena(arene).getPlayerList()){
				
				Location loc = mob.getLocation();
				Location loc2 = pl.getLocation();
				
				double dist = Math.sqrt((loc.getX()-loc2.getBlockX())*(loc.getX()-loc2.getBlockX()) + (loc.getZ()-loc2.getZ())*(loc.getZ()-loc2.getZ()) + (loc.getY()-loc2.getY())*(loc.getY()-loc2.getY()));
				
				
				if(distance == -1){
					p = pl;
					distance = dist;
				}else if(dist < distance){
					p = pl;
					distance = dist;
				}
			}
			
			((Creature) mob).setTarget(p);
			
			
               		}
        	
     }, 0, 20);
	}
}
