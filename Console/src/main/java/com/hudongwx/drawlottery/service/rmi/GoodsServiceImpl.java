package com.hudongwx.drawlottery.service.rmi;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 14:02
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @PostConstruct
    public void initMethod(){
        System.out.println("我是初始化方法时调用的");
    }
    @Override
    public String test() {
        String result = "我是远程方法";
        System.out.println(result);
        return result;
    }
}
