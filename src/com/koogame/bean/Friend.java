package com.koogame.bean;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class Friend implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid;
	private int fuid; 
	
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
	
	@Override
	public String toString() {
		JSONObject res =new  JSONObject();
		res.put("uid", this.uid);
		res.put("fuid", this.fuid); 
		return res.toString();
	}
	
	

}
