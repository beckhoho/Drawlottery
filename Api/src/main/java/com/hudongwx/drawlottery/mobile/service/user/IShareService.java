package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.springframework.web.multipart.MultipartFile;

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
 * 创建　kiter　2016/12/21 15:56　<br/>
 * <p>
 * 用户晒单service
 * <p>
 * @email 294786949@qq.com
 */
public interface IShareService {

    /**
     * 用户添加晒单信息
     *
     * @return
     */
    boolean addShare(Long accountId, Long commId, String desc, List<MultipartFile> imgs);

    /**
     * 用户删除晒单信息
     *
     * @param shareid 晒单id
     * @return
     */
    boolean deleteShare(Long shareid);

    /**
     * 用户晒单列表
     *
     * @param accountid
     * @return
     */
    List<Share> selectShare(Long accountid, Long lastshareid, Integer tag);

    //用户分享晒单
    boolean friendsShare(Long account);

    //查看用户晒单
    List<Map<String, Object>> selectByUserAccountId(Long accountId, Long lastCommId);

    //首页全部晒单
    List<Map<String, Object>> selectAll(Long lastCommId);

    //首页全部晒单
    List<Map<String, Object>> selectByCommId(Long commId, Long lastCommId);

    /**
     * 得到七牛的upToken
     *
     * @param suffix 文件后缀名
     * @return upToken
     */
    public String getUpToken(String suffix);

    /**
     * 上传文件到七牛云
     *
     * @param file 文件
     * @return 保存成功的文件名
     */
    public String uploadToQiniu(MultipartFile file);

    /**
     * 得到七牛的upToken
     *
     * @return upToken
     */
    public String getUpToken();

    /**
     * 判断七牛是否就绪
     *
     * @return
     */
    public boolean isReady();

    /**
     * 得到晒单详情
     * @param shareId 分享id
     * @return 晒单详情
     */
    public Share getShare(Long shareId);
}
