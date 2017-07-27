package com.koogame.mapper;

import com.koogame.bean.Endless;



public interface EndlessMapper {
	
	public Endless findEndlessByImei(String imei);
	
	public Endless findEndlessByRank(int rank);
	
 
}
