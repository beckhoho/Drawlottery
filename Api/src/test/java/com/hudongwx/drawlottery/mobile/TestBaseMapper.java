package com.hudongwx.drawlottery.mobile;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.entitys.Users;
import com.hudongwx.drawlottery.mobile.mappers.UsersMapper;
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
	UsersMapper mapper;

	@Test
	public void selectUser(){
		List<Users> userses = mapper.selectAll();
		System.out.println(JSON.toJSONString(userses));
	}

	@Test
	public void insertUser(){
		Users users = new Users();
		users.setName("lisi20");
		users.setId(30);
		users.setPassword("123456");
		int insert = mapper.insert(users);
		Assert.assertEquals(1,insert);
	}

}
