package com.koogame.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

import com.koogame.netty.SocketServer;

public class GameServer {
	private static final Logger logger = LoggerFactory
			.getLogger(GameServer.class);
	public static volatile boolean shutdown = false;
	private static GameServer server;

	private GameServer() {
	}

	public static GameServer getInstance() {
		if (null == server) {
			server = new GameServer();
		}
		return server;
	}

	/**
	 * @Title: startServer
	 * @Description: 开启服务器
	 * @throws
	 */
	public void startServer() {
		SocketServer.getInstance().start(); 
		shutdown = false;
	}

	/**
	 * @Title: shutServer
	 * @Description: 关闭服务器
	 * @throws
	 */
	public void shutServer() {
		SocketServer.getInstance().shut(); 
		shutdown = true;
	}
}
