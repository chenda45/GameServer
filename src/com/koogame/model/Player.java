package com.koogame.model;

import io.netty.channel.Channel;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("player")
@Scope("prototype")
public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2272772178258834390L;
	
	//消息通道
	private Channel channel; 
	//玩家ID
	private int userId;
	//设备
	private String device;
	//手机号码
	private int telphone;
	
	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public int getTelphone() {
		return telphone;
	}
	public void setTelphone(int telphone) {
		this.telphone = telphone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
