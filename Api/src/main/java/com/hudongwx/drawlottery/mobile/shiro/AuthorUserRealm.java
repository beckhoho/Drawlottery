package com.hudongwx.drawlottery.mobile.shiro;

import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户登录验证器
 */
public class AuthorUserRealm  extends AuthorizingRealm{

    @Autowired
    private IUserService userService;

    /**
     *
     * @param cacheManager 缓存对象
     * @param matcher 匹配器
     * @param authorZationName 权限信息
     * @param authenTicationName 认证信息
     */
    public AuthorUserRealm(CacheManager cacheManager, CredentialsMatcher matcher,String authorZationName,String authenTicationName) {
        super(cacheManager, matcher);
        setAuthenticationCachingEnabled(true);
        setAuthorizationCachingEnabled(true);
//        setCachingEnabled(true);
        setAuthorizationCacheName(authorZationName);
        setAuthenticationCacheName(authenTicationName);
    }

    @Override
    protected void doClearCache(PrincipalCollection principals) {
        super.doClearCache(principals);
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 权限实现
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 用户信息实现
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户信息
        UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
        String username = passwordToken.getUsername();
        String password = new String(passwordToken.getPassword());

        //查询用户
        User user = userService.login(username, password);

        if(user == null) throw new UnknownAccountException("用户名或密码错误");

        //非法账户会被锁定
        if(user.isLocked()) throw new LockedAccountException("账户被锁定");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                user.getPasswd(),
                Util.bytes(user.getCredentialsSalt()),
                user.getName());

        return info;
    }

}
