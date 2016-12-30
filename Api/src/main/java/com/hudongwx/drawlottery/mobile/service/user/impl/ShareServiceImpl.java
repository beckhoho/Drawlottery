package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.entitys.ShareImg;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.ShareImgMapper;
import com.hudongwx.drawlottery.mobile.mappers.ShareMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/21 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/21 16:48　<br/>
 * <p>
 * 晒单Service实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class ShareServiceImpl implements IShareService {

    @Autowired
    ShareMapper mapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ShareImgMapper shareImgMapper;
    /**
     * 添加用户晒单
     *
     * @param share 晒单对象
     * @return 返回添加用户晒单
     */
    @Override
    public boolean addShare(Share share) {
        return mapper.insert(share) > 0;
    }

    /**
     * 删除用户晒单
     *
     * @param shareid 用户晒单id
     * @return 返回删除结果
     */
    @Override
    public boolean deleteShare(Long shareid) {
        Share share = new Share();
        share.setId(shareid);
        return mapper.delete(share) > 0;
    }

    /**
     * 查看当前用户的所有晒单
     *
     * @param accountid
     * @param lastshareid
     * @param tag
     * @return
     */
    @Override
    public List<Share> selectShare(Long accountid, Long lastshareid, Integer tag) {
        Share share = new Share();
        if (tag == Settings.INITIALIZE_ENTER_STATUS) {
            share.setUserAccountId(accountid);
            return mapper.select(share);
        } else if (tag == Settings.DROP_DOWN_REFRESH) {//下拉刷新

            return null;
        }

        return null;
    }

    @Override
    public boolean friendsShare(Long account) {
        return false;
    }


    /**
     * 用户晒单列表
     * @param account
     * @return
     */
    @Override
    public List<Map<String, Object>> selectUserAll(Long account) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        User user1 = userMapper.selectByPrimaryKey(account);
        Share s = new Share();
        s.setUserAccountId(account);
        List<Share> shares = mapper.select(s);
        for(Share share : shares){
            ShareImg shareImg = new ShareImg();
            shareImg.setShareId(share.getId());
            List<ShareImg> imgList = shareImgMapper.select(shareImg);
            Map<String,Object> map = new HashMap<>();
            List<String> list = new ArrayList<>();
            map.put("userHeaderUrl",user1.getHeaderUrl());//添加用户头像
            map.put("userAccountId",account);//添加用户id
            map.put("userName",user1.getNickname());//添加用户昵称
            map.put("shareTime",share.getIssueDate());//添加分享时间
            map.put("context",share.getParticulars());//添加用户分享内容
            map.put("commodityId",share.getCommodityId());//添加商品id
            for(ShareImg sh : imgList){
                list.add(sh.getShareImgUrl());
            }
            map.put("shareImgUrl",list);//添加用户晒单照片
            listMap.add(map);
        }

        return listMap;
    }

}
