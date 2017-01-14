package com.hudongwx.drawlottery.service.commodity.impl;

import com.hudongwx.drawlottery.dao.LuckCodeTemplateMapper;
import com.hudongwx.drawlottery.dao.LuckCodesMapper;
import com.hudongwx.drawlottery.pojo.LuckCodeTemplate;
import com.hudongwx.drawlottery.pojo.LuckCodes;
import com.hudongwx.drawlottery.service.commodity.LuckCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 19:02
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service
public class LCServiceImpl implements LuckCodeService {
    @Resource
    private LuckCodeTemplateMapper luckTempMapper;
    @Resource
    private LuckCodesMapper luckMapper;

    /**
     * 生成幸运码（会尝试生成，如果total已经小于或等于目前已有的了，就不再生成）
     *
     * @param total 幸运码总量
     * @return 返回total对应的最后一个幸运码
     */
    @Override
    public String generate(final long total) {
        final Long count = getCountCode();
        if (total > count) {
            LuckCodeTemplate temp = new LuckCodeTemplate();
            for (long i = count + 1; i <= total; i++) {
                temp.setId(i);
                temp.setLuckCode(100000000 + i + "");
                luckTempMapper.insert(temp);
            }
        }
        return 100000000 + total + "";
    }

    /**
     * 得到幸运码模板中的幸运码总量
     *
     * @return 总量
     */
    @Override
    public Long getCountCode() {
        return luckTempMapper.getCount();
    }

    /**
     * 将幸运码关联到商品
     *
     * @param commodityId 商品id
     * @param count       总数
     */
    @Override
    public void connect(long commodityId, long count) {
        final LuckCodes code = new LuckCodes();
        for (long i = 0; i < count; i++) {
            code.setCommodityId(commodityId);
            code.setLuckCodeTemplateId(i + 1);
            luckMapper.insertAuto(code);
        }
    }

}
