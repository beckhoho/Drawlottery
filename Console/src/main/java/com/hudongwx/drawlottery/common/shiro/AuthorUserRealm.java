package com.hudongwx.drawlottery.common.shiro;

import com.hudongwx.drawlottery.pojo.Role;
import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.user.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

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
     * 权限认证，为当前登录的Subject授予角色和权限 .
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String) super.getAvailablePrincipal(principals);
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        //到数据库查是否有此对象
        final User user = userService.queryUserByPhoneNum(loginName);
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.setRoles(user.getRolesName());
            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
            List<Role> roleList = user.getRoleList();
            for (Role role : roleList) {
                info.addStringPermissions(role.getPermissionsName());
            }
            // 或者按下面这样添加
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//            simpleAuthorInfo.addRole("admin");
            //添加权限
//            simpleAuthorInfo.addStringPermission("admin:manage");
//            logger.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
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
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        logger.info("验证当前Subject时获取到token为：" + token.toString() + " token类型为：" + token.getClass().getName());
        String username = passwordToken.getUsername();
        String password = new String(passwordToken.getPassword());
        //查询用户
        User user = userService.queryUserByPhoneNum(username);
        if (user == null) throw new UnknownAccountException("用户名或密码错误");
        //非法账户会被锁定
        if (user.isLocked()) throw new LockedAccountException("账户被锁定");
        // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                Util.bytes(user.getCredentialsSalt()),
                user.getPhoneNumber());

        return info;
    }

}
