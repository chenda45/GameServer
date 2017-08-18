package com.koogame.controller;

import com.koogame.model.Message;

public class LoginController implements BaseController{

	@Override
	public void onMesaageRevicve(Message message) {
		if(message.getMsgId() == 1)  //登录
		{
			
		} 
	}

}
