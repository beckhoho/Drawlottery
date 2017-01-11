package com.hudongwx.drawlottery.common.exceptions;

/**
 * 控制层异常.
 * Date: 2016/12/30 0026
 * Time: 14:47
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class ControllerException extends BaseException {
    /**
     * Instantiates a new Controller exception.
     */
    public ControllerException() {
        super();
    }

    /**
     * Instantiates a new Controller exception.
     *
     * @param msg the msg
     */
    public ControllerException(String msg) {
        super(msg);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return "控制器错误";
    }
}
