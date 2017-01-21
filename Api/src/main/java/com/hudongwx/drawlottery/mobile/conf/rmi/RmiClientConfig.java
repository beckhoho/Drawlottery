package com.hudongwx.drawlottery.mobile.conf.rmi;

import com.hudongwx.drawlottery.mobile.service.rmi.GoodsService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 14:19
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class RmiClientConfig {

    /**
     * //TODO  客户端RMI配置
     * @return 生成bean
     */
    @Bean(name = "goodsService")
    public RmiProxyFactoryBean initRmiProxyFactoryBean() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://192.168.6.80:1099/rmiService");
        factoryBean.setServiceInterface(GoodsService.class);
        return factoryBean;
    }

}
