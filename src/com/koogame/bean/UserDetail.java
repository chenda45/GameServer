package com.koogame.bean;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserDetail implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id ;
	private int uid;
	private String name;
	private String hero;
	private int progress;
	private int level; 
	private String exp ;
	private String gold;
	private String gem;
	 
	private List<Equipment> elist;
	private List<Solider> slist;
	private List<Ability> alist; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getHero() {
		return hero;
	}
	public void setHero(String hero) {
		this.hero = hero;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public String getGem() {
		return gem;
	}
	public void setGem(String gem) {
		this.gem = gem;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Equipment> getElist() {
		return elist;
	}
	public void setElist(List<Equipment> elist) {
		this.elist = elist;
	}
	public List<Solider> getSlist() {
		return slist;
	}
	public void setSlist(List<Solider> slist) {
		this.slist = slist;
	}
	public List<Ability> getAlist() {
		return alist;
	}
	public void setAlist(List<Ability> alist) {
		this.alist = alist;
	}
	@Override
	public String toString() {
		JSONObject res = new JSONObject();
		res.put("uid", this.uid);  
		res.put("hero", this.hero); 
		res.put("progress", this.progress); 
		res.put("level", this.level); 
		res.put("exp", this.exp); 
		res.put("gold", this.gold); 
		res.put("gem", this.gem); 
		
		if(this.alist != null){
			JSONArray aja = new JSONArray();
			for (int i = 0; i < this.alist.size(); i++) {
				aja.add(this.alist.get(i));
			};
			res.put("ability",aja.toString());
		}
		if(this.elist != null){
			JSONArray eja = new JSONArray();
			for (int e = 0; e < this.elist.size(); e++) {
				eja.add(this.elist.get(e));
			};
			res.put("equipment",eja.toString());
		}
		if(this.slist != null){
			JSONArray sja = new JSONArray();
			for (int s = 0; s< this.slist.size(); s++) {
				sja.add(this.slist.get(s));
			};
			res.put("solider",sja.toString());
		}
		return res.toString();
	}
	
	
	
	
}
