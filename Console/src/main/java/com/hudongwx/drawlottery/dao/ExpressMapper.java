package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.pojo.Express;

public interface ExpressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Express record);

    int insertSelective(Express record);

    Express selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);
}