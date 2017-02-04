package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    /**
     * 七牛ak
     */
    final String qiniuAccessKey = "F5kk6Wp3aSKV5ViXVd-hH0YZvoEeYrI_3dLx4SbQ";
    /**
     * 七牛sk
     */
    final String qiniuSecretKey = "6a182dZ4k2fl9DvJj4iLOeJlhgwKdopPPhjsk6oi";
    /**
     * 七牛上传空间
     */
    final String bucketName = "lottery";
    /**
     * token有效时间
     */
    final Long expries = 600L;
    /**
     * 七牛远程显示地址 注意要在最后加/
     */
    final String qiniuhost = "http://ojm4pmwbe.bkt.clouddn.com/";

    /**
     * 已分享
     */
    final int SHARE_STATE_YET = 1;

    /**
     * 未分享
     */
    final int SHARE_STATE_NOT = 0;

    @Autowired
    ShareMapper shareMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ShareImgMapper shareImgMapper;
    @Autowired
    CommoditysMapper commsMapper;
    @Autowired
    CommodityMapper commMapper;
    @Autowired
    CommodityHistoryMapper commodityHistoryMapper;

    /**
     * 得到七牛的upToken
     *
     * @param suffix 文件后缀名
     * @return upToken
     */
    @Override
    public String getUpToken(String suffix) {
        if (!suffix.startsWith("."))
            suffix = "." + suffix;
        //上传到七牛后保存的文件名
        String key = UUID.randomUUID().toString() + suffix;

        //密钥配置
        Auth auth = Auth.create(qiniuAccessKey, qiniuSecretKey);

        auth.uploadToken(bucketName);
        //生成token
        return auth.uploadToken(bucketName, key, expries, null);

    }

    /**
     * 上传文件到七牛云
     *
     * @param file 文件
     * @return 保存成功的文件名
     */
    @Override
    public String uploadToQiniu(MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        final String s = fileName.substring(fileName.indexOf("."), fileName.length());
        String key = UUID.randomUUID().toString() + s;
        final String upToken = getUpToken();
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(c);
        try {
            //调用put方法上传
            Response res = uploadManager.put(file.getBytes(), key, upToken);
            //打印返回的信息
            final JSONObject obj = JSONObject.parseObject(res.bodyString());
            return obj.get("key").toString();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到七牛的upToken
     *
     * @return upToken
     */
    @Override
    public String getUpToken() {
        //密钥配置
        Auth auth = Auth.create(qiniuAccessKey, qiniuSecretKey);

        auth.uploadToken(bucketName);
        //生成token
        return auth.uploadToken(bucketName);

    }

    /**
     * 判断七牛是否就绪
     *
     * @return
     */
    @Override
    public boolean isReady() {
        return !(null == qiniuAccessKey || null == qiniuSecretKey);
    }

    /**
     * 得到晒单详情
     *
     * @param shareId 分享id
     * @return 晒单详情
     */
    @Override
    public Share getShare(Long shareId) {
        final Share share = shareMapper.selectDetailsById(shareId);
        List<ShareImg> list = shareImgMapper.selectByShare(share.getId());
        share.setImgList(list);
        return share;
    }

    /**
     * 添加用户晒单
     *
     * @return 返回添加用户晒单
     */
    @Override
    public boolean addShare(Long accountId, Long commId, String desc, List<MultipartFile> imgs) {
        //判断是否晒过单
        Share hasShare = shareMapper.selectByCommId(commId);
        if (null != hasShare)
            return false;
        Commodity com = commMapper.selectByKey(commId);
        if ((com.getShareState() == null ? 0 : com.getShareState()) == 1)
            return false;
        Share share = new Share();
        share.setCommodityId(commId);
        share.setIssueDate(new Date().getTime());
        share.setParticulars(desc);
        share.setUserAccountId(accountId);
        shareMapper.insertByGeneratedKeys(share);
        for (MultipartFile img : imgs) {
            ShareImg shareImg = new ShareImg();
            String filename = uploadToQiniu(img);
            //拼接文件地址
            String url = qiniuhost + filename;
            shareImg.setShareId(share.getId());
            shareImg.setShareImgUrl(url);
            shareImgMapper.insert(shareImg);
        }
        commMapper.updateShareStateByCommodityId(com.getId(), SHARE_STATE_YET);
        commodityHistoryMapper.updateShareStateByCommodityId(com.getId(), SHARE_STATE_YET);
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
     * @param accountId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectByUserAccountId(Long accountId, Long lastCommId) {
        List<Share> shareList = shareMapper.selectByUserAccountId(accountId, lastCommId, Settings.PAGE_LOAD_SIZE_10);
        List<Map<String, Object>> mapList = createShareMapList(accountId, shareList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> selectAll(Long lastCommId) {
        List<Share> shareList = shareMapper.selectAllWithPage(lastCommId, Settings.PAGE_LOAD_SIZE_10);
        List<Map<String, Object>> mapList = createShareMapList(null, shareList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> selectByCommId(Long commId, Long lastCommId) {
        Long tempId = commMapper.selectTempIdByCommId(commId);
        List<Long> commIdList = commMapper.selectCommIdByTempId(tempId, lastCommId, Settings.PAGE_LOAD_SIZE_10);
        List<Map<String, Object>> mapList = null;
        List<Share> shareList;
        if (!commIdList.isEmpty()) {
            shareList = new ArrayList<>();
            for (Long aCommId : commIdList) {
                Share share = shareMapper.selectPassedCommShare(aCommId);
                if (null != share)
                    shareList.add(share);
            }
            mapList = createShareMapList(null, shareList);
        }
        return mapList;
    }

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
            Commoditys comm = commsMapper.selectByKey(share.getCommodityId());
            Map<String, Object> map = new HashMap<>();
            List<String> list = new ArrayList<>();
            if (null == account)
                user1 = userMapper.selectByPrimaryKey(share.getUserAccountId());
            map.put("id", share.getId());//添加
            map.put("headImgUrl", user1.getHeaderUrl());//添加用户头像
            map.put("userName", user1.getNickname());//添加用户昵称
            map.put("shareDate", share.getIssueDate());//添加分享时间
            map.put("commName", comm == null ? null : comm.getName());//添加商品id
            map.put("commRoundTime", comm == null ? null : comm.getRoundTime());//添加商品id
            map.put("context", share.getParticulars());//添加用户分享内容
            map.put("commId", share.getCommodityId());//添加商品id
            for (ShareImg sh : imgList)
                list.add(sh.getShareImgUrl());
            map.put("shareImgUrl", list);//添加用户晒单照片
            mapList.add(map);
        }
        return mapList;
    }

}
