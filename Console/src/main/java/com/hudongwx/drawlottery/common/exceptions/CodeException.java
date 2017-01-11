package com.hudongwx.drawlottery.common.exceptions;

/**
 * 通用代码级异常.
 * Date: 2016/12/30 0026
 * Time: 14:47
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class CodeException extends BaseException{
    /**
     * Instantiates a new Code exception.
     */
    public CodeException(){
        super();
    }

    /**
     * Instantiates a new Code exception.
     *
     * @param msg the msg
     */
    public CodeException(String msg) {
        super(msg);
    }

    /**
     * Get type string.
     *
     * @return the string
     */
    public String getType(){
        return "代码错误";
    }
}
