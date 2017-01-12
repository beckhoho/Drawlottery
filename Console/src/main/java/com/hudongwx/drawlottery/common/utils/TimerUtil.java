package com.hudongwx.drawlottery.common.utils;

import java.util.*;

/**
 * 定时任务工具类.
 * Date: 2017/1/12 0012
 * Time: 15:10
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class TimerUtil {
    private static Map<String, Timer> map = new HashMap<>();

    /**
     * 注册执行器
     *
     * @param job      执行器
     * @param interval 下次执行时间
     * @param key      键，可以用来提前停掉定时器
     */
    public static void registry(Job job, Long interval, String key) {
        if (interval <= 0L)
            interval = 0L;
        final Timer timer = new Timer();
        timer.schedule(new TimerUtil().new Pack(job, interval, key), interval);
        map.put(key, timer);
    }

    public static void registry(Job job, Long interval) {
        registry(job, interval, UUID.randomUUID().toString());
    }

    public static void cancle(String key) {
        map.get(key).cancel();
    }

    public interface Job {
        /**
         * 定时执行的任务，返回值为是否继续定时执行执行（因此可成为循环调度）
         *
         * @return 是否继续执行
         */
        public boolean execute();
    }

    public class Pack extends TimerTask {
        private Job job;
        private Long interval;
        private String key;

        public Pack(Job job, long interval, String key) {
            this.job = job;
            this.interval = interval;
            this.key = key;
        }

        /**
         * The action to be performed by this timer task.
         */
        @Override
        public void run() {
            if (job.execute()) {
                registry(job, interval, key);
            } else {
                map.remove(key);
            }
        }
    }
}
