/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.koogame.netty;

 
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.koogame.baseConfig.Config;

/**
 * @author : 陈磊 <br/>
 *         Date: 13-3-11<br/>
 *         Time: 上午11:32<br/>
 *         connectMethod:13638363871@163.com<br/>
 *         ServerChannelInitializer which's a special handler that is purposed to help a user configure a new Channel
 *         <p/>
 *         注册连接（NioServerSocketChannel   accepts incoming TCP/IP connections）上来的    channel
 *         SocketChannel  ---- A TCP/IP socket Channel  equel  to
 *         help a user configure a new Channel.
 *         this implementation must be safe to be re-used.
 *         <p/>
 *         <p/>
 *         线程池（接收I/O操作线程数据）处理业务逻辑数据两种方案：
 *         <p/>
 *         1、线程池直接处理数据；
 *         2、单线程分发--->>>线程池处理；
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {


    /**
     * I/O线程将递交给此线程池处理数据   线程池大小为cpu核心数  ,逻辑处理线程和I/O线程分开
     * 对应一个任务是一个msg，当任务被逻辑线程处理不过来的时候将会被排队
     * 执行非I/O逻辑操作
     */
    private static final EventExecutorGroup LOGIC_EVENT_EXECUTORS = new
            DefaultEventExecutorGroup(Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory("#+逻辑处理线程+#", Thread.NORM_PRIORITY));


    public ServerChannelInitializer() {
    }


    /**
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {


        /**
         *   config  server Config
         */
        ch.config().setAllowHalfClosure(true);//处理远程主机强制关闭连接


        ChannelPipeline pipeline = ch.pipeline();

        /**
         * inbound order:          decoder-> idleStateHandler -> handler
         * outbound order:         encoder->  idleStateHandler
         */
        /**
         * 当前解码器中 默认读取包长度为 2 字节  ，根据 实际情况 具体设置。
         * 推荐使用4字节作为包长度
         * 然后修改{@link ServerDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)}
         */

        /**解码器 inbound,默认网络字节序为大端*/
        pipeline.addLast("decoder", new NettyServerDecoder());

//        /**检查链接超时处理 inbound  心跳处理*/
//        pipeline.addLast("idleStateHandler",
//                new IdleStateHandler(Config.DEFAULT_VALUE.SERVER_VALUE.readTimeOut, Config.DEFAULT_VALUE.SERVER_VALUE.writeTimeOut, 0));

        /**逻辑处理 将channel交给相应的线程池处理相应的数据  I/O线程池---->>>>逻辑处理线程池
         *
         * LOGIC_EVENT_EXECUTORS 数目在实际项目中会有不同 ，这里推荐 使用16个线程
         *
         * */
        pipeline.addLast(LOGIC_EVENT_EXECUTORS, "handler", new NettyServerHandler());

        /**编码器 outbound**/
        pipeline.addLast("encoder", new NettyServerEncoder());
    }

    /**
     * 线程池工厂
     */
    static class PriorityThreadFactory implements ThreadFactory {

        private int _prio;
        private String _name;
        private AtomicInteger _threadNumber = new AtomicInteger(1);
        private ThreadGroup _group;

        public String get_name() {
            return _name;
        }

        /**
         * @param name     线程池名
         * @param priority 线程池优先级
         */
        public PriorityThreadFactory(String name, int priority) {
            _prio = priority;
            _name = name;
            _group = new ThreadGroup(_name);
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(_group, r);
            t.setName(_name + "-" + "#-" + _threadNumber.getAndIncrement());
            t.setPriority(_prio);
            return t;
        }

        public ThreadGroup getGroup() {
            return _group;
        }
    }
}
