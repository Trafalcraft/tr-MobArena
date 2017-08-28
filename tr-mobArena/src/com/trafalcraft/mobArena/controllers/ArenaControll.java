package com.trafalcraft.mobArena.controllers;

import java.util.Map;

import com.google.common.collect.Maps;
import com.trafalcraft.mobArena.models.Arena;

public class ArenaControll {
	private final static Map<String, Arena> activeMap = Maps.newHashMap();
	
	public static void addArene(String name){
		if(!activeMap.containsKey(name)){
			Arena arene = new Arena(name);
			activeMap.put(name, arene);
		}
	}
	
	public static boolean contains(String aname){
		if(activeMap.containsKey(aname)){
			return true;
		}
		return false;
	}
	
	public static void removeMap(String name){
		if(activeMap.containsKey(name)){
			activeMap.remove(name);
		}
	}
	
	public static Arena getArena(String name){
		return activeMap.get(name);
	}
}
