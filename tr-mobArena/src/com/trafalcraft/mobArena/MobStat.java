package com.trafalcraft.mobArena;

import org.bukkit.entity.EntityType;

public enum MobStat {
	
	zombie(20,true,1, EntityType.ZOMBIE),
	squelette(30, true,4, EntityType.SKELETON),
	blaze(50,true,10, EntityType.BLAZE),
	//creeper(50,false),
	witherskelette(50,true,8, EntityType.WITHER_SKELETON),
	spider(20,true,3, EntityType.SPIDER),
	cavespider(30,true,5, EntityType.CAVE_SPIDER),
	pigman(10,true,5, EntityType.PIG_ZOMBIE),
	enderman(50,true,10, EntityType.ENDERMAN),
	bear(50,true,8, EntityType.POLAR_BEAR),
	//spiderjoket(50,true),
	magmacube(75,true,8, EntityType.MAGMA_CUBE),
	endermite(10,true,3, EntityType.ENDERMITE),
	silverfisch(10,true,3, EntityType.SILVERFISH),
	slime(50,true,5, EntityType.SLIME),
	witch(30,true,8, EntityType.WITCH),
	vex(30,true,5, EntityType.VEX),
	vindicator(30,true,3, EntityType.VINDICATOR),
	golem(100,true,15, EntityType.IRON_GOLEM),
	snowman(50,true,8, EntityType.SNOWMAN)
	;

	private int life;
	private boolean fight;
	private int value;
	private EntityType type;
	
	MobStat(int i, boolean b,int v, EntityType t){
		life = i;
		fight = b;
		value = v;
		type = t;
	}
	
	public EntityType getType(){
		return type;
	}
	
	public int getLife(){
		return life;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public boolean getFight(){
		return this.fight;
	}
}
