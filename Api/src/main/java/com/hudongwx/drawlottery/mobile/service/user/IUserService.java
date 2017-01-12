package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.User;

import java.util.List;
import java.util.Map;

/**
 * 用户
 */
public interface IUserService {

    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @return
     */
    boolean register(String phone, String password);

    /**
     * 手机号查询用户
     *
     * @param phone
     * @return Users
     */
    User queryUserByPhoneNum(String phone);

    Map<String,Object> getUserInfo(User user);

    /**
     * 查询用户中奖历史
     *
     * @param accountId
     * @return
     */
    List<Map<String,Object>> selectHistoryLottery(Long accountId);

    /**
     * 用户购买历史(夺宝历史)
     *
     * @param accountId
     * @param item
     * @return
     */
    List<Map<String,Object>> selectHistoryPay(Long accountId,Integer item);

    /**
     * 添加推广Id
     *
     * @param accountId 当前用户Id
     * @param promId 推广Id
     * @return
     */
    boolean addPromoterId(Long accountId, Long promId);


    /**
     * 查询用户信息
     * @param openId
     * @param platform
     * @return
     */
    User queryByOpenId(String openId,String platform);

    /**
     * 第三方账号绑定
     * @param openId
     * @param password
     * @param header_url
     * @param nickname
     * @param platform
     * @return
     */
    User registerByOpenId(String openId,String password,String header_url,String nickname,String platform);

    /**
     * 校验openId是否合法
     * @return
     */
    boolean checkOpenId(String token,String openId);
}

