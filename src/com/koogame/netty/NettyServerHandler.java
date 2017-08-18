package com.koogame.netty; 
 
import com.koogame.controller.MainController;
import com.koogame.model.Message;
import com.koogame.model.Player;
import com.koogame.start.launcher;

import io.netty.channel.Channel; 
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
  
public class NettyServerHandler extends SimpleChannelInboundHandler<Message>	{ 
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);  
	
	//总控制器
	private MainController mainController;
	//玩家数据
	private Player player;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception { 
		if (msg == null) {   
            return;  
        }  
		mainController.DispatchMessage(msg,player); 
		
	} 
	 
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		player = (Player)launcher.springContext.getBean("player");
		player.setChannel(ctx.channel());
		mainController = (MainController)launcher.springContext.getBean("mainController");
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
