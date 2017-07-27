package com.koogame.bean;

import java.io.Serializable;

public class Ability implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid;
	private String ability;
	private int level;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
