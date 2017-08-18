 
package com.koogame.start;
  
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.koogame.baseConfig.Config; 
import com.koogame.model.Player;
import com.koogame.netty.ServerServiceImpl;

import javax.annotation.Resource;

/**
 * @author :陈磊 <br/>
 *         Date: 13-3-15
 *         Time: 下午11:31
 *         connectMethod:13638363871@163.com<br/>
 *         服务器启动入口，初始化服务器所需要的配置，负责调用需要的启动的对象。
 *         详细信息请看readme.properties
 *         注意：用spring管理bean，那么bean必须由spring容器产生，否则会注入bean失败
 */ 
@Service("gameServer")
public class launcher {

    private static final Logger LOG = LoggerFactory.getLogger(launcher.class);
    
    public static BeanFactory springContext;
    
    public static Map<Integer,Player> playerMap = new ConcurrentHashMap<Integer,Player>();


    @Resource
    private ServerServiceImpl serverService;

    public static void main(String[] args) {
        try {
  
            System.setProperty("java.net.preferIPv4Stack", "true"); //Disable IPv6 in JVM
            /**初始化spring容器*/
            BeanFactory springContext = new FileSystemXmlApplicationContext(Config.DEFAULT_VALUE.FILE_PATH.SPRING_CONFIG_PATH);
            launcher gameServer = (launcher) springContext.getBean("gameServer");
            /**set  spring context**/
            launcher.springContext = springContext;

            /**初始化配置文件，启动通信层*/
            gameServer.serverService.IntiServer();

            gameServer.serverService.run();

        } catch (Exception e) {
            LOG.error("server start error", e);
        }

    }


}
