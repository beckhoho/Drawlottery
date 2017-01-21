package com.hudongwx.drawlottery.mobile.entitys;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/16 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/16 0016　<br/>
 * <p>
 *     第三方注册,登录认证
 * <p>
 * @email 294786949@qq.com
 */
public class ThirdPartyLoginToken extends UsernamePasswordToken {

    private String openid; //第三方登录openid
    private String accessToken; //第三方登录accessToken
    private int platform = -1;//第三方登录平台id,1=qq,2=weixin
    private String nickName;
    private String headImg;


    public ThirdPartyLoginToken(String openid,String accessToken,int platform,String nickName,String headImg){
        this.openid = openid;
        this.accessToken = accessToken;
        this.platform = platform;
        this.headImg = headImg;
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    /**
     * 是qq
     * @return
     */
    public boolean isQQPlatform(){
        return platform == 1;
    }

    /**
     * 是微信
     * @return
     */
    public boolean isWeixinPlatform(){
        return platform == 2;
    }

}
