package com.hudongwx.drawlottery.mobile.web.index;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.ApiApplication;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.shiro.AuthorPasswordService;
import com.hudongwx.drawlottery.mobile.shiro.AuthorRetryLimitCredentialsMatcher;
import com.hudongwx.drawlottery.mobile.shiro.AuthorUserRealm;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.HashingPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.crypto.hash.format.Base64Format;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.groups.Default;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * DATE:十二月
 * Author: origin
 * DESC:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class})
public class ShiroTest{


    @Autowired
    IUserService service;

    @Test
    public void test7(){
        //HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();

        String salt = "TSSAyFblLssgUl+gSDun2A==";
        SimpleHash simpleHash = new SimpleHash(AuthorPasswordService.DEFAULT_ALGORITHM_NAME, "123456", salt,AuthorPasswordService.DEFAULT_HASH_ITERATIONS);
        String password = simpleHash.toBase64();

        AuthorRetryLimitCredentialsMatcher matcher = new AuthorRetryLimitCredentialsMatcher();

        UsernamePasswordToken token = new UsernamePasswordToken("123456","123456");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                "123456",password
                ,ByteSource.Util.bytes(salt),"12345");

        boolean b = matcher.doCredentialsMatch(token, info);

        System.out.println(b);

    }

    @Test
    public void test6(){

        String salt = "TSSAyFblLssgUl+gSDun2A==";
        //String s = new SecureRandomNumberGenerator().nextBytes().toBase64();
        String paswd = "rUuRisYeDez9EzP7JHebI/E6kMx0vrSGoFNeVM1pqYFGVQpmnImjKGuHThA/B8MJHRDJ36Cgki6TdS6/5PoykQ==";

//        SimpleHash simpleHash = new SimpleHash(Sha512Hash.ALGORITHM_NAME,"123456",s,5);
//        String s1 = simpleHash.toBase64();

        String pwd2 = "PMdAxm8I3zD1TplftZg1IsKAjjewInKnWlSdjW9slPZqYjkwGOTQcbo9Ac5EdNb9gxv8c9DNUA5JQpa5r9Xf0A==";
        String salt2 = "k/2uKZKS/HfTcBMbO98z7Q==";

        User user = new User();
        user.setPasswd("123456");
        user.setName("123456");
        AuthorPasswordService service = new AuthorPasswordService();
        service.entryUser(user);
        pwd2 = user.getPasswd();
        salt2 = user.getSalt();

        DefaultPasswordService passwordService;


        boolean b = service.passwordsMath("123456", salt2, pwd2);


        AuthorRetryLimitCredentialsMatcher matcher2 = new AuthorRetryLimitCredentialsMatcher();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Sha512Hash.ALGORITHM_NAME);
        matcher.setHashIterations(5);
        matcher.setStoredCredentialsHexEncoded(false);


        UsernamePasswordToken token = new UsernamePasswordToken("123456","123456");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                "123456",pwd2
                ,ByteSource.Util.bytes(salt2),"12345");
        //boolean b = matcher.doCredentialsMatch(token,info);
        boolean b1 = matcher2.doCredentialsMatch(token, info);
        //System.out.println(b);
        System.out.println(b1);

        /*HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Sha512Hash.ALGORITHM_NAME);
        matcher.setHashIterations(5);
        matcher.setStoredCredentialsHexEncoded(false);//base64


        UsernamePasswordToken token = new UsernamePasswordToken("123456","123456");

        User user = service.login("123456","123456");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getName(),
                user.getPasswd(),ByteSource.Util.bytes(user.getSalt()),user.getName());
        AuthorRetryLimitCredentialsMatcher matcher = new AuthorRetryLimitCredentialsMatcher();
        boolean b = matcher.doCredentialsMatch(token, info);

        System.out.println(b);*/

        //ByteSource byteSource = generator.nextBytes();
        //ByteSource aaa = ByteSource.Util.bytes(byteSource);
//        ByteSource aaa1 = ByteSource.Util.bytes(byteSource.toHex());

    }

    @Test
    public void test5(){
        User user = service.login("1234", "123456");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getName(),
                user.getPasswd(),
                ByteSource.Util.bytes(user.getSalt()),
                user.getName());

        System.out.println("-----------");

    }

    @Test
    public void test4(){
        /*"passwd":"p9dzquedAw92ztw/OP1k5FsyDeZGIVDgZ6xftE4o8l8FLZN5gB5draHooB8YuxJdF/NQssTyEj3F7JNJo9gSrQ==",
         "salt":"q3xbBRJUAhnCXCACbe3lQA==",*/

        User user = new User();
        user.setName("wangwu");
        user.setPasswd("654321");

        AuthorPasswordService service = new AuthorPasswordService();
        service.encryptUserPassword(user);
        System.out.println(JSON.toJSONString(user,true));

       /* String sql = "1UhCE1rzLr5xibJ0wsTX8DbKIzOuiwyD7lzAeFRtNpmWQYCritBgnd3uEZY7savCpA0g5ZLhlnKMx57n6AClYw==";
        UsernamePasswordToken token = new UsernamePasswordToken("123456","123456");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                "123456",
                sql,
                ByteSource.Util.bytes("123"),
                "123456789");

        AuthorRetryLimitCredentialsMatcher matcher = new AuthorRetryLimitCredentialsMatcher();
        boolean b = matcher.doCredentialsMatch(token, info);

        System.out.println(b);*/

        //6Xix/8NIfuj+cQUgMU+d0rJ5/cNYgFfBzi409V8tA1R5Vz0hKiLeQnuVaLEAyS8r2Ro9Hcj/JRPLYSvd53GbdA==
        /*User user = new User();
        user.setPasswd("123456");
//        user.setSalt("123");
        AuthorPasswordService service = new AuthorPasswordService();
        service.encryptUserPassword(user);*/

        //1UhCE1rzLr5xibJ0wsTX8DbKIzOuiwyD7lzAeFRtNpmWQYCritBgnd3uEZY7savCpA0g5ZLhlnKMx57n6AClYw==
        //MTIz
/*
        System.out.println(user.getPasswd());
        System.out.println(user.getSalt());*/
    }

    @Test
    public void test3(){

       /* DefaultHashService service = new DefaultHashService();
        service.setPrivateSalt(ByteSource.Util.bytes("123456"));
        service.setGeneratePublicSalt(true);
        Hash hash = service.computeHash(new SimpleHashRequest("", null, null, 5));
        String format = new Base64Format().format(hash);
*/

        String sql = "3d7785912ab7f6c452557b126b12d6514583b78e48f2dc3d9a0718f0613707faf757294e928d2aef6eea3c2872fae89ba9c16e0b2eb0ebbaee690dc0c871de0e";
        UsernamePasswordToken token = new UsernamePasswordToken("wangwu","123456");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                "wangwu",
                sql,
                ByteSource.Util.bytes("123456"),
                "123456789");

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("sha-512");
        matcher.setStoredCredentialsHexEncoded(false);
        boolean b = matcher.doCredentialsMatch(token, info);
        System.out.println(b);

        /*PasswordMatcher matcher = new PasswordMatcher();
        matcher.setPasswordService(new AuthorPasswordService());*/
//        HashedCredentialsMatcher
//        String s = matcher.getPasswordService().encryptPassword("123456");
//        System.out.println(s);
        //HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-512");

       /* System.out.println(ByteSource.Util.bytes("123456"));
        Sha512Hash hash = new Sha512Hash("123456",ByteSource.Util.bytes("123456"),5);

        String enty = "3d7785912ab7f6c452557b126b12d6514583b78e48f2dc3d9a0718f0613707faf757294e928d2aef6eea3c2872fae89ba9c16e0b2eb0ebbaee690dc0c871de0e";
        enty = hash.toBase64();

        UsernamePasswordToken token = new UsernamePasswordToken("wangwu","123456");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                "wangwu",
                enty,
                ByteSource.Util.bytes("123456"),
                "123456789");
        System.out.println(enty);
        //MTIzNDU2
        boolean b = matcher.doCredentialsMatch(token, info);

        System.out.println(b);*/
    }

    @Test
    public void test2(){
//        SimpleByteSource
//        SimpleHashRequest
        DefaultHashService hashService = new DefaultHashService();
        System.out.println(hashService.getRandomNumberGenerator().nextBytes()
        );

        String paswd = "123456";
        String entryPswd= "TSXTJF4iseTkPx6kTqPr5Imr48JjVgEX6h+FWNTa0mNtnC90ObaHhSwcJ/ionSrP7mQc2ShdiOXHi59uHsjdmQ==";

        AuthorPasswordService passwordService = new AuthorPasswordService();
        String encryptPassword = passwordService.encryptPassword(paswd);
        System.out.println(encryptPassword);
        System.out.println(encryptPassword.length());

        System.out.println(passwordService.passwordsMatch(paswd,encryptPassword));

        //TSXTJF4iseTkPx6kTqPr5Imr48JjVgEX6h+FWNTa0mNtnC90ObaHhSwcJ/ionSrP7mQc2ShdiOXHi59uHsjdmQ==
        //k8ytEHdiAKRDD4Ya3iO//xON/xK5hjdKW7A1D/0g5JklX/gzVrwkN0zuzglDVSBqnfTX7Mmx+wlc8R9ECjCGpQ==

        //PasswordMatcher matcher = null;
        //matcher.getPasswordService();

        //DefaultPasswordService passwordService;
        //passwordService.setHashFormatFactory();

//        PasswordMatcher matcher = null;
        //密码匹配器
        //PasswordMatcher matcher;

        //设置密码计算服务
        //matcher.setPasswordService();

        //给realm设置匹配器
        //realm.setCredentialsMatcher(matcher);


       /* DefaultPasswordService passwordService;

        UserDBRealm realm = new UserDBRealm();
        realm.setCredentialsMatcher(matcher);


        SimpleCredentialsMatcher credentialsMatcher = (SimpleCredentialsMatcher) realm.getCredentialsMatcher();

        System.out.println(credentialsMatcher);*/
    }

    //生成盐
    @Test
    public void test1(){

//        PasswordMatcher
//        HashedCredentialsMatcher

        DefaultPasswordService service = new DefaultPasswordService();

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();

//        JdbcRealm
//        DefaultHashService service = new DefaultHashService();
//        System.out.println(service.getPrivateSalt());
//        System.out.println(service.getPrivateSalt());
//        System.out.println(service.getPrivateSalt());

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        usernamePasswordToken.setPassword("123456".toCharArray());
        usernamePasswordToken.setUsername("wangwu");

        DefaultPasswordService passwordService = new DefaultPasswordService();
        passwordService.setHashFormat(new Base64Format());
        String encryptPassword = passwordService.encryptPassword("123456");


        System.out.println(encryptPassword);
        System.out.println(passwordService.passwordsMatch("123456",encryptPassword));

//        HashedCredentialsMatcher
//        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        //16进制随机数
        //generator.nextBytes().toHex()


        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo("wangwu",encryptPassword,"wangwu");

        /*HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-512");//默认SHA-512算法
        matcher.setHashIterations(DefaultPasswordService.DEFAULT_HASH_ITERATIONS);
        matcher.setHashAlgorithmName(DefaultPasswordService.DEFAULT_HASH_ALGORITHM);
        boolean b = matcher.doCredentialsMatch(usernamePasswordToken,authenticationInfo);
        System.out.println(b);*/


        //38b60bd9c1bd4f758c4fd3ecd5d170e513a36637a5e3732d36c495c9a4cb6919
//        DefaultHashService service = new DefaultHashService();

    }
}
