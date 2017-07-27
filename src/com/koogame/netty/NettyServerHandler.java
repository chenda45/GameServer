package com.koogame.netty; 

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext; 
import io.netty.channel.ChannelInboundHandlerAdapter; 
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
  
public class NettyServerHandler extends ChannelInboundHandlerAdapter{ 
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	 
    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
    	try {
            String body = (String) msg;
            System.out.println("server receive msg : " + body);
 
            String respMsg = ""; 

            ByteBuf resp = Unpooled.copiedBuffer(respMsg.getBytes());
            ctx.writeAndFlush(resp);
        }catch (Exception e){
//        	ReferenceCountUtil.release(msg);
            e.printStackTrace();
        }
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
