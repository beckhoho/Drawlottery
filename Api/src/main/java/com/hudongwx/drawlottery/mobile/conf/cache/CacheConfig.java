package com.hudongwx.drawlottery.mobile.conf.cache;

import com.hudongwx.drawlottery.mobile.conf.shiro.ShiroConfig;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/15 0015 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/15 0015　<br/>
 * <p>
 * 缓存配置
 * <p>
 * @email 294786949@qq.com
 */
//// TODO: 2017/1/15 0015 在ShiroConfig中无法注入对象,暂时配置在ShiroConfig
public class CacheConfig {
   /* @Bean
    public org.apache.shiro.cache.CacheManager getShiroCacheManager(){
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManager(getCacheManager());
        return manager;
    }

    @Bean
    public CacheManager getCacheManager(){
        return getManagerFactoryBean().getObject();
    }

    @Bean
    public EhCacheCacheManager getEhCacheCacheManager(){
        return new EhCacheCacheManager(getManagerFactoryBean().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean getManagerFactoryBean(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache-dev-config.xml"));
        factoryBean.setShared (true);
        return factoryBean;
    }*/

}
