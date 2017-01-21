package com.hudongwx.drawlottery.mobile;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;


//用于dao测试
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class})
@Transactional //回滚数据
//@ActiveProfiles("dev")
@ActiveProfiles("dev")
public class TestBaseMapper extends AbstractTestNGSpringContextTests {

}
