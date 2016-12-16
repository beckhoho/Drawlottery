package com.hudongwx.drawlottery.mobile.shiro;

import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * 用户登录验证器
 *
 */
@Component
public class AuthorUserRealm  extends AuthorizingRealm{

    @Autowired
    private IUserService userService;

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
        if(user == null)
            throw new UnknownAccountException("用户名或密码错误");
        //非法账户会被锁定
        if(user.isLocked()){
            throw new LockedAccountException("账户被锁定");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getName(),
                user.getPasswd(),
                Util.bytes(user.getSalt()),
                user.getName());

//        SecurityUtils.getSubject().getPrincipal().
//        SecurityUtils.getSecurityManager()

        return info;
    }

    @PostConstruct
    protected void initMatcher(){
        //设置匹配器
        setCredentialsMatcher(new AuthorRetryLimitCredentialsMatcher());
    }

}
