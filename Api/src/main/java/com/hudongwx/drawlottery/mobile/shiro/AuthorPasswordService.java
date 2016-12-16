package com.hudongwx.drawlottery.mobile.shiro;

import com.hudongwx.drawlottery.mobile.entitys.User;
import com.sun.xml.internal.xsom.impl.AttributeUseImpl;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashingPasswordService;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.format.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DATE:2016-12-2016/12/14 0014-22:00
 * Author: origin
 * DESC: 密码加密,和密码匹配
 *
 */
public class AuthorPasswordService implements HashingPasswordService {

    private static final Logger log = LoggerFactory.getLogger(AuthorPasswordService.class);

    public static final int DEFAULT_HASH_ITERATIONS = 5; //散列迭代次数
    public static final String DEFAULT_ALGORITHM_NAME = Sha512Hash.ALGORITHM_NAME; //散列算法
    //私盐
    private final SimpleByteSource privateSalt = new  SimpleByteSource("294786949@qq.com");
    //公钥随机数
    private final SecureRandomNumberGenerator random = new SecureRandomNumberGenerator();

    //设置格式
    private HashFormat hashFormat;
    private HashFormatFactory hashFormatFactory;

    public AuthorPasswordService() {
        this.hashFormat = new Base64Format();//设置加密后的格式
        this.hashFormatFactory = new DefaultHashFormatFactory();
    }

    /**
     * 自动生成盐,加密用户信息
     * @param user
     */
    public void encryptUserPassword(User user) {
        //公盐
        ByteSource publicSalt = random.nextBytes();
        //私盐
        ByteSource privateSalt = this.privateSalt;
        //合并后的盐
        ByteSource combineSalt = combine(privateSalt, publicSalt);
        //设置盐
        user.setSalt(publicSalt.toBase64());

        Hash hash = hashPassword(user.getPasswd(), publicSalt, combineSalt);
        //加密后的密码
        String format = this.hashFormat.format(hash);

        user.setPasswd(format);
    }

    protected Hash hashProvidedCredentials(Object credentials, Object salt, int hashIterations) {
        ByteSource publicSalt = ByteSource.Util.bytes(salt);
        ByteSource combine = combine(getPrivateSalt(),publicSalt);
        Hash hash = hashPassword(credentials, publicSalt, combine);
        return hash;
    }

    /**
     *
     * @param plaintext 明文
     * @param publicSalt 公盐
     * @param combineSalt 私盐+公盐
     * @return
     */
    public Hash hashPassword(Object plaintext,ByteSource publicSalt,ByteSource combineSalt) {
        ByteSource plaintextBytes = createByteSource(plaintext);
        Hash computed = new SimpleHash(DEFAULT_ALGORITHM_NAME, plaintextBytes, combineSalt, DEFAULT_HASH_ITERATIONS);

        SimpleHash result = new SimpleHash(DEFAULT_ALGORITHM_NAME);
        result.setBytes(computed.getBytes());
        result.setIterations(DEFAULT_HASH_ITERATIONS);
        result.setSalt(publicSalt);
        return result;
    }

    /**
     * 合并公盐和私盐
     * @param privateSalt
     * @param publicSalt
     * @return
     */
//    protected ByteSource combine(ByteSource privateSalt, ByteSource publicSalt) {
//        //私盐
//        byte[] privateSaltBytes = privateSalt.getBytes();
//        int privateSaltLength = privateSaltBytes.length;
//
//        //公盐
//        byte[] publicSaltBytes = publicSalt.getBytes();
//        int publicBytesLength =   publicSaltBytes.length;
//
//        //拼接之后的长度
//        int length = privateSaltLength + publicBytesLength;
//
//        //合并后的数组
//        byte[] combined = new byte[length];
//        System.arraycopy(privateSaltBytes, 0, combined,0,privateSaltLength);
//        System.arraycopy(publicSaltBytes, 0, combined, privateSaltLength,publicBytesLength);
//
//        return ByteSource.Util.bytes(combined);
//    }


    protected ByteSource combine(ByteSource privateSalt, ByteSource publicSalt) {

        byte[] privateSaltBytes = privateSalt != null ? privateSalt.getBytes() : null;
        int privateSaltLength = privateSaltBytes != null ? privateSaltBytes.length : 0;

        byte[] publicSaltBytes = publicSalt != null ? publicSalt.getBytes() : null;
        int extraBytesLength = publicSaltBytes != null ? publicSaltBytes.length : 0;

        int length = privateSaltLength + extraBytesLength;

        if (length <= 0) {
            return null;
        }

        byte[] combined = new byte[length];

        int i = 0;
        for (int j = 0; j < privateSaltLength; j++) {
            assert privateSaltBytes != null;
            combined[i++] = privateSaltBytes[j];
        }
        for (int j = 0; j < extraBytesLength; j++) {
            assert publicSaltBytes != null;
            combined[i++] = publicSaltBytes[j];
        }

        return ByteSource.Util.bytes(combined);
    }


    public String encryptPassword(Object plaintext) {
        Hash hash = hashPassword(plaintext);
        return this.hashFormat.format(hash);
    }

    //计算密码的hash码值
    public Hash hashPassword(Object plaintext) {
        ByteSource plaintextBytes = createByteSource(plaintext);
        if (plaintextBytes == null || plaintextBytes.isEmpty()) {
            return null;
        }
        HashRequest request = createHashRequest(plaintextBytes);
        return computeHash(request);
    }



    public boolean passwordsMatch(Object plaintext, Hash saved) {
        ByteSource plaintextBytes = createByteSource(plaintext);

        if (saved == null || saved.isEmpty()) {
            return plaintextBytes == null || plaintextBytes.isEmpty();
        } else {
            if (plaintextBytes == null || plaintextBytes.isEmpty()) {
                return false;
            }
        }

        HashRequest request = buildHashRequest(plaintextBytes, saved);

        Hash computed = this.computeHash(request);

        return saved.equals(computed);
    }

    protected HashRequest createHashRequest(ByteSource plaintext) {
        return new HashRequest.Builder().setSource(plaintext).build();
    }

    protected ByteSource createByteSource(Object o) {
        return ByteSource.Util.bytes(o);
    }


    public boolean passwordsMath(java.lang.Object paswd, String pubslat, String entryPaswd){

        SimpleHash simpleHash = new SimpleHash(DEFAULT_ALGORITHM_NAME);
        simpleHash.setIterations(DEFAULT_HASH_ITERATIONS);
        simpleHash.setSalt(ByteSource.Util.bytes(pubslat));

        ByteSource byteSource = createByteSource(paswd);
        HashRequest hashRequest = this.buildHashRequest(byteSource, simpleHash);

        Hash hash = computeHash(hashRequest);
        String format = this.hashFormat.format(hash);

        return format.equals(entryPaswd);
    }

    public boolean passwordsMatch(Object submittedPlaintext, String saved) {
        ByteSource plaintextBytes = createByteSource(submittedPlaintext);

        if (saved == null || saved.length() == 0) {
            return plaintextBytes == null || plaintextBytes.isEmpty();
        } else {
            if (plaintextBytes == null || plaintextBytes.isEmpty()) {
                return false;
            }
        }

        //First check to see if we can reconstitute the original hash - this allows us to
        //perform password hash comparisons even for previously saved passwords that don't
        //match the current HashService configuration values.  This is a very nice feature
        //for password comparisons because it ensures backwards compatibility even after
        //configuration changes.
        HashFormat discoveredFormat = this.hashFormatFactory.getInstance(saved);

        if (discoveredFormat != null && discoveredFormat instanceof ParsableHashFormat) {
            ParsableHashFormat parsableHashFormat = (ParsableHashFormat)discoveredFormat;
            Hash savedHash = parsableHashFormat.parse(saved);

            return passwordsMatch(submittedPlaintext, savedHash);
        }

        //If we're at this point in the method's execution, We couldn't reconstitute the original hash.
        //So, we need to hash the submittedPlaintext using current HashService configuration and then
        //compare the formatted output with the saved string.  This will correctly compare passwords,
        //but does not allow changing the HashService configuration without breaking previously saved
        //passwords:

        //The saved text value can't be reconstituted into a Hash instance.  We need to format the
        //submittedPlaintext and then compare this formatted value with the saved value:
        HashRequest request = createHashRequest(plaintextBytes);
        Hash computed = this.computeHash(request);
        String formatted = this.hashFormat.format(computed);

        return saved.equals(formatted);
    }

    public void entryUser(User user){
        SimpleHash simpleHash = new SimpleHash(DEFAULT_ALGORITHM_NAME);
        simpleHash.setIterations(DEFAULT_HASH_ITERATIONS);
        simpleHash.setSalt(random.nextBytes());

        ByteSource byteSource = createByteSource(user.getPasswd());
        HashRequest hashRequest = this.buildHashRequest(byteSource, simpleHash);

        Hash hash = computeHash(hashRequest);
        String format = this.hashFormat.format(hash);
        user.setSalt(hashRequest.getSalt().toBase64());
        user.setPasswd(format);
    }

    protected HashRequest buildHashRequest(ByteSource plaintext, Hash saved) {
        //keep everything from the saved hash except for the source:
        return new HashRequest.Builder().setSource(plaintext)
                //now use the existing saved data:
                .setAlgorithmName(saved.getAlgorithmName())
                .setSalt(saved.getSalt())
                .setIterations(saved.getIterations())
                .build();
    }

    public Hash computeHash(HashRequest request) {
        ByteSource source = request.getSource();
        ByteSource publicSalt = getPublicSalt(request);
        ByteSource privateSalt = getPrivateSalt();
        ByteSource salt = combine(privateSalt, publicSalt);

        Hash computed = new SimpleHash(DEFAULT_ALGORITHM_NAME, source, salt, DEFAULT_HASH_ITERATIONS);

        SimpleHash result = new SimpleHash(DEFAULT_ALGORITHM_NAME);
        result.setBytes(computed.getBytes());
        result.setIterations(DEFAULT_HASH_ITERATIONS);
        //Only expose the public salt - not the real/combined salt that might have been used:
        result.setSalt(publicSalt);

        return result;
    }

    /**
     * 使用公盐
     * @param request
     * @return
     */
    protected ByteSource getPublicSalt(HashRequest request) {
        ByteSource publicSalt = request.getSalt();
        if (publicSalt != null && !publicSalt.isEmpty()) {
            return publicSalt;
        }
        publicSalt = null;
        //check to see if we need to generate one:
        ByteSource privateSalt = getPrivateSalt();
        boolean privateSaltExists = privateSalt != null && !privateSalt.isEmpty();

        if (privateSaltExists) {
            publicSalt = random.nextBytes();
        }
        return publicSalt;
    }
    
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        UsernamePasswordToken token1 = (UsernamePasswordToken)token;
        SimpleAuthenticationInfo info1 = (SimpleAuthenticationInfo) info;

        SimpleHash simpleHash = new SimpleHash(DEFAULT_ALGORITHM_NAME);
        simpleHash.setIterations(DEFAULT_HASH_ITERATIONS);
        simpleHash.setSalt(info1.getCredentialsSalt());

        ByteSource byteSource = createByteSource(token1.getPassword());
        HashRequest hashRequest = this.buildHashRequest(byteSource, simpleHash);

        Hash hash = computeHash(hashRequest);


        final boolean b = passwordsMatch(info.getCredentials(), hash);
        return b;
    }

    public ByteSource getPrivateSalt() {
        return this.privateSalt;
    }

    public HashFormat getHashFormat() {
        return hashFormat;
    }

    public void setHashFormat(HashFormat hashFormat) {
        this.hashFormat = hashFormat;
    }

}
