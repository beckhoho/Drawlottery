package com.hudongwx.drawlottery.service.commodity.impl;

import com.github.pagehelper.PageHelper;
import com.hudongwx.drawlottery.dao.LuckCodeTemplateMapper;
import com.hudongwx.drawlottery.dao.LuckCodesMapper;
import com.hudongwx.drawlottery.pojo.LuckCodeTemplate;
import com.hudongwx.drawlottery.pojo.LuckCodes;
import com.hudongwx.drawlottery.service.commodity.LuckCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * <p>
     * <p>2017/1/17 优化代码，幸运码生成乱序</p>
     * <p>2017/1/18 优化代码，针对大量数据处理，做出分段存储优化</p>
     *
     * @param total 幸运码总量
     * @return 返回total对应的最后一个幸运码
     */
    @Override
    public String generate(final long total) {
        Long count = getCountCode();
        while (total > count) {
            Set<LuckCodeTemplate> set = new HashSet<>();
            for (long i = count + 1; i <= total && i <= count + 80000; i++) {
                LuckCodeTemplate temp = new LuckCodeTemplate();
                temp.setLuckCode(10000000 + i + "");
                set.add(temp);
            }
            long i = count + 1;
            for (LuckCodeTemplate t : set) {
                t.setId(i++);
            }
            luckTempMapper.insertSet(set);
            count += 80000;
        }
        return 10000000 + total + "";
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
     * <p>2017/1/18 优化代码，针对大量数据处理，做出分段读取存储优化，减少数据库操作次数</p>
     *
     * @param commodityId 商品id
     * @param count       总数
     */
    @Override
    public void connect(long commodityId, long count) {
        for (int currentPage = 0; currentPage * 500000 < count; currentPage++) {
            PageHelper.startPage(currentPage+1, 80000);
            List<LuckCodes> list = luckTempMapper.selectRange(10000000+count);
            for (LuckCodes code : list) {
                code.setCommodityId(commodityId);
            }
            luckMapper.insertCodeList(list);
        }
    }

}
