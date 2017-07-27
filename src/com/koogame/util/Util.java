package com.koogame.util;

import net.sf.json.JSONObject;

public class Util {
	
	
	public static String USERTYPE = "UserDetail";
	
	public static String FRIENDTYPE = "UserFriend";
	
	public static boolean getBoolean(JSONObject json,String arg){
		boolean temp = false;
		try {
			temp = json.getBoolean(arg);
		} catch (Exception e) {
			temp = false;
		}
		return temp;
	}
	
	public static int getInt(JSONObject json,String arg){
		int temp = -1;
		try {
			temp = json.getInt(arg);
		} catch (Exception e) {
			temp = -1;
		}
		return temp;
	}

}
