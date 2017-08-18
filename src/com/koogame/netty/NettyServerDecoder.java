package com.koogame.netty;

 
import com.koogame.model.Message;
import com.koogame.start.launcher;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyServerDecoder extends LengthFieldBasedFrameDecoder { 
    
    /** 
     *  
     * @param maxFrameLength 解码时，处理每个帧数据的最大长度 
     * @param lengthFieldOffset 该帧数据中，存放该帧数据的长度的数据的起始位置 
     * @param lengthFieldLength 记录该帧数据长度的字段本身的长度 
     * @param lengthAdjustment 修改帧数据长度字段中定义的值，可以为负数 
     * @param initialBytesToStrip 解析的时候需要跳过的字节数 
     * @param failFast 为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常 
     */  
    public NettyServerDecoder() {
		super(1024*1024,8,4,0,0, false);
	}
 
	@Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		
		Message message = (Message)launcher.springContext.getBean("message");
        message.setTypeId(in.readInt()); 
        message.setMsgId(in.readInt()); 
        message.setLength(in.readInt()); 
        byte[] data = new byte[in.readableBytes()]; 
        in.readBytes(data);  
        message.setBody(data); 
    
        return message;
    } 
}