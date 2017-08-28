package com.trafalcraft.mobArena;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.mobArena.controllers.ArenaControll;
import com.trafalcraft.mobArena.controllers.MAPlayerControll;
import com.trafalcraft.mobArena.util.FileControler;

public class Main extends JavaPlugin{
	private static JavaPlugin plugin;
	
	public void onEnable(){
		plugin = this;
		
		File d = new File(getPlugin().getDataFolder().getPath()+"//arene");
		
		if(!(d.exists())){
			d.mkdir();
			d.mkdirs();
		}
		
		FileControler.loadAllFile();
		
		for(String s : FileControler.getAllName()){
			if(FileControler.getArena(s).getString("status").equalsIgnoreCase("on")){
				if(SecureConfig.testConfig(s) == true){
					
					ArenaControll.addArene(s);
					Bukkit.getLogger().info("arene " + s + " creer");
					
				}else{
					Bukkit.getLogger().warning("l'arene " + s + " est mal configuree");
				}
			}
		}	
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player) sender;
			
			if(command.getName().equalsIgnoreCase("mobarena")){
				if(args.length != 0){
					
					if(args[0].equalsIgnoreCase("create")){
						if(p.isOp()){
							if(!(MAPlayerControll.contains(p.getName()))){
								if(args.length == 2){
									if(!(FileControler.contains(args[1]))){
										FileControler.addFile(getPlugin().getDataFolder(), args[1]);
										FileControler.getArena(args[1]).set("name", args[1]);
										FileControler.getArena(args[1]).set("world", p.getWorld().getName());
										FileControler.getArena(args[1]).set("status", "off");
										FileControler.getArena(args[1]).set("nbrspawn", 0);
										FileControler.getArena(args[1]).set("nbrmobspawn", 0);
										FileControler.saveArena(args[1]);
										
										//msg arene créée
									}else{
										//msg arena exite deja
									}
								}else{
									//msg commande incomplete
								}
							}else{
								//msg pas config in game
							}
						}else{
							//msg pas permission
						}
						
					}else if(FileControler.contains(args[0])){
						if(p.isOp()){
							if(!MAPlayerControll.contains(p.getName())){
								if(ArenaControll.getArena(args[0]).getStatus().equalsIgnoreCase("lobby")){
									if(args.length != 0){
										if(args[1].equalsIgnoreCase("set")){
											if(args.length >= 3){
												if(args[2].equalsIgnoreCase("lobby")){
													
													FileControler.getArena(args[0]).set("lobby.x", p.getLocation().getX());
													FileControler.getArena(args[0]).set("lobby.y", p.getLocation().getY());
													FileControler.getArena(args[0]).set("lobby.z", p.getLocation().getZ());
													FileControler.getArena(args[0]).set("lobby.yaw", p.getLocation().getYaw());
													FileControler.getArena(args[0]).set("lobby.pitch", p.getLocation().getPitch());
													FileControler.saveArena(args[0]);
													
													//msg lobby save
												}else if(args[2].equalsIgnoreCase("generallobby")){
													
													FileControler.getArena(args[0]).set("generallobby.x", p.getLocation().getX());
													FileControler.getArena(args[0]).set("generallobby.y", p.getLocation().getY());
													FileControler.getArena(args[0]).set("generallobby.z", p.getLocation().getZ());
													FileControler.getArena(args[0]).set("generallobby.yaw", p.getLocation().getYaw());
													FileControler.getArena(args[0]).set("generallobby.pitch", p.getLocation().getPitch());
													FileControler.saveArena(args[0]);
													
													//msg lobby general save
												}else if(args[2].equalsIgnoreCase("spawn")){
													
													FileControler.getArena(args[0]).set("nbrspawn", FileControler.getArena(args[0]).getInt("nbrspawn") + 1);
													
													FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".x", p.getLocation().getX());
													FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".y", p.getLocation().getY());
													FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".z", p.getLocation().getZ());
													FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".yaw", p.getLocation().getYaw());
													FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".pitch", p.getLocation().getPitch());
													FileControler.saveArena(args[0]);
													
													//msg spawn X creer
												}else if(args[2].equalsIgnoreCase("mobspawn")){
													FileControler.getArena(args[0]).set("nbrmobspawn", FileControler.getArena(args[0]).getInt("nbrmobspawn") + 1);
													
													FileControler.getArena(args[0]).set("mobspawn." + FileControler.getArena(args[0]).getInt("nbrmobspawn") + ".x", p.getLocation().getX());
													FileControler.getArena(args[0]).set("mobspawn." + FileControler.getArena(args[0]).getInt("nbrmobspawn") + ".y", p.getLocation().getY());
													FileControler.getArena(args[0]).set("mobspawn." + FileControler.getArena(args[0]).getInt("nbrmobspawn") + ".z", p.getLocation().getZ());
													FileControler.getArena(args[0]).set("mobspawn." + FileControler.getArena(args[0]).getInt("nbrmobspawn") + ".yaw", p.getLocation().getYaw());
													FileControler.getArena(args[0]).set("mobspawn." + FileControler.getArena(args[0]).getInt("nbrmobspawn") + ".pitch", p.getLocation().getPitch());
													FileControler.saveArena(args[0]);
													
													//msg mod spawn X creer
												}else if(args[2].equalsIgnoreCase("pos1")){
													
													FileControler.getArena(args[0]).set("pos1.x", p.getLocation().getX());
													FileControler.getArena(args[0]).set("pos1.y", p.getLocation().getY());
													FileControler.getArena(args[0]).set("pos1.z", p.getLocation().getZ());
													FileControler.getArena(args[0]).set("pos1.yaw", p.getLocation().getYaw());
													FileControler.getArena(args[0]).set("pos1.pitch", p.getLocation().getPitch());
													FileControler.saveArena(args[0]);
													
													//msg pos1 set
													
												}else if(args[2].equalsIgnoreCase("pos2")){
													
													FileControler.getArena(args[0]).set("pos2.x", p.getLocation().getX());
													FileControler.getArena(args[0]).set("pos2.y", p.getLocation().getY());
													FileControler.getArena(args[0]).set("pos2.z", p.getLocation().getZ());
													FileControler.getArena(args[0]).set("pos2.yaw", p.getLocation().getYaw());
													FileControler.getArena(args[0]).set("pos2.pitch", p.getLocation().getPitch());
													FileControler.saveArena(args[0]);
													
													//msg pos2 set
													
												}else if(args[2].equalsIgnoreCase("maxplayer")){
													if(args.length == 4){
														try{
															Integer max1 = Integer.valueOf(args[3]);
															FileControler.getArena(args[0]).set("maxplayer", max1);
														
															//msg maxplayer enregistrer
														}catch(NumberFormatException e){
															//msg entrer nombre
														}
													}else{
														//msg arg invalide
													}
												}else if(args[2].equalsIgnoreCase("maxmob")){
													if(args.length == 4){
														try{
															Integer max1 = Integer.valueOf(args[3]);
															FileControler.getArena(args[0]).set("maxmob", max1);
															
															//msg maxmob enregistrer
														}catch(NumberFormatException e){
															//msg entrer nombre
														}
													}else{
														//msg arg invalide
													}
												}else if(args[2].equalsIgnoreCase("status")){
													if(args.length == 4){
														if(FileControler.getArena(args[0]).getString("status").equalsIgnoreCase("off")){
															if(SecureConfig.testConfig(args[0]) == true){
																FileControler.getArena(args[0]).set("status", args[4]);
																
																//msg status enregistrer
															}else{
																//msg arene mal config
															}
														}else{
															//msg status deja on
														}
													}else{
														//msg arg invalide
													}
												}else{
													//msg arg invalide
												}
											}
										}else if(args[1].equalsIgnoreCase("delete")){
											if(args.length == 3){
												if(args[2].equalsIgnoreCase("spawn")){
													
													FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") , null);
													FileControler.getArena(args[0]).set("nbrspawn", FileControler.getArena(args[0]).getInt("nbrspawn") + 1);
													FileControler.saveArena(args[0]);
													
													//msg spawn x detruit
													
												}else if(args[2].equalsIgnoreCase("mobspawn")){
													
													FileControler.getArena(args[0]).set("mobspawn." + FileControler.getArena(args[0]).getInt("nbrmobspawn") , null);
													FileControler.getArena(args[0]).set("nbrmobspawn", FileControler.getArena(args[0]).getInt("nbrmobspawn") + 1);
													FileControler.saveArena(args[0]);
													
													//msg mod spawn x detruit
													
												}else{
													//msg arg invalide
												}
											}else{
												//msg commande incomplete
											}
										}
									}else{
										//msg commande incomplete
									}
								}else{
									//msg pas configure en cour
								}
							}else{
								//msg pas config in game
							}
						}else{
							//msg no permission 
						}
					}else if(args[0].equalsIgnoreCase("join")){
						if(args.length == 1){
							//TODO check join
						}else{
							//msg arguments invalide
						}
					}else if(args[0].equalsIgnoreCase("leave")){
						//TODO leave arene et objet
						//TODO send jeux
					}else if(args[0].equalsIgnoreCase("fstart")){
						//TODO objet arene et timer debut
					}else{
						//msg arg incorect
					}
					
					
				}else{
					//msg pas d'argument
				}
			}
			
		}else{
			//TODO return message pas de commande en console ou creer possibiliter commande console
		}
		
		return false;
	}
	
	public static JavaPlugin getPlugin(){
		return plugin;
	}
}