package com.koogame.bean;

import java.io.Serializable;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid;  //
	private String name;
	private String dt; //注册时间
	private String channel; //渠道
	private String chnum;//渠道号(只针对XPAY)
	private String imei;
	private String device;  //设备型号
	private String os;   //系统(IOS,ANDROID)
	private String osversion;   //系统版本
	private String gversion;    //游戏版本
	private String net;   //网络类型
	private String ad;   //广告商 (主要针对IOS)
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	public String getGversion() {
		return gversion;
	}
	public void setGversion(String gversion) {
		this.gversion = gversion;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getChnum() {
		return chnum;
	}
	public void setChnum(String chnum) {
		this.chnum = chnum;
	}

}
