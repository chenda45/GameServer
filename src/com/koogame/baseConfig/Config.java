/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.koogame.baseConfig;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * @author 石头哥哥
 *         Date: 13-11-27</br>
 *         Time: 上午11:20</br>
 *         Package: com.dc.gameserver.baseConfig</br>
 *         注解：游戏服务器配置文件加载
 */
public class Config {


    public static class  DEFAULT_VALUE{
        /**
         * 文件配置路径
         */
        public static class FILE_PATH{
            public static  String SERVER="res/gameConfig/server.properties";
            public static  String GAME="res/gameConfig/gameParameter.properties";
            public static  String EHCACHE="res/gameConfig/ehcache.xml";
            public static  String LOG4J="res/gameConfig/log4j.properties";
            public static  String SPRING_CONFIG_PATH="res/springConfig/spring-context.xml";
 
            public static String GAME_XML_DATA_LEVEL="res/xml/level.xml";    //关卡文件 

        }

        /**
         * 游戏基础配置
         */
        public static class GAME_VALUE{

            /****玩家使用神识造成对手眩晕回合次数*/
            public static int  DizzinessTimes;
            /****每个回合出牌超时时间*/
            public static long roundOutCardIntervalTimeOut;
            /**每个回合结束后间隔时间*/
            public static long   roundInterval;


            /**产生牌类型的概率  神识 -> 防御 -> 攻击 -> 技能   1,2,3,4 */
            public static float soualProbability ;
            public static float defenceProbability;
            public static float attackProbability;
            public static float magicProbability;


            /**游戏服务器初始化  cardBoxNum个数，在线人数==   cardBoxNum个数*2**/
            public static int cardBoxNum ;

            /**对象池大小***/
            public static int cardNum;

            /**使用点数规则*/
            public static boolean rulePoint ;
            /***pageSize***/
            public static int PrograssPageSize ;

            public static  int MAX_ROUND;

           /**开启物品日志记录（数据库）***/
            public static boolean useItemLog;
            /**防牌固定的恢复法力盾值**/
            public static int defenceRecoverySheild;

            /**伤害<=0,固定伤害***/
            public static int hurt;

            /**装备秘籍属性浮动随机范围*****/
            public static double equipAndBooksAttrRandomRange;
            /**品阶差系数*****/
            public static double  PinJieModulus ;
            /**品质差系数*****/
            public static double PinZhiModulus ;


            /**
             * 1、2张神牌是眩晕；3、4张神牌是以伤 换伤；5、6张神牌是秒杀。
               使用神牌配置参数
             */
            public static int    minDizziness ;
            public static int    maxDizziness ;
            public static int    minHurtSoul ;
            public static int    maxHurtSoul ;
            public static int    minSpike ;
            public static int    maxSpike ;

            // #神牌眩晕要求的神牌张数
            public static int  dizinnessCardNum ;
            //#神牌以伤换伤要求的神牌张数
            public static int InotherInjuryToInjuryCardNum ;
           // #神牌以伤换伤的神识值效果
           public static int  InotherInjuryToInjurySoulValue ;
           // #神牌以伤换伤的伤害百分比
           public static double  InotherInjuryToInjuryHurtPercent ;
            //#神牌以伤换伤的反伤百分比
           public static double  InotherInjuryToInjuryReservedHurtPercent ;
           // #神牌秒杀要求的神牌张数
           public static int  SpikeCardNum ;
            //#神牌秒杀的神识值效果
           public static int  SpikeValue  ;

           //神识回复间隔时间 10min   单位 毫秒
            public static long soulRecoveryInitial ;
             //每多一本功法书增加的灵气存储百分比
            public static double GongPercent;
            //功法转换容器被充满间隔时间
            public static long spriteFullInitial;

            //#领取灵气间隔时间
            public static long  getSpriteInitial ;

            //背包对象池化  3k
            public static int ItemCellNum;

        }

        /**
         * 游戏服务器底层配置
         */
        public static class SERVER_VALUE{
            //超时处理
            public static  int readTimeOut=0;//read data time out   ..secs
            public static  int writeTimeOut=0;//write data time out    ..secs
            public static  int connect_timeout=0;//connect_timeout SEC

            public static  int gameserverPort=0;//server port
            public static  int maxConnection=0;//最大连接数

            //数据包解析参数
            public static  int maxFrameLength=0;
            public static  int lengthFieldOffset   = 0 ;
            public static  int lengthFieldLength   = 0 ;
            public static  int lengthAdjustment    = 0 ;
            public static  int initialBytesToStrip = 0;

            //流量监控
            public static long checkInterval=0;//监测流量间隔

            /**游戏核心逻辑计算使用多线程**/
            public  static  boolean useMultleThread ;

            /***游戏逻辑计算线程数量(包含db操作部分)**/
            public static  int calculateThreads = 0;

            /***回合间隔计时线程**/
            public static  int  RoundIntervalTimerNum;
            /**回合计时线程***/
            public static  int  RoundTimeOutTimerNum;
            /**buff 冷却处理线程数 */
            public static  int  buffTimerNum;
            /**销毁cancelled task */
            public static  int   purgeTaskNum;
            /**AI 线程池数**/
            public static  int AIthreadNum;
        }

    }

    /**
     * 加载游戏配置
     * @throws org.apache.commons.configuration.ConfigurationException
     */
    public static void IntiConfig() throws ConfigurationException {
        PropertiesConfiguration server_config= new PropertiesConfiguration(DEFAULT_VALUE.FILE_PATH.SERVER);
        PropertiesConfiguration game_config= new PropertiesConfiguration(DEFAULT_VALUE.FILE_PATH.GAME);
        FileChangedReloadingStrategy strategy=new FileChangedReloadingStrategy();                    //default 5000
        if (server_config.getBoolean("autoReload")) {
            strategy.setRefreshDelay(server_config.getInt("refreshDelay",5000));
            server_config.setReloadingStrategy(strategy);
            game_config.setReloadingStrategy(strategy);
        }
        /*********************************************************************************/
        //   加载游戏底层基础配置
        /*********************************************************************************/
        DEFAULT_VALUE.SERVER_VALUE.gameserverPort=server_config.getInt("gameserverPort",8888);

        DEFAULT_VALUE.SERVER_VALUE.readTimeOut=server_config.getInt("readTimeOut");
        DEFAULT_VALUE.SERVER_VALUE.writeTimeOut=server_config.getInt("writeTimeOut");
        DEFAULT_VALUE.SERVER_VALUE.connect_timeout=server_config.getInt("connect_timeout");
        DEFAULT_VALUE.SERVER_VALUE.maxConnection=server_config.getInt("maxConnection");
        DEFAULT_VALUE.SERVER_VALUE.checkInterval=server_config.getInt("checkInterval");

        //传输协议参数配置
        DEFAULT_VALUE.SERVER_VALUE.maxFrameLength=server_config.getInt("maxFrameLength",1048576);
        DEFAULT_VALUE.SERVER_VALUE.lengthFieldOffset=server_config.getInt("lengthFieldOffset",0);
        DEFAULT_VALUE.SERVER_VALUE.lengthFieldLength=server_config.getInt("lengthFieldLength",2);
        DEFAULT_VALUE.SERVER_VALUE.lengthAdjustment=server_config.getInt("lengthAdjustment",0);
        DEFAULT_VALUE.SERVER_VALUE.initialBytesToStrip=server_config.getInt("initialBytesToStrip",2);


        DEFAULT_VALUE.SERVER_VALUE.useMultleThread=server_config.getBoolean("useMultleThread",false);

        //底层服务线程池配置
        DEFAULT_VALUE.SERVER_VALUE.RoundIntervalTimerNum=server_config.getInt("RoundIntervalTimerNum");
        DEFAULT_VALUE.SERVER_VALUE.RoundTimeOutTimerNum=server_config.getInt("RoundTimeOutTimerNum");
        DEFAULT_VALUE.SERVER_VALUE.buffTimerNum=server_config.getInt("buffTimerNum");
        DEFAULT_VALUE.SERVER_VALUE.calculateThreads=server_config.getInt("calculateThreads");
        DEFAULT_VALUE.SERVER_VALUE.purgeTaskNum=server_config.getInt("purgeTaskNum");
        DEFAULT_VALUE.SERVER_VALUE.AIthreadNum=server_config.getInt("AIthreadNum");




        /*********************************************************************************/
        /*   加载游戏参数配置
        /*********************************************************************************/
        DEFAULT_VALUE.GAME_VALUE.rulePoint=game_config.getBoolean("rulePoint",false);
        DEFAULT_VALUE.GAME_VALUE.DizzinessTimes=game_config.getInt("DizzinessTimes");
        DEFAULT_VALUE.GAME_VALUE.roundOutCardIntervalTimeOut=game_config.getLong("roundOutCardIntervalTimeOut",30000);
        DEFAULT_VALUE.GAME_VALUE.roundInterval= game_config.getLong("roundInterval");

        /**产生牌类型的概率  神识 -> 防御 -> 攻击 -> 技能   1,2,3,4 */
        DEFAULT_VALUE.GAME_VALUE.soualProbability=game_config.getFloat("soualProbability");
        DEFAULT_VALUE.GAME_VALUE.defenceProbability=game_config.getFloat("defenceProbability");
        DEFAULT_VALUE.GAME_VALUE.attackProbability=game_config.getFloat("attackProbability");
        DEFAULT_VALUE.GAME_VALUE.magicProbability=game_config.getFloat("magicProbability");






        DEFAULT_VALUE.GAME_VALUE.cardBoxNum=game_config.getInt("cardBoxNum");
        DEFAULT_VALUE.GAME_VALUE.cardNum =game_config.getInt("cardNum");
        DEFAULT_VALUE.GAME_VALUE.PrograssPageSize=game_config.getInt("PrograssPageSize",5);
        DEFAULT_VALUE.GAME_VALUE.MAX_ROUND=game_config.getInt("MAX_ROUND",10) ;

        DEFAULT_VALUE.GAME_VALUE.useItemLog =game_config.getBoolean("useItemLog",true);
        DEFAULT_VALUE.GAME_VALUE.defenceRecoverySheild=game_config.getInt("defenceRecoverySheild");
        DEFAULT_VALUE.GAME_VALUE.hurt=game_config.getInt("hurt");
        DEFAULT_VALUE.GAME_VALUE.equipAndBooksAttrRandomRange=game_config.getDouble("equipAndBooksAttrRandomRange");

        DEFAULT_VALUE.GAME_VALUE.PinJieModulus=game_config.getDouble("PinJieModulus");
        DEFAULT_VALUE.GAME_VALUE.PinZhiModulus=game_config.getDouble("PinZhiModulus");



        DEFAULT_VALUE.GAME_VALUE.minDizziness=game_config.getInt("minDizziness");
        DEFAULT_VALUE.GAME_VALUE.maxDizziness=game_config.getInt("maxDizziness");
        DEFAULT_VALUE.GAME_VALUE.minHurtSoul=game_config.getInt("minHurtSoul");
        DEFAULT_VALUE.GAME_VALUE.maxHurtSoul=game_config.getInt("maxHurtSoul");
        DEFAULT_VALUE.GAME_VALUE.minSpike =game_config.getInt("minSpike");
        DEFAULT_VALUE.GAME_VALUE.maxSpike=game_config.getInt("maxSpike");


        //神牌眩晕要求的神牌张数
        //神牌以伤换伤要求的神牌张数
        //神牌以伤换伤的神识值效果
        //神牌以伤换伤的伤害百分比
        //神牌以伤换伤的反伤百分比
        //神牌秒杀要求的神牌张数
        //神牌秒杀的神识值效果
        DEFAULT_VALUE.GAME_VALUE.dizinnessCardNum=game_config.getInt("dizinnessCardNum");
        DEFAULT_VALUE.GAME_VALUE.InotherInjuryToInjuryCardNum=game_config.getInt("InotherInjuryToInjuryCardNum");
        DEFAULT_VALUE.GAME_VALUE.InotherInjuryToInjurySoulValue=game_config.getInt("InotherInjuryToInjurySoulValue");
        DEFAULT_VALUE.GAME_VALUE.InotherInjuryToInjuryHurtPercent=game_config.getDouble("InotherInjuryToInjuryHurtPercent");
        DEFAULT_VALUE.GAME_VALUE.InotherInjuryToInjuryReservedHurtPercent= game_config.getDouble("InotherInjuryToInjuryReservedHurtPercent");
        DEFAULT_VALUE.GAME_VALUE.SpikeCardNum=game_config.getInt("SpikeCardNum");
        DEFAULT_VALUE.GAME_VALUE.SpikeValue=game_config.getInt("SpikeValue");
        DEFAULT_VALUE.GAME_VALUE.soulRecoveryInitial=game_config.getLong("soulRecoveryInitial");
        DEFAULT_VALUE.GAME_VALUE.GongPercent=game_config.getDouble("GongPercent",0.02);

        //功法转换容器被充满间隔时间
        DEFAULT_VALUE.GAME_VALUE.spriteFullInitial=game_config.getLong("spriteFullInitial");
        DEFAULT_VALUE.GAME_VALUE.getSpriteInitial=game_config.getLong("getSpriteInitial");

        DEFAULT_VALUE.GAME_VALUE.ItemCellNum =game_config.getInt("ItemCellNum");
    }


}
