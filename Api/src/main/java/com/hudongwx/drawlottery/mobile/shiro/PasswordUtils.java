package com.hudongwx.drawlottery.mobile.shiro;

import com.hudongwx.drawlottery.mobile.entitys.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * DATE:2016-12-2016/12/16 0016-00:48
 * Author: origin
 * DESC:
 */
public final class PasswordUtils {

    public static final int DEFAULT_HASH_ITERATIONS = 5; //散列迭代次数
    public static final String DEFAULT_ALGORITHM_NAME = Sha512Hash.ALGORITHM_NAME; //散列算法
    //私盐
    //private final SimpleByteSource privateSalt = new  SimpleByteSource("294786949@qq.com");
    //公钥随机数
    private final SecureRandomNumberGenerator random = new SecureRandomNumberGenerator();

    //加密用户信息
    public void encryptPassword(User user){
        ByteSource salt = random.nextBytes();
        SimpleHash simpleHash = new SimpleHash(
                DEFAULT_ALGORITHM_NAME,
                user.getPasswd(),
                salt,
                DEFAULT_HASH_ITERATIONS);
        String password = simpleHash.toBase64();
        user.setSalt(salt.toBase64());
        user.setPasswd(password);
    }


}
