package com.koogame.netty; 

import com.koogame.bean.Message; 
import io.netty.channel.Channel; 
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
  
public class NettyServerHandler extends SimpleChannelInboundHandler<Message>	{ 
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception {
		// TODO Auto-generated method stub
		if (msg == null) {   
            return;  
        }  
		Channel incoming = ctx.channel();
		incoming.writeAndFlush(msg);
	} 
	 
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		System.out.println("-----------------user come-----------------------");
	} 
	
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	Channel incoming = ctx.channel();
        if(!incoming.isActive())
        System.out.println("SimpleClient:" + incoming.remoteAddress()
                + "异常");
    	cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
//        Channel incoming = ctx.channel();
//        for (Channel channel : channels) {
//            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
//        }
//        channels.add(ctx.channel());
    	System.out.println("#############have one user come in##################");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
//        Channel incoming = ctx.channel();
//        for (Channel channel : channels) {
//            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
//        }
//        channels.remove(ctx.channel());
    	
    	System.out.println("#############have one user come out##################");
    } 
 
}
