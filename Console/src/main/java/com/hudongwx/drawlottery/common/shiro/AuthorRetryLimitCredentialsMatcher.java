package com.hudongwx.drawlottery.common.shiro;

import com.hudongwx.drawlottery.common.utils.PasswordUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 *
 * DATE:2016-12-2016/12/14 0014-22:04
 * Author: origin
 * DESC:用户认证,3次登陆失败就出现图片验证码
 *
 * */
public class AuthorRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    public AuthorRetryLimitCredentialsMatcher() {
        super();
        setHashIterations(PasswordUtils.DEFAULT_HASH_ITERATIONS);
        setHashAlgorithmName(PasswordUtils.DEFAULT_ALGORITHM_NAME);
        setStoredCredentialsHexEncoded(false);//使用base64
    }

}
