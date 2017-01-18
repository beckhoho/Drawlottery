package com.hudongwx.drawlottery.common.exceptions;

/**
 * Drawlottery.
 * Date: 2017/1/18 0018
 * Time: 11:48
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class ValidateException extends BaseException {
    /**
     * Instantiates a new Validate exception.
     */
    public ValidateException() {
        super();
    }

    /**
     * Instantiates a new Validate exception.
     *
     * @param msg the msg
     */
    public ValidateException(String msg) {
        super(msg);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return "校验错误";
    }
}
