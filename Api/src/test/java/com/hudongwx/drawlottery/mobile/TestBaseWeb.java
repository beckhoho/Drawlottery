package com.hudongwx.drawlottery.mobile;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;

//,MockServletContext.class,
//用于web测试
@SpringBootTest(classes = {ApiApplication.class})
@WebAppConfiguration
@Transactional
@ActiveProfiles("test")
public abstract class TestBaseWeb extends AbstractTestNGSpringContextTests {

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

        //集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        //独立测试环境,模拟一个Mvc测试环境
        mvc = MockMvcBuilders.standaloneSetup(getController()).build();
    }


    public void get(String url, String content) throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(content)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    public void post(String url, String content) throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(content)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    public void post(String url, String content,MediaType contentType) throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(contentType)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(content)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    public abstract Object getController();

}
