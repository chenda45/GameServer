package com.koogame.netty;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 参考ProtobufVarint32LengthFieldPrepender 和 ProtobufEncoder
 */
@Sharable
public class NettyServerEncoder extends MessageToByteEncoder<MessageLite> {
    
     
    @Override
    protected void encode(
            ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) throws Exception {

    	System.out.println("#####################encode");
        byte[] body = msg.toByteArray();
        byte[] header = encodeHeader(msg, (short)body.length);
        
        out.writeBytes(header);
        out.writeBytes(body);
        
        return;
    }
    
    private byte[] encodeHeader(MessageLite msg, short bodyLength) {
        byte messageType = 0x0f;
        
//        if (msg instanceof StockTickOuterClass.StockTick) {
//            messageType = 0x00;
//        } else if (msg instanceof OptionTickOuterClass.OptionTick) {
//            messageType = 0x01;
//        }
//        
        byte[] header = new byte[4];
        header[0] = (byte) (bodyLength & 0xff);
        header[1] = (byte) ((bodyLength >> 8) & 0xff);
        header[2] = 0; // 保留字段
        header[3] = messageType;

        return header;

    }
}