package com.hudongwx.drawlottery.common.config.spring;

import com.hudongwx.drawlottery.common.constants.CommonConstants;
import com.hudongwx.drawlottery.common.constants.ConfigConstants;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * 启动监听类.
 * Date: 2017/1/5 0005
 * Time: 14:22
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class ApplicationStartListener implements ApplicationListener<ContextStartedEvent> {
    /**
     * 监听服务启动，设置常用参数、配置参数.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        CommonConstants.load();
        ConfigConstants.load();
    }
}
