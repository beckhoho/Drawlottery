package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.base.BaseMapper;
import com.hudongwx.drawlottery.pojo.ConnectFilePath;

public interface ConnectFilePathMapper extends BaseMapper<ConnectFilePath> {
    public String getPath(String fileName);
}