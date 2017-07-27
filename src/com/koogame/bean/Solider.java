package com.koogame.bean;

import java.io.Serializable;

public class Solider implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int uid;
	private String solider;
	private int level;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSolider() {
		return solider;
	}
	public void setSolider(String solider) {
		this.solider = solider;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
