/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.koogame.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author :陈磊 <br/>
 *         Project:CreazyGameServer1.3
 *         Date: 13-3-19
 *         Time: 下午8:47
 *         connectMethod:13638363871@163.com<br/>
 */
public class PriorityThreadFactory implements ThreadFactory {

    private int _prio;
    private String _name;
    private AtomicInteger _threadNumber = new AtomicInteger(1);
    private ThreadGroup _group;

    /**
     *
     * @param name 线程池名
     * @param priority   线程池优先级
     */
    public PriorityThreadFactory(String name, int priority){
        _prio = priority;
        _name = name;
        _group = new ThreadGroup(_name);
    }

    @Override
    public Thread newThread(Runnable r){
        Thread t = new Thread(_group, r);
        t.setName(_name + "-"+"#-" + _threadNumber.getAndIncrement());
        t.setPriority(_prio);
        return t;
    }

    public ThreadGroup getGroup(){
        return _group;
    }
}
