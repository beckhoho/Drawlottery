package com.hudongwx.drawlottery.mobile;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.conf.shiro.ShiroConfig;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.HelloMapper;
import com.hudongwx.drawlottery.mobile.web.index.HelloController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import javax.annotation.Resource;
import java.util.List;


//用于web测试
@SpringBootTest(classes = { ApiApplication.class, ShiroConfig.class,MockServletContext.class,})
@WebAppConfiguration
@Transactional
public abstract class ApiWebApplicationTests extends AbstractTestNGSpringContextTests {

	public MockMvc mvc;


	@Autowired
	WebApplicationContext context;

	@Autowired
	SecurityManager securityManager;

	@BeforeClass
	public void setUp() {
//		DefaultWebSecurityManager mock = Mockito.mock(DefaultWebSecurityManager.class,Mockito.RETURNS_DEEP_STUBS);
//		mock.setRealm(new UserDBRealm());
		ThreadContext.bind(securityManager);
//		mvc = MockMvcBuilders.webAppContextSetup(Mockito.mock(WebApplicationContext.class)).build();
		mvc = MockMvcBuilders.standaloneSetup(getController()).build();
	}


	public void executeGet(String url) throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get(url)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(getContent())
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	public void executepPost(String url) throws Exception {
		mvc.perform(
				// .accept(MediaType.APPLICATION_JSON_UTF8)
				MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(getContent())
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	public abstract  String getContent();

	public abstract Object getController();

}
