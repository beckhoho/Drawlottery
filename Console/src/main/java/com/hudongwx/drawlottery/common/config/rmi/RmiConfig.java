package com.hudongwx.drawlottery.common.config.rmi;

import com.hudongwx.drawlottery.service.rmi.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 14:04
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Configuration
public class RmiConfig {

    @Autowired
    @Qualifier("goodsService")
    private GoodsService serviceImpl;
    /**
     * 方法描述：
     * 注意事项：
     * @return
     * @Exception 异常对象
     */
    @Bean
    public RmiServiceExporter initRmiServiceExporter(){
        RmiServiceExporter exporter=new RmiServiceExporter();
        exporter.setServiceInterface(GoodsService.class);
        exporter.setServiceName("rmiService");
        exporter.setService(serviceImpl);
        exporter.setServicePort(6666);
        exporter.setRegistryPort(8989);
        return exporter;
    }
}
