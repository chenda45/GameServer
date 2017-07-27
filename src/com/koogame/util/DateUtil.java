package com.koogame.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 将DATE类型装换成字符串格式
	 * @param date
	 * @param type  要转换的类型 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDate2String(Date date,String type){
		SimpleDateFormat sdf = new SimpleDateFormat(type);
	    String time = sdf.format(date);	
		return time;
		
	}
}
