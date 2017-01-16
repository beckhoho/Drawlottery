package com.hudongwx.drawlottery.mobile.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/15 0015 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/15 0015　<br/>
 * <p>
 *     登录验证器
 * <p>
 * @email 294786949@qq.com
 */
public class MobileAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //内部跳转
        request.getRequestDispatcher("/api/v1/pub/error/403").forward(request,response);
        //如果返回false表示该拦截器实例已经处理了，将直接返回结果
        return false;
    }

}
