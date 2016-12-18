package com.hudongwx.drawlottery.mobile;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.DeliveryAddressMapper;
import com.hudongwx.drawlottery.mobile.mappers.HelloMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//用于dao测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class})
@Transactional //回滚数据
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
		user.setName("lisi20");
		user.setId("30");
		user.setPasswd("123456");
		int insert = mapper.insert(user);
		Assert.assertEquals(1,insert);
	}

}
