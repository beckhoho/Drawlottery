package com.hudongwx.drawlottery.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常基类.
 * Date: 2016/12/30 0026
 * Time: 14:47
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class BaseException extends RuntimeException {

    /**
     * The Log.
     */
    private Logger log = LoggerFactory.getLogger(ServiceException.class);

    /**
     * Instantiates a new Base exception.
     */
    BaseException() {
        super("未知错误：");
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param msg the msg
     */
    BaseException(String msg) {
        super(msg);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return "未知";
    }
}
