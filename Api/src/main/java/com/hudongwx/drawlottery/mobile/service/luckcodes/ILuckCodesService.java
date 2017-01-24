package com.hudongwx.drawlottery.mobile.service.luckcodes;

import com.hudongwx.drawlottery.mobile.entitys.LuckCodeTemplate;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;

import java.util.List;
import java.util.Set;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:33　<br/>
 * <p>
 * 幸运码service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface ILuckCodesService {

    //查询商品幸运码
    List<LuckCodes> select(Long commodId);

    //删除幸运码
    boolean delete(Long id);

    //查看用户幸运码
    List<LuckCodes> selectByUserId(Long accountId);

    /**
     * 得到模板总数
     *
     * @return 总数
     */
    Long getTempCount();

    /**
     * 添加模板集合到模板表中
     *
     * @param set 模板集合（注意是set，为了打乱顺序）
     */
    void addToTemp(Set<LuckCodeTemplate> set);

    /**
     * 查询范围内的幸运码
     *
     * @param count 总数
     * @return 幸运码集合
     */
    List<LuckCodes> selectRange(long count);

    /**
     * 插入幸运码集合
     *
     * @param list 集合
     */
    void insertCodeList(List<LuckCodes> list);

    /**
     * 查询用于参与总数
     * @param accountId 用户id
     * @param commodityId 商品id
     * @return 参与总数
     */
    int getCount(Long accountId,Long commodityId);
}
