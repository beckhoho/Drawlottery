package com.hudongwx.drawlottery.mobile.conf.shiro;

import com.hudongwx.drawlottery.mobile.shiro.AuthorUserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro权限框架
 */
@Configuration
public class ShiroConfig {

    @Bean
    public AuthorUserRealm getRealm(){
        AuthorUserRealm realm = new AuthorUserRealm();
        //设置密码匹配器
        //realm.setCredentialsMatcher();
        return realm;
    }

    //配置过滤器
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //管理安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        shiroFilterFactoryBean.setLoginUrl("/auth/login");//登录地址
        shiroFilterFactoryBean.setSuccessUrl("/auth/loginok");//登录成功地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/nologin");//没有登录地址


        //自定义过滤器
        Map<String,Filter> filters = new LinkedHashMap<String,Filter>();
        shiroFilterFactoryBean.setFilters(filters);

        //设置自带过滤器,配置url=过滤器关系
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

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

        filterChainDefinitionMap.put("/**","anon");

        /*filterChainDefinitionMap.put("/static*//**","anon"); //anon 不需要登录就可以访问
        filterChainDefinitionMap.put("*//**//*captcha","anon");
        filterChainDefinitionMap.put("*//**//*captcha","anon");
        filterChainDefinitionMap.put("/auth/login", "authc");//登录验证
        filterChainDefinitionMap.put("/auth/logout", "logout");//
        filterChainDefinitionMap.put("/user*//**", "authc");//
        filterChainDefinitionMap.put("/pay*//**", "authc");//*/


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        return shiroFilterFactoryBean;
    }

    /*
      默认安全管理器
    * 这个类组合了登陆，登出，权限，session的处理
    * */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //管理认证器
        securityManager.setRealm(getRealm());

        //记住密码管理器
//        securityManager.setRememberMeManager();
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

}
