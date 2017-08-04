package com.koogame.bean;

import java.io.Serializable;

import com.google.protobuf.MessageLite;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int typeId; 			//消息类型ID(对应模块)
	private int msgId;  			//消息ID
	private int length; 			//消息长度 
	private byte[] body;			//消息数据部分对应protocol
	private MessageLite lite ; 	//
	 
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public byte[] getBody() {
		return body;
	}
	public void setBody(byte[] body) {
		this.body = body;
	}
	public MessageLite getLite() {
		return lite;
	}
	public void setLite(MessageLite lite) {
		this.lite = lite;
	} 
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " typeId = " + typeId + " msgId = " + msgId + " length = " + length + " body = " + body;
	}
	 
}	
