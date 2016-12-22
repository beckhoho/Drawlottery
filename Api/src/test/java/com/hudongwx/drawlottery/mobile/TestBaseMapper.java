package com.hudongwx.drawlottery.mobile;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//用于dao测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class})
@Transactional //回滚数据
@ActiveProfiles("test")
public class TestBaseMapper{

	@Autowired
	UserMapper mapper;

	@Test
	public void selectUser(){
		List<User> users = mapper.selectAll();
		System.out.println(JSON.toJSONString(users));
	}

	@Test
	public void insertUser(){
		User user = new User();
		user.setAccountId(10000l);
		user.setPassword("123456");
		int insert = mapper.insert(user);
		Assert.assertEquals(1,insert);
	}

}
