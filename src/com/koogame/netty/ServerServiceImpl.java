/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.koogame.netty;
 
import com.koogame.baseConfig.Config;
import com.koogame.util.PriorityThreadFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

 
@Service
public class ServerServiceImpl implements IServer {

    private static final Logger LOGGER= LoggerFactory.getLogger(ServerServiceImpl.class);

    /**
     * 初始化server,游戏配置
     * @return boolean
     */
    @Override
    public boolean IntiServer() {
        boolean status=true;
        try {
            Config.IntiConfig();
            return   status;
        } catch (Exception e) {
            status=false;
            LOGGER.error("加载服务器配置文件失败",e);
           return   status;
        }
    }
    /**
     *  run netty server
     *  NioEventLoopGroup used for NIO Selector based Channels
     *  two NioEventLoopGroup(AioEventLoopGroup) will be used. The first is
     *  used to handle the accept of new connections and the second will serve the IO of them.
     *  IoEventLoopGroupThread +1;
     *  DefaultEventExecutorGroup 处理业务业务逻辑
     *
     *  bossExecutor:负责监听，链接SocketChannel；
     *  workerExecutor：负责注册SocketChannel，并监听channel的读/写事件；
     *  executionLogicHandlerThread：负责将数据分发到相应的逻辑层进行数据处理；
     *  AIO 每一个异步的channel都会属于一个group，同一个group里的对象就可以共享一个线程池
     *  option() is for the NioServerSocketChannel that accepts incoming connections
     *  childOption() is for the Channels accepted by the parent ServerChannel,
     *  which is NioServerSocketChannel in this case.
     *
     *  一个 ServerBootstrap 启动后，会创建一个 parent channel，
     *  这个 parent channel 用于接受 connections，
     *  每个 connection 会被分配到一个 child channel 上，用于后续的处理。
     */
    @Override
    public void run() throws Exception {
        NioEventLoopGroup EventLoopGroupLister=new NioEventLoopGroup(0x1,new PriorityThreadFactory("@+main_reactor线程池+@",Thread.NORM_PRIORITY));
        NioEventLoopGroup IOEventLoopGroup=new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()+1,new PriorityThreadFactory("@+sub_reactor线程池+@",Thread.NORM_PRIORITY));
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(EventLoopGroupLister,IOEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childOption(ChannelOption.SO_REUSEADDR,true)     //重用地址
                    .childOption(ChannelOption.ALLOCATOR,new PooledByteBufAllocator(false))// heap buf 's better
                    .childOption(ChannelOption.SO_RCVBUF, 1048576)
                    .childOption(ChannelOption.SO_SNDBUF,1048576)
                    .childHandler(new ServerChannelInitializer());//used to serve the request for the {@link Channel}'s
            // Bind and start to accept incoming connections.
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(Config.DEFAULT_VALUE.SERVER_VALUE.gameserverPort)).sync();
            LOGGER.info("server服务开始->监听端口:"+ Config.DEFAULT_VALUE.SERVER_VALUE.gameserverPort);
            // Wait until the server socket is closed.
            // In this server, this does not happen, but you can do that to gracefully
            // shut down your server.
            channelFuture.channel().closeFuture().sync();
        }finally {
            // Shut down all event loops to terminate all threads.
            EventLoopGroupLister.shutdownGracefully();
            IOEventLoopGroup.shutdownGracefully();
        }
    }

    /**
     * 基于jni ，native Epoll
     #NioEventLoopGroup → EpollEventLoopGroup   ----实现了 EventLoopGroup 接口
     #NioEventLoop → EpollEventLoop
     #NioServerSocketChannel → EpollServerSocketChannel
     #NioSocketChannel → EpollSocketChannel        ---- 实现了SocketChannel接口

     ## RHEL/CentOS/Fedora:
     #sudo yum install autoconf automake libtool glibc-devel.i686 glibc-devel libgcc.i686 make
     ## Debian/Ubuntu:
     #sudo apt-get install autoconf automake libtool make gcc-multilib
     * @throws Exception
     */
    @Override
    public void runNativeEpoll() throws Exception {
        EpollEventLoopGroup BossEventLoopGroup=new EpollEventLoopGroup(0x1,new PriorityThreadFactory("@+监听连接线程",Thread.NORM_PRIORITY)); //mainReactor    1个线程
        EpollEventLoopGroup WorkerEventLoopGroup=new EpollEventLoopGroup(Runtime.getRuntime().availableProcessors()+ 0x1,new PriorityThreadFactory("@+I/O线程",Thread.NORM_PRIORITY));   //subReactor       线程数量等价于cpu个数+1
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(BossEventLoopGroup,WorkerEventLoopGroup)
                    .channel(EpollServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childOption(ChannelOption.SO_REUSEADDR,true)     //重用地址
                    .childOption(ChannelOption.ALLOCATOR,new PooledByteBufAllocator(false))// heap buf 's better
                    .childOption(ChannelOption.SO_RCVBUF, 1048576)
                    .childOption(ChannelOption.SO_SNDBUF,1048576)
                    .childHandler(new ServerChannelInitializer());//used to serve the request for the {@link Channel}'s
            // Bind and start to accept incoming connections.
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(Config.DEFAULT_VALUE.SERVER_VALUE.gameserverPort)).sync();
            LOGGER.info("server监听端口:"+ Config.DEFAULT_VALUE.SERVER_VALUE.gameserverPort);
            // Wait until the server socket is closed.
            // In this server, this does not happen, but you can do that to gracefully
            // shut down your server.
            channelFuture.channel().closeFuture().sync();
        }finally {
            // Shut down all event loops to terminate all threads.
            BossEventLoopGroup.shutdownGracefully();
            WorkerEventLoopGroup.shutdownGracefully();
        }
    }

}
