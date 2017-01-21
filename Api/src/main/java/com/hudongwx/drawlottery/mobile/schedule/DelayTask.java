package com.hudongwx.drawlottery.mobile.schedule;

import com.hudongwx.drawlottery.mobile.utils.SpringUtils;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/22 0022 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/22 0022　<br/>
 * <p>
 * 延迟任务
 * <p>
 * @email 294786949@qq.com
 */
public abstract class DelayTask implements Runnable {

    /**
     * 执行定时任务
     * @param task
     * @param seconds
     */
    public static void execute(DelayTask task,int seconds){
        SpringUtils.getBean(ScheduledThreadPoolExecutor.class)
                .schedule(task,seconds, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        try{
            this.todo();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 要做的任务
     */
    public abstract void todo();

}
