package com.hudongwx.drawlottery.mobile.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.ByteSource;

import static com.hudongwx.drawlottery.mobile.shiro.AuthorPasswordService.DEFAULT_ALGORITHM_NAME;
import static com.hudongwx.drawlottery.mobile.shiro.AuthorPasswordService.DEFAULT_HASH_ITERATIONS;

/**
 * DATE:2016-12-2016/12/14 0014-22:04
 * Author: origin
 * DESC:用户认证,匹配器
 */
public class AuthorRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    private AuthorPasswordService passwordService;

    public AuthorRetryLimitCredentialsMatcher() {
        this.passwordService = new AuthorPasswordService();
        setHashIterations(AuthorPasswordService.DEFAULT_HASH_ITERATIONS);
        setHashAlgorithmName(DEFAULT_ALGORITHM_NAME);
        setStoredCredentialsHexEncoded(false);
    }

}
