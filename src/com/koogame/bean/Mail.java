package com.koogame.bean;

import net.sf.json.JSONObject;

public class Mail {
	
	private int id;
	private String dt;
	private int uid;
	private int fuid;
	private int level;
	private int type; 
	private String context;
	private boolean opened;
	private boolean accept;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getFuid() {
		return fuid;
	}
	public void setFuid(int fuid) {
		this.fuid = fuid;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public boolean isOpened() {
		return opened;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	public boolean isAccept() {
		return accept;
	}
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		
		JSONObject res = new JSONObject();
		res.put("id", this.id);
		res.put("dt", this.dt); 
		res.put("uid", this.uid);
		res.put("level", this.level);
		res.put("type", this.type); 
		
		return res.toString();
	}
	
	

}
