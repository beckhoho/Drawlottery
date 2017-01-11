package com.hudongwx.drawlottery.common.exceptions;

/**
 * 服务层异常.
 * Date: 2016/12/30 0026
 * Time: 14:47
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class ServiceException extends BaseException {
    /**
     * Instantiates a new Service exception.
     */
    public ServiceException(){
        super();
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param msg the msg
     */
    public ServiceException(String msg){
        super(msg);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return "服务层错误";
    }
}
