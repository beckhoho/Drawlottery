package com.hudongwx.drawlottery.mobile.web;

import com.hudongwx.drawlottery.mobile.entitys.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/17 0017 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/17 0017　<br/>
 * <p>
 *  控制层基类
 * <p>
 * @email 294786949@qq.com
 */
public abstract class BaseController {

    /**
     * 获取当前登录的用户id
     * @return
     */
    public int getUserId(){
       return getUser().getId();
    }

    /**
     * 获取当前登录的用户信息
     * @return
     */
    public User getUser(){
        Object principal = getSubject().getPrincipal();
        User user = (User)principal;
        return user;
    }

    public Session getSession(){
       /* DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        securityManager.getRealms();*/
        return getSubject().getSession();
    }

    /**
     * 获取当前用户的的Subject对象
     * @return
     */
    public Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 是否记住密码
     * @return
     */
    public boolean isRemembered(){
        return getSubject().isRemembered();
    }

    /**
     * 用户是否已经验证通过
     * @return
     */
    public boolean isAuthenticated(){
        return getSubject().isAuthenticated();
    }

    /**
     * 用户登录
     * @param token
     * @throws AuthenticationException
     */
    public void login(AuthenticationToken token)throws AuthenticationException {
        getSubject().login(token);
    }

    /**
     * 登出
     */
    public void logout(){
//        ShutdownListener
//        DiskStoreBootstrapCacheLoaderFactory
        /*RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        AuthorUserRealm realm = (AuthorUserRealm) securityManager.getRealms().iterator().next();
        getSubject().logout();
*/
        //getSubject().runAs(new SimplePrincipalCollection());
    }


}
