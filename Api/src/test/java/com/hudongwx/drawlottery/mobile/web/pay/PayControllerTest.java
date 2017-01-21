package com.hudongwx.drawlottery.mobile.web.pay;

import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/20 0020 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/20 0020　<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
public class PayControllerTest extends TestBaseWeb {

    @Autowired
    PayController controller;

    @Test
    public void testCreateOrderByAlipay() throws Exception {
    }

    @Test
    public void testAlipayCallback() throws Exception {
        String  content = "discount=0.00&payment_type=1&subject=测试&trade_no=2013082244524842&buyer_email=dlwdgl@gmail.com&gmt_create=2013-08-22 14:45:23&notify_type=trade_status_sync&quantity=1&out_trade_no=082215222612710&seller_id=2088501624816263&notify_time=2013-08-22 14:45:24&body=测试测试&trade_status=TRADE_SUCCESS&is_total_fee_adjust=N&total_fee=1.00&gmt_payment=2013-08-22 14:45:24&seller_email=xxx@alipay.com&price=1.00&buyer_id=2088602315385429&notify_id=64ce1b6ab92d00ede0ee56ade98fdf2f4c&use_coupon=N&sign_type=RSA&sign=1glihU9DPWee+UJ82u3+mw3Bdnr9u01at0M/xJnPsGuHh+JA5bk3zbWaoWhU6GmLab3dIM4JNdktTcEUI9/FBGhgfLO39BKX/eBCFQ3bXAmIZn4l26fiwoO613BptT44GTEtnPiQ6+tnLsGlVSrFZaLB9FVhrGfipH2SWJcnwYs=";
        post("/api/v1/pub/user/order/alipay/callback",content, MediaType.APPLICATION_FORM_URLENCODED);
    }

    @Override
    public Object getController() {

        return controller;
    }
}