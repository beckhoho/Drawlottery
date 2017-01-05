package com.hudongwx.drawlottery.common.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 启动监听类.
 * Date: 2017/1/5 0005
 * Time: 14:22
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class ApplicationStartListener implements ApplicationListener<ApplicationStartedEvent> {
    private Logger logger = LoggerFactory.getLogger(ApplicationStartListener.class);

    /**
     * 监听服务启动，设置常用参数、配置参数.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("spring boot 启动监听");
        //CommonConstants.load();
        //ConfigConstants.load();
    }
}
