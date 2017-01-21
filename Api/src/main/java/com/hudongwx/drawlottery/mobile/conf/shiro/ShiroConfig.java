package com.hudongwx.drawlottery.mobile.conf.shiro;

import com.hudongwx.drawlottery.mobile.shiro.AuthorRetryLimitCredentialsMatcher;
import com.hudongwx.drawlottery.mobile.shiro.AuthorUserRealm;
import com.hudongwx.drawlottery.mobile.shiro.CustomerEnterpriseCacheSessionDAO;
import com.hudongwx.drawlottery.mobile.shiro.MobileAuthenticationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.AbstractSessionManager;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.Filter;
import java.util.*;

/**
 * shiro权限框架
 */
@Configuration
public class ShiroConfig {
    /*
    * @Configuration
        //加载资源文件
      @PropertySource({"classpath:/config/properties/db.properties"})
        @Value("${jdbc.driver}")
        String driverClass;
        @Value("${jdbc.url}")
        String url;
        @Value("${jdbc.username}")
        String userName;
        @Value("${jdbc.password}")
        String passWord;
    * */

    @Bean
    @Qualifier
    public AuthorUserRealm getRealm(){
        //设置密码匹配器
        return new AuthorUserRealm(
                getShiroCacheManager(),
                getCredentialsMatcher(),
                "Login_AuthorizationCacheName",
                "Login_AuthenticationCacheName");
    }

    @Bean
    public HashedCredentialsMatcher getCredentialsMatcher(){
        return new AuthorRetryLimitCredentialsMatcher("login_limitretry_cache");
    }

    //配置过滤器
    @Bean(name = "shiroFilter")
    @Profile(value = "dev")
    public ShiroFilterFactoryBean shiroFilterFactoryBeanDev(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //管理安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //shiroFilterFactoryBean.setUnauthorizedUrl("/api/v1/pub/error/403");//没有登录跳转的地址


        //设置自带过滤器,配置url=过滤器关系
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //不需要登录就能访问
        /*filterChainDefinitionMap.put("/swagger-ui.html*//**","anon");
        filterChainDefinitionMap.put("/swagger-resources*//**","anon");
        filterChainDefinitionMap.put("/webjars*//**","anon");
        filterChainDefinitionMap.put("/v2/api-docs*//**","anon");
        filterChainDefinitionMap.put("/configuration*//**","anon");
        filterChainDefinitionMap.put("/api/v1/pub*//**","anon");*/

        filterChainDefinitionMap.put("/**","anon");

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

        //filterChainDefinitionMap.put("/auth/queryUserByPhoneNum","authc");

        /*filterChainDefinitionMap.put("/static*","anon"); //anon 不需要登录就可以访问
        filterChainDefinitionMap.put("captcha","anon");
        filterChainDefinitionMap.put("captcha","anon");
        filterChainDefinitionMap.put("/auth/queryUserByPhoneNum", "authc");//登录验证
        filterChainDefinitionMap.put("/auth/logout", "logout");//
        filterChainDefinitionMap.put("/user*", "authc");//
        filterChainDefinitionMap.put("/pay*", "authc");//*/

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    //配置过滤器
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //自定义过滤器
        //用户没有登录的过滤器
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        //自定义非法请求
        filters.put("authc",new MobileAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        //管理安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/v1/pub/error/403");//没有登录跳转的地址
        /*shiroFilterFactoryBean.setLoginUrl("/auth/queryUserByPhoneNum");//登录地址
        shiroFilterFactoryBean.setSuccessUrl("/auth/loginok");//登录成功地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/nologin");//没有登录地址*/

        //设置自带过滤器,配置url=过滤器关系
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //不需要登录就能访问
        filterChainDefinitionMap.put("/swagger-ui.html/**","anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/webjars/**","anon");
        filterChainDefinitionMap.put("/v2/api-docs/**","anon");
        filterChainDefinitionMap.put("/configuration/**","anon");
        filterChainDefinitionMap.put("/api/v1/pub/**","anon");

        filterChainDefinitionMap.put("/**","authc");

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

        //filterChainDefinitionMap.put("/auth/queryUserByPhoneNum","authc");

        /*filterChainDefinitionMap.put("/static*","anon"); //anon 不需要登录就可以访问
        filterChainDefinitionMap.put("captcha","anon");
        filterChainDefinitionMap.put("captcha","anon");
        filterChainDefinitionMap.put("/auth/queryUserByPhoneNum", "authc");//登录验证
        filterChainDefinitionMap.put("/auth/logout", "logout");//
        filterChainDefinitionMap.put("/user*", "authc");//
        filterChainDefinitionMap.put("/pay*", "authc");//*/

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /*
      默认安全管理器
    * 这个类组合了登陆，登出，权限，session的处理
    * */
    @Bean(name = "securityManager")
    @Profile("dev")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(getWebSessionManager());
        //管理认证器
        securityManager.setRealm(getRealm());
        return securityManager;
    }

    @Bean
    public CachingSessionDAO getCachingSessionDAO(){
        CustomerEnterpriseCacheSessionDAO dao = new CustomerEnterpriseCacheSessionDAO();
        dao.setActiveSessionsCacheName("ActiveSessionCache");
        dao.setCacheManager(getShiroCacheManager());
        return dao;
    }

    @Bean
    public WebSessionManager getWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(getCachingSessionDAO());
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置session失效时间:60天过期
        sessionManager.setGlobalSessionTimeout(AbstractSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT*2*24*60);
        sessionManager.setSessionValidationSchedulerEnabled(true);//定期检查失效session

        //设置cookie
        SimpleCookie token = new SimpleCookie("token");
        token.setMaxAge(86400000*60);//缓存时间60天
        token.setVersion(1);
        token.setHttpOnly(true);
        sessionManager.setSessionIdCookie(token);
        return sessionManager;
    }


    @Bean(name = "securityManager")
    @Profile("test")
    public SecurityManager securityManagerTest(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setSessionManager(getWebSessionManager());
        //管理认证器
        securityManager.setRealm(getRealm());
        return securityManager;
    }

    /*
    * shiro生命周期的管理
    * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁
    * */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 启动权限注解
     * Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * 启动权限注解
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return aasa;
    }

    @Bean
    public org.apache.shiro.cache.CacheManager getShiroCacheManager(){
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManager(getCacheManager());
        return manager;
    }

    @Bean
    public net.sf.ehcache.CacheManager getCacheManager(){
        net.sf.ehcache.CacheManager object = getManagerFactoryBean().getObject();
        return object;
    }

    @Bean
    public EhCacheCacheManager getSpringCacheManager(){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(getManagerFactoryBean().getObject());
        return ehCacheCacheManager;
    }

    @Bean
    public EhCacheManagerFactoryBean getManagerFactoryBean(){
        System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setAcceptExisting(true);
        factoryBean.setConfigLocation(new ClassPathResource("ehcache-dev-config.xml"));
        factoryBean.setShared (false);
        return factoryBean;
    }

}
