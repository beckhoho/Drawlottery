package com.hudongwx.drawlottery.mobile.shiro;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.hudongwx.drawlottery.mobile.utils.PasswordUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * DATE:2016-12-2016/12/14 0014-22:04
 * Author: origin
 * DESC:用户认证,3次登陆失败就出现图片验证码
 *
 * */
public class AuthorRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private CacheManager cache;
    //缓存的名称
    private final String cacheName;

    private int limitRetry = 10;//重试次数


    public AuthorRetryLimitCredentialsMatcher(String cacheName) {
        super();
        this.cacheName = cacheName;
        setHashIterations(PasswordUtils.DEFAULT_HASH_ITERATIONS);
        setHashAlgorithmName(PasswordUtils.DEFAULT_ALGORITHM_NAME);
        setStoredCredentialsHexEncoded(false);//使用base64
    }



    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean match = false;
        String id = (String) token.getPrincipal();
        AtomicInteger atomicInteger = getAtomicInteger(id);
        if(atomicInteger.incrementAndGet() > limitRetry){
            updateAtomicInteger(atomicInteger,id);
            throw new ExcessiveAttemptsException("登录错误次数太多,3分钟之后再登录");
        }else{
            //匹配验证
            match = super.doCredentialsMatch(token,info);
            if(match){
                //删除缓存
                removeAtomicInteger(id);
            }else{
                updateAtomicInteger(atomicInteger,id);
            }
        }
        return match;
    }


    public AtomicInteger getAtomicInteger(String id){
        Cache cacheCache = getCache();
        AtomicInteger atomicInteger = cacheCache.get(id, AtomicInteger.class);
        if(atomicInteger == null){
            return new AtomicInteger(0);
        }
        return atomicInteger;
    }



    private Cache getCache(){
        return cache.getCache(cacheName);
    }


    /**
     * 更新数据
     * @param integer
     * @param id
     * @return
     */
    public AtomicInteger updateAtomicInteger(AtomicInteger integer,String id){
        Cache cache = getCache();
        cache.put(id,integer);
        return integer;
    }

    /**
     * 删除数据
     * @param id
     */
    public void removeAtomicInteger(String id){
        Cache cache = getCache();
        cache.evict(id);
    }

}
