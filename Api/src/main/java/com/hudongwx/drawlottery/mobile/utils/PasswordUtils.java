package com.hudongwx.drawlottery.mobile.utils;

import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.shiro.AuthorRetryLimitCredentialsMatcher;
import com.sun.tools.classfile.Opcode;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;
import sun.applet.Main;

/**
 * DATE:2016-12-2016/12/16 0016-00:48
 * Author: origin
 * DESC:
 */
public final class PasswordUtils {

    public static final int DEFAULT_HASH_ITERATIONS = 7; //散列迭代次数
    public static final String DEFAULT_ALGORITHM_NAME = Sha512Hash.ALGORITHM_NAME; //散列算法
    //公钥随机数
    public static final SecureRandomNumberGenerator random = new SecureRandomNumberGenerator();

    /**
     * 加密用户密码
     * @param user
     */
    public static void encryptPassword(User user){
        ByteSource salt = random.nextBytes();
        user.setSalt(salt.toBase64());
        SimpleHash simpleHash = new SimpleHash(DEFAULT_ALGORITHM_NAME,
                user.getPasswd(),user.getCredentialsSalt(),DEFAULT_HASH_ITERATIONS);
        user.setPasswd(simpleHash.toBase64());
    }

}
