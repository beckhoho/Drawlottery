package com.hudongwx.drawlottery.common.config.spring;

import net.sf.ehcache.constructs.web.ShutdownListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/17 0017 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/17 0017　<br/>
 * <p>
 *     监听spring boot的关闭事件,保证ehcache缓存框架的正常关闭
 * <p>
 * @email 294786949@qq.com
 */
public class ApplicationStopListener implements ApplicationListener<ContextStoppedEvent> {
    private Logger logger = LoggerFactory.getLogger(ApplicationStopListener.class);
     /* if (event instanceof ApplicationEnvironmentPreparedEvent) { // 初始化环境变量 }
        else if (event instanceof ApplicationPreparedEvent) { // 初始化完成 }
        else if (event instanceof ContextRefreshedEvent) { // 应用刷新 }
        else if (event instanceof ApplicationReadyEvent) {// 应用已启动完成 }
        else if (event instanceof ContextStartedEvent) { // 应用启动，需要在代码动态添加监听器才可捕获 }
        else if (event instanceof ContextStoppedEvent) { // 应用停止 }
        else if (event instanceof ContextClosedEvent) { // 应用关闭 }
        else {}*/
    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        //为了正常关闭缓存框架
        new ShutdownListener().contextDestroyed(null);
        logger.info("spring boot 关闭监听->关闭缓存框架");
    }
}
