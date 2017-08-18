package com.koogame.controller;
   
import org.springframework.stereotype.Service;

import com.koogame.model.Message;
import com.koogame.model.Player;

@Service("mainController") 
public class MainController {
	
	public static int LOGINID = 1 ;
	public static int MAINID = 2 ; 
	
	
	/**
	 * 消息分发
	 * @param message 消息
	 * @param player  玩家数据
	 */
	public void DispatchMessage(Message message,Player player)
	{
		 
		player.getChannel().writeAndFlush(message);
		
		//登录消息
		if(message.getTypeId() == LOGINID)
		{
			
		}
		else if (message.getTypeId() == MAINID)
		{
			
			
		}
	}

}
