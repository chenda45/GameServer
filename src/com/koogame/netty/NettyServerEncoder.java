package com.koogame.netty;

 
import com.koogame.model.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 参考ProtobufVarint32LengthFieldPrepender 和 ProtobufEncoder
 */
@Sharable
public class NettyServerEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out)
			throws Exception { 
		
		if(null == msg){
            throw new Exception("msg is null");
        } 
        out.writeInt(msg.getTypeId()); 	//类型ID
        out.writeInt(msg.getMsgId()); 	//消息ID
        out.writeInt(msg.getLength()); 	//消息长度
        out.writeBytes(msg.getBody());  //消息内容  
	} 
}