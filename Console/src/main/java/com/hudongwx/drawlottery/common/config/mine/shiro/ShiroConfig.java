package com.hudongwx.drawlottery.common.config.mine.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro几个核心的类，第一就是ShiroFilterFactory（实现类是ShiroFilterFactoryBean）,第二就是SecurityManager（安全管理器），这里主要是注入这两个类 .
 * <p>
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，
 * 所以我们需要定义一系列关于URL的规则和访问权限。
 * <p>
 * Date: 2017/1/5 0005
 * Time: 19:03
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class ShiroConfig {
    @Value("spring.shiro.loginUrl")
    private String loginUrl;
    @Value("spring.shiro.successUrl")
    private String successUrl;
    @Value("spring.shiro.unauthorizedUrl")
    private String unauthorizedUrl;
    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * -------------------------------------------
     * 目的1：注入ShiroFilterFactory（Bean实现类）
     * -------------------------------------------
     * <p>
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，
     * 因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager（依赖关系）
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean createShiroFilterFactoryBean(SecurityManager securityManager) {
        logger.info("-------------- createShiroFilterFactoryBean --------------");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager（安全管理器）
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        // ---------------------------
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // ---------------------------

        //以上为作者注释，个人感觉应该是使用LinkedHashMap的原因。
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        /*
            anon 	org.apache.shiro.web.filter.authc.AnonymousFilter 	匿名过滤器
            authc 	org.apache.shiro.web.filter.authc.FormAuthenticationFilter 	如果继续操作，需要做对应的表单验证否则不能通过
            authcBasic 	org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter 	基本http验证过滤，如果不通过，跳转屋登录页面
            logout 	org.apache.shiro.web.filter.authc.LogoutFilter 	登录退出过滤器
            noSessionCreation 	org.apache.shiro.web.filter.session.NoSessionCreationFilter 	没有session创建过滤器
            perms 	org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter 	权限过滤器
            port 	org.apache.shiro.web.filter.authz.PortFilter 	端口过滤器，可以设置是否是指定端口如果不是跳转到登录页面
            rest 	org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter 	http方法过滤器，可以指定如post不能进行访问等
            roles 	org.apache.shiro.web.filter.authz.RolesAuthorizationFilter 	角色过滤器，判断当前用户是否指定角色
            ssl 	org.apache.shiro.web.filter.authz.SslFilter 	请求需要通过ssl，如果不是跳转回登录页
            user 	org.apache.shiro.web.filter.authc.UserFilter 	如果访问一个已知用户，比如记住
        */
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl(loginUrl);//登录地址
        shiroFilterFactoryBean.setSuccessUrl(successUrl);//登录成功地址
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);//没有登录地址

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * -------------------------------------------
     * 目的2：注入SecurityManager（安全管理器）
     * -------------------------------------------
     *
     * @return
     */
    @Bean(name = "securityManager")
    @Profile("dev")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        return securityManager;
    }


    @Bean(name = "securityManager")
    @Profile("test")
    public SecurityManager securityManagerTest() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //管理认证器
        //securityManager.setRealm(getRealm());
        return securityManager;
    }

}
