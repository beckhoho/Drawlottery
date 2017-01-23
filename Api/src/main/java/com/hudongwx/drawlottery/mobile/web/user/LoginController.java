/**
 * Copyright (c) 2011-2014, hubin (243194995@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.hudongwx.drawlottery.mobile.web.user;

import com.hudongwx.drawlottery.mobile.web.BaseController;

/**
 * 登录
 */
public class LoginController extends BaseController {

//    public void index() throws UnsupportedEncodingException {
//        fillHeaderAndFooter();
//        String returnURL = null;
//        returnURL = URLDecoder.decode(getPara("ReturnURL"), "UTF-8");
//        System.out.println(returnURL);
//        setAttr("url", returnURL);
//        render("demo.ftl");
//    }
//
//    @Before(POST.class)
//    public void login() {
//        /**
//         * 登录 生产环境需要过滤sql注入
//         */
//        String url = getPara("url");
//
//        WafRequestWrapper req = new WafRequestWrapper(getRequest());
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String vcode = getPara("vcode");
//        if (!CaptchaRender.validate(this, vcode)) {
//            RenderKit.render403Error(this, new LoginStatus(CaptchaRender.validate(this, vcode),
//                    MockKit.<Exp>start()
//                            .add(new Exp("vcode", Exp.STATE_ERROR, "验证码错误"))
//                            .build()
//            ));
//            return;
//        }
//        if ("kisso".equals(username) && "123".equals(password)) {
//            /**
//             * 系统定义 SSOToken st = new SSOToken();
//             * <p>
//             * 自定义 MyToken
//             * </p>
//             */
//            SSOToken mt = new SSOToken();
//            mt.setId(1000L);
//            mt.setUid("1000");
//            mt.setIp(IpHelper.getIpAddr(getRequest()));
//
//            //记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
//            String rememberMe = req.getParameter("rememberMe");
//            if ("on".equals(rememberMe)) {
//                HttpSession session = getSession();
//                log.info(session.toString());
//                setAttr(SSOConfig.SSO_COOKIE_MAXAGE, Common.MAX_AGE);
//            }
//
//            SSOHelper.setSSOCookie(getRequest(), getResponse(), mt, true);
//            if (url == null)
//                renderNull();
//            else
//                RenderKit.renderSuccess(this, new LoginStatus(true, new ArrayList<>()));
//        } else {
//            RenderKit.render403Error(this, new LoginStatus(CaptchaRender.validate(this, vcode),
//                    MockKit.<Exp>start()
//                            .add(new Exp("username", Exp.STATE_ERROR, "账号有问题"))
//                            .build()
//            ));
//        }
//    }
//
//    public void otherLogin() throws QQConnectException {
//        redirect(new Oauth().getAuthorizeURL(getRequest()));
//    }
//
//
//    /**
//     * <p>
//     * 支持APP端登录
//     * <br>
//     * 调用时需要为请求Header设置PLATFORM=APP
//     * 否则请求将不会被kisso处理，而直接视为jFinal的controller
//     * </p>
//     */
//    public void auth() {
//        Res res = new Res();
//        SSOToken token = SSOHelper.getToken(getRequest());
//        if (token != null) {
//            renderJson(res);
//        } else {
//            if (HttpUtil.isPost(getRequest())) {
//                WafRequestWrapper req = new WafRequestWrapper(getRequest());
//                String username = req.getParameter("username");
//                String password = req.getParameter("password");
//                if ("admin".equals(username) && "admin".equals(password)) {
//                    token = new SSOToken();
//                    token.setUid("1000");
//                    token.setIp(IpHelper.getIpAddr(getRequest()));
//                    SSOHelper.setSSOCookie(getRequest(), getResponse(), token, true);
//                    res.setData("已下发Cookies至响应");
//                    renderJson(res);
//                } else {
//                    RenderKit.renderError(this, MockKit.<Exp>start()
//                            .add(new Exp("vcode", Exp.STATE_ERROR, "验证码错误"))
//                            .add(new Exp("username", Exp.STATE_ERROR, "有点问题哟"))
//                            .build());
//                }
//            } else {
//                renderError(401);
//            }
//        }
//    }
//
//    public void afterLogin() throws IOException, QQConnectException {
//        HttpServletResponse response = getResponse();
//        HttpServletRequest request = getRequest();
//        response.setContentType("text/html; charset=utf-8");
//
//        PrintWriter out = response.getWriter();
//
//        AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
//
//        String accessToken = null,
//                openID = null;
//        long tokenExpireIn = 0L;
//
//
//        if (accessTokenObj.getAccessToken().equals("")) {
////                网站被CSRF攻击了或者用户取消了授权
//            RenderKit.render403Error(this, new LoginStatus(false,
//                    MockKit.<Exp>start()
//                            .add(new Exp("otherLogin", Exp.STATE_ERROR, "没有能够得到你的QQ登录信息"))
//                            .build()
//            ));
//        } else {
//            accessToken = accessTokenObj.getAccessToken();
//            tokenExpireIn = accessTokenObj.getExpireIn();
//
//            request.getSession().setAttribute("demo_access_token", accessToken);
//            request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
//
//            // 利用获取到的accessToken 去获取当前用的openid -------- start
//            OpenID openIDObj = new OpenID(accessToken);
//            openID = openIDObj.getUserOpenID();
//
//            out.println("欢迎你，代号为 " + openID + " 的用户!");
//            request.getSession().setAttribute("demo_openid", openID);
//            out.println("<a href=" + "/shuoshuoDemo.html" + " target=\"_blank\">去看看发表说说的demo吧</a>");
//            // 利用获取到的accessToken 去获取当前用户的openid --------- end
//
//
//            out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
//            UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
//            UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
//            out.println("<br/>");
//            if (userInfoBean.getRet() == 0) {
//                out.println(userInfoBean.getNickname() + "<br/>");
//                out.println(userInfoBean.getGender() + "<br/>");
//                out.println("黄钻等级： " + userInfoBean.getLevel() + "<br/>");
//                out.println("会员 : " + userInfoBean.isVip() + "<br/>");
//                out.println("黄钻会员： " + userInfoBean.isYellowYearVip() + "<br/>");
//                out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
//                out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
//                out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
//            } else {
//                out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
//            }
//            out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>");
//
//
//            out.println("<p> start ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ start <p>");
//            PageFans pageFansObj = new PageFans(accessToken, openID);
//            PageFansBean pageFansBean = pageFansObj.checkPageFans("97700000");
//            if (pageFansBean.getRet() == 0) {
//                out.println("<p>验证您" + (pageFansBean.isFans() ? "是" : "不是") + "QQ空间97700000官方认证空间的粉丝</p>");
//            } else {
//                out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + pageFansBean.getMsg());
//            }
//            out.println("<p> end ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ end <p>");
//
//
//            out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start </p>");
//            com.qq.connectLuckCodes.api.weibo.UserInfo weiboUserInfo = new com.qq.connectLuckCodes.api.weibo.UserInfo(accessToken, openID);
//            com.qq.connectLuckCodes.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
//            if (weiboUserInfoBean.getRet() == 0) {
//                //获取用户的微博头像----------------------start
//                out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
//                out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
//                out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
//                //获取用户的微博头像 ---------------------end
//
//                //获取用户的生日信息 --------------------start
//                out.println("<p>尊敬的用户，你的生日是： " + weiboUserInfoBean.getBirthday().getYear()
//                        + "年" + weiboUserInfoBean.getBirthday().getMonth() + "月" +
//                        weiboUserInfoBean.getBirthday().getDay() + "日");
//                //获取用户的生日信息 --------------------end
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("<p>所在地:").append(weiboUserInfoBean.getCountryCode()).append("-").append(weiboUserInfoBean.getProvinceCode()).append("-").append(weiboUserInfoBean.getCityCode()).append(weiboUserInfoBean.getLocation());
//
//                //获取用户的公司信息---------------------------start
//                ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
//                if (companies.size() > 0) {
//                    //有公司信息
//                    for (Company company : companies) {
//                        sb.append("<p>曾服役过的公司：公司ID-").append(company.getID()).append(" 名称-").append(company.getCompanyName()).append(" 部门名称-").append(company.getDepartmentName()).append(" 开始工作年-").append(company.getBeginYear()).append(" 结束工作年-").append(company.getEndYear());
//                    }
//                } else {
//                    //没有公司信息
//                    log.info("没有公司信息");
//                }
//                //获取用户的公司信息---------------------------end
//
//                out.println(sb.toString());
//
//            } else {
//                out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + weiboUserInfoBean.getMsg());
//            }
//
//            out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- end </p>");
//        }
//    }
}
