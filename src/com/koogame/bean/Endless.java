package com.koogame.bean;

public class Endless {
	
	private int uid;
	private String name;
	private int rank;
	private String dt;
	private int count;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "{\"count\":" + count + ",\"dt\":\"" + dt + "\",\"name\":\"" + name
				+ "\",\"rank\":" + rank + ",\"uid\":" + uid+"}" ;
	}
	
	
	
}
