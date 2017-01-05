package com.hudongwx.drawlottery.common.shiro;

import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.user.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 用户登录验证器
 */
public class AuthorUserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(AuthorUserRealm.class);
    @Resource
    private IUserService userService;

    /**
     * @param cacheManager       缓存对象
     * @param matcher            匹配器
     * @param authorZationName   权限信息
     * @param authenTicationName 认证信息
     */
    public AuthorUserRealm(CacheManager cacheManager, CredentialsMatcher matcher, String authorZationName, String authenTicationName) {
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
     * 权限认证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    /**
     * 登录认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("##################执行Shiro登录认证##################");
        //获取用户信息
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        String username = passwordToken.getUsername();
        String password = new String(passwordToken.getPassword());
        //查询用户
        User user = userService.queryUserByPhoneNum(username);
        if (user == null) throw new UnknownAccountException("用户名或密码错误");
        //非法账户会被锁定
        if (user.isLocked()) throw new LockedAccountException("账户被锁定");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                Util.bytes(user.getCredentialsSalt()),
                user.getPhoneNumber());

        return info;
    }

}
