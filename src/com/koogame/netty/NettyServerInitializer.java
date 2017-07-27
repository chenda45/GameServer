package com.koogame.netty;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel; 
 
public class NettyServerInitializer extends ChannelInitializer<SocketChannel>{
	
	@Autowired
    public NettyServerHandler nettyServerHandler;

	@Override
	protected void initChannel(SocketChannel sc) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = sc.pipeline();
		pipeline.addLast("decoder",new NettyServerDecoder());
        pipeline.addLast("encoder",new NettyServerEncoder());
        pipeline.addLast(nettyServerHandler);
         
	}

	public NettyServerHandler getNettyServerHandler() {
		return nettyServerHandler;
	}

	public void setNettyServerHandler(NettyServerHandler nettyServerHandler) {
		this.nettyServerHandler = nettyServerHandler;
	}
	
	

}
