package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.entitys.ShareImg;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.ShareImgMapper;
import com.hudongwx.drawlottery.mobile.mappers.ShareMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    ShareMapper shareMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ShareImgMapper shareImgMapper;

    /**
     * 添加用户晒单
     *
     * @return 返回添加用户晒单
     */
    @Override
    public boolean addShare(Long accountId, Long commId, String desc, List<MultipartFile> imgs) {
        if (!shareMapper.selectByCommId(commId).isEmpty()) {
            return false;
        }
        long date = new Date().getTime();
        Share share = new Share();
        share.setCommodityId(commId);
        share.setParticulars(desc);
        share.setUserAccountId(accountId);
        share.setIssueDate(date);
        if (shareMapper.insert(share) <= 0)
            return false;
        String fileSavePath = "C:\\Users\\wu\\IdeaProjects\\DrawLottery\\Api\\src\\main\\resources\\static\\imgs\\shareimg";
        for (MultipartFile multipartFile : imgs) {
            try {
                String filename = multipartFile.getOriginalFilename();
                ShareImg shareImg = new ShareImg();
                shareImg.setShareId(shareMapper.selectByIssueDate(date).get(0).getId());
                shareImg.setShareImgUrl("imgs/shareimg" + filename);
                if (shareImgMapper.insert(shareImg) > 0)
                    return false;
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(fileSavePath, filename));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
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
        return shareMapper.delete(share) > 0;
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
            return shareMapper.select(share);
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
     *
     * @param account
     * @return
     */
    @Override
    public List<Map<String, Object>> selectByUserAccountId(Long account) {
        List<Share> shareList = shareMapper.selectByUserAccountId(account);
        List<Map<String, Object>> mapList = createShareMapList(account, shareList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> selectAll(Integer page) {
        List<Share> shareList = shareMapper.selectAll();
        List<Map<String, Object>> mapList = createShareMapList(null, shareList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> selectByCommId(Long commId) {
        List<Share> shareList = shareMapper.selectByCommId(commId);
        List<Map<String, Object>> mapList = createShareMapList(null, shareList);
        return mapList;
    }

//    /**
//     * 首页晒单
//     *
//     * @return
//     */
//    @Override
//    public List<Map<String, Object>> selectAll() {
//        List<Map<String, Object>> mapList = new ArrayList<>();
//        List<Share> list = shareMapper.selectAll();
//        for (Share s : list) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", s.getId());//获取晒单ID
//            map.put("accountId", s.getUserAccountId());//获取用户ID
//            map.put("particulars", s.getParticulars());//获取晒单分享内容
//            map.put("commodityId", s.getCommodityId());//添加商品ID
//            map.put("date", s.getIssueDate());//添加晒单时间
//            map.put("imgUrl", imgUrl(s));//添加晒单图片List
//            mapList.add(map);
//        }
//
//        return mapList;
//    }

    public List<String> imgUrl(Share s) {
        List<String> list = new ArrayList<>();
        ShareImg i = new ShareImg();
        i.setShareId(s.getId());
        List<ShareImg> imgs = shareImgMapper.select(i);
        for (ShareImg img : imgs) {
            list.add(img.getShareImgUrl());
        }
        return list;
    }

    private List<Map<String, Object>> createShareMapList(Long account, List<Share> shares) {
        User user1 = null;
        if (null != account)
            user1 = userMapper.selectByPrimaryKey(account);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Share share : shares) {
            ShareImg shareImg = new ShareImg();
            shareImg.setShareId(share.getId());
            List<ShareImg> imgList = shareImgMapper.select(shareImg);
            Map<String, Object> map = new HashMap<>();
            List<String> list = new ArrayList<>();
            if (null == account)
                user1 = userMapper.selectByPrimaryKey(share.getUserAccountId());
            map.put("userHeaderUrl", user1.getHeaderUrl());//添加用户头像
            map.put("userName", user1.getNickname());//添加用户昵称
            map.put("shareTime", share.getIssueDate());//添加分享时间
            map.put("context", share.getParticulars());//添加用户分享内容
            map.put("commodityId", share.getCommodityId());//添加商品id
            map.put("commodityId", share.getCommodityId());//添加商品id
            for (ShareImg sh : imgList)
                list.add(sh.getShareImgUrl());
            map.put("shareImgUrl", list);//添加用户晒单照片
            mapList.add(map);
        }
        return mapList;
    }

}
