package com.hudongwx.drawlottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 一个@SpringbootApplication
 * 相当于@Configuration,@EnableAutoConfiguration和 @ComponentScan
 * 并具有他们的默认属性值。
 *
 * @apiNote 入口main必须在路径顶层，因为项目启动会逐层扫描下面的包
 * 如果不处于顶层，会导致注解扫描不到
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
