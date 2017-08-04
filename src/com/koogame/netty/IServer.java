 
package com.koogame.netty;
 
public interface IServer {

    /**
     * 初始化server配置
     * @return  boolean
     */
    boolean IntiServer();
    /**
     * run netty server
     */
    void run()throws Exception;

    /**
     * run epoll
     * @throws Exception
     */
    public void runNativeEpoll() throws Exception;
}
