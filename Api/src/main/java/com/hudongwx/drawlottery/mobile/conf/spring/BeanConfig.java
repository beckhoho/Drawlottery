package com.hudongwx.drawlottery.mobile.conf.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledThreadPoolExecutor;

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
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@Configuration
public class BeanConfig {

    /**
     * 异步任务
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    public ScheduledThreadPoolExecutor getExecutor(){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(15);
        executor.setRemoveOnCancelPolicy(true);
        return executor;
    }


}
